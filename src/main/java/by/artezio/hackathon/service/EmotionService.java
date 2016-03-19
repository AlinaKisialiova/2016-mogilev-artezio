package by.artezio.hackathon.service;

import by.artezio.hackathon.service.dto.UserEmotionDto;

import java.util.List;

public interface EmotionService {

    List<UserEmotionDto> loadEmotionsByUrl(String url);

}
