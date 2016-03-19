package by.artezio.hackathon.service;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.service.dto.UserEmotionDto;

import java.util.List;

/**
 * Created by Roman Savoskin on 19.03.2016.
 */
public interface AdviceSearchStrategy {

    List<Advice> search(List<UserEmotionDto> userEmotions);
}
