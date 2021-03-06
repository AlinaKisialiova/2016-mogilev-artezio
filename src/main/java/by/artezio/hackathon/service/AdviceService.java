package by.artezio.hackathon.service;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.service.dto.UserEmotionDto;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface AdviceService {

    List<Advice> findByEmotions(List<UserEmotionDto> userEmotions);
}
