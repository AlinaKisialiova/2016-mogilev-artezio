package by.artezio.hackathon.service;

import by.artezio.hackathon.model.Advice;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface AdviceService {

    List<Advice> findByEmotion(String emotion);
}
