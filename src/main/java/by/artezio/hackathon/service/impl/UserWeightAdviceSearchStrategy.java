package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.repository.AdviceRepository;
import by.artezio.hackathon.service.AdviceSearchStrategy;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import by.artezio.hackathon.service.dto.enumeration.EmotionTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserWeightAdviceSearchStrategy implements AdviceSearchStrategy {

    private static class EmotionAdviceList implements Comparable<EmotionAdviceList> {
        int finalCount;
        EmotionTypes emotion;
        List<Advice> adviceList;

        @Override
        public int compareTo(EmotionAdviceList o) {
            return finalCount - o.finalCount;
        }
    }

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public List<Advice> search(List<UserEmotionDto> userEmotions) {

        List<Advice> adviceList = new ArrayList<>();

        // Поиск всех советов для каждой из указканных эмоций
        userEmotions.stream()
                .map(userEmotion -> {
                    EmotionAdviceList emotionAdviceList = new EmotionAdviceList();
                    emotionAdviceList.emotion = userEmotion.getType();
                    emotionAdviceList.finalCount = userEmotion.getValue() / 10;
                    emotionAdviceList.adviceList = adviceRepository.findByEmotion(userEmotion.getType().getShortCode());
                    return emotionAdviceList;
                }).forEach(emotionAdvice -> {
                    for(int i = 0; i < Math.min(emotionAdvice.finalCount, emotionAdvice.adviceList.size()); ++i) {
                        adviceList.add(emotionAdvice.adviceList.get(i));
                    }
                });

        return adviceList;
    }
}
