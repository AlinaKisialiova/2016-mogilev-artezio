package by.artezio.hackathon.service;

import by.artezio.hackathon.service.dto.UserEmotionDto;
import by.artezio.hackathon.service.dto.UserPhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmotionService {

    UserPhotoDto loadEmotionsByUrl(String url);

    UserPhotoDto loadEmotionsByImage(MultipartFile file);

    UserPhotoDto loadEmotionsByBase64Data(String imageBase64);

    String serializeUserEmotions(List<UserEmotionDto> emotions);

    List<UserEmotionDto> deserializeUserEmotions(String emotions);

}