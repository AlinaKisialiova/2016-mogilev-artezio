package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.service.EmotionService;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import by.artezio.hackathon.service.dto.enumeration.EmotionTypes;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmotionServiceImpl implements EmotionService {

    private static final String FAKE_JSON = "[" +
            "{\"faceRectangle\":{\"height\":362,\"left\":200,\"top\":259,\"width\":362}," +
            "\"scores\":{\"anger\":0.842028,\"contempt\":0.004918335,\"disgust\":0.147966027," +
            "\"fear\":9.70716646E-05,\"happiness\":4.799247E-06,\"neutral\":0.00493922364," +
            "\"sadness\":3.30198527E-05,\"surprise\":1.34937009E-05}}]";

    private static boolean DEBUG_MODE = false;

    @Override
    public List<UserEmotionDto> loadEmotionsByUrl(String url) {
        if(DEBUG_MODE) {
            return getDebugEmotions();
        }
        if(url.isEmpty()) {
            return null;
        }
        return null;
    }

    private List<UserEmotionDto> getDebugEmotions() {
        try {
            return parseJson(FAKE_JSON);
        } catch (IOException e) {
            return null;
        }
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
