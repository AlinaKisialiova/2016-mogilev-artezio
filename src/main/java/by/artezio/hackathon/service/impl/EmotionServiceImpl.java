package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.service.dto.enumeration.EmotionTypes;
import by.artezio.hackathon.service.EmotionService;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmotionServiceImpl implements EmotionService {

    private static final String EMOTION_DELIMETER = ";";
    private static final String VALUE_DELIMETER = ":";
    private static final String MCRSFT_REC_SERVICE_URL = "https://api.projectoxford.ai/emotion/v1.0/recognize";
    private static final String MCRSFT_REC_SERVICE_TOKEN = "c815def4b0544f9cb6906b7986705bbd";
    private static final String FAKE_JSON = "[" +
            "{\"faceRectangle\":{\"height\":362,\"left\":200,\"top\":259,\"width\":362}," +
            "\"scores\":{\"anger\":0.842028,\"contempt\":0.004918335,\"disgust\":0.147966027," +
            "\"fear\":9.70716646E-05,\"happiness\":4.799247E-06,\"neutral\":0.00493922364," +
            "\"sadness\":3.30198527E-05,\"surprise\":1.34937009E-05}}]";

    private static boolean DEBUG_MODE = true;

    @Override
    public List<UserEmotionDto> loadEmotionsByUrl(String url) {
        if(DEBUG_MODE) {
            return getDebugEmotions();
        }
        if(url.isEmpty()) {
            return null;
        }
        try {
            return getEmotionsFromMCRSFT(new URI(url));
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public List<UserEmotionDto> loadEmotionsByImage(MultipartFile file) {
        if(DEBUG_MODE) {
            return getDebugEmotions();
        }
        return file.isEmpty() ? null : getEmotionsFromMCRSFT(file);
    }

    @Override
    public List<UserEmotionDto> loadEmotionsByBase64Data(String imageBase64) {
        if(DEBUG_MODE) {
            return getDebugEmotions();
        }
        if(imageBase64.isEmpty()) {
            return null;
        }
        byte[] bytes = Base64.decodeBase64(imageBase64);
        return getEmotionsFromMCRSFT(bytes);
    }

    @Override
    public String serializeUserEmotions(List<UserEmotionDto> emotions) {
        return emotions.stream()
                .map(emotion -> String.join(VALUE_DELIMETER, emotion.getType().getShortCode(), String.valueOf(emotion.getValue().intValue())))
                .collect(Collectors.joining(EMOTION_DELIMETER));
    }

    @Override
    public List<UserEmotionDto> deserializeUserEmotions(String emotions) {

        return Stream.of(emotions.split(EMOTION_DELIMETER)).map(str -> {
            String[] codeAndValue = str.split(VALUE_DELIMETER);
            return new UserEmotionDto(EmotionTypes.of(codeAndValue[0]), Integer.parseInt(codeAndValue[1]));
        }).collect(Collectors.toList());
    }

    private List<UserEmotionDto> getDebugEmotions() {
        try {
            return parseJson(FAKE_JSON);
        } catch (IOException e) {
            return null;
        }
    }

    private List<UserEmotionDto> getEmotionsFromMCRSFT(URI url) {
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(MCRSFT_REC_SERVICE_URL);

            HttpPost request = new HttpPost(builder.build());
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", MCRSFT_REC_SERVICE_TOKEN);

            StringEntity reqEntity = new StringEntity("{ \"url\":\"" + url + "\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return parseJson(EntityUtils.toString(entity));
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<UserEmotionDto> getEmotionsFromMCRSFT(MultipartFile file) {

        byte[] bytes;

        try {
            InputStream inputStream = file.getInputStream();
            bytes = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            return null;
        }

        return getEmotionsFromMCRSFT(bytes);
    }

    private List<UserEmotionDto> getEmotionsFromMCRSFT(byte[] bytes) {

        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(MCRSFT_REC_SERVICE_URL);

            HttpPost request = new HttpPost(builder.build());
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", MCRSFT_REC_SERVICE_TOKEN);

            ByteArrayEntity reqEntity = new ByteArrayEntity(bytes);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return parseJson(EntityUtils.toString(entity));
            }

        } catch (IOException | URISyntaxException e) {
            return null;
        }
        return null;
    }

    private List<UserEmotionDto> parseJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(json);
        if(root == null) return null;

        JsonNode face1 = root.get(0);
        if(face1 == null) return null;

        JsonNode scores = face1.path("scores");
        List<UserEmotionDto> result = new ArrayList<>();
        for (EmotionTypes emoType : EmotionTypes.values()) {
            int value = (int)(scores.path(emoType.getCode()).floatValue() * 100);
            // Percentage value should be greater than 10
            if (value >= 5) {
                result.add(new UserEmotionDto(emoType, value));
            }
        }
        return result;
    }
}