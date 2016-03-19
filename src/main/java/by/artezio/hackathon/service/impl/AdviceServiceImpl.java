package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.repository.AdviceRepository;
import by.artezio.hackathon.service.AdviceSearchStrategy;
import by.artezio.hackathon.service.AdviceService;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Service
@Transactional
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceSearchStrategy adviceSearchStrategy;

    @Override
    public List<Advice> findByEmotions(List<UserEmotionDto> userEmotions) {
        return adviceSearchStrategy.search(userEmotions);
    }
}
