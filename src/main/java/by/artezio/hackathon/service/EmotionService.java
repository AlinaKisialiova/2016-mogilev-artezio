package by.artezio.hackathon.service;

import by.artezio.hackathon.service.dto.UserEmotionDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmotionService {

    List<UserEmotionDto> loadEmotionsByUrl(String url);

    List<UserEmotionDto> loadEmotionsByImage(MultipartFile file);

    List<UserEmotionDto> loadEmotionsByBase64Data(String imageBase64);

    String serializeUserEmotions(List<UserEmotionDto> emotions);

    List<UserEmotionDto> deserializeUserEmotions(String emotions);

}