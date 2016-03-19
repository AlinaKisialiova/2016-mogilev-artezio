package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.AdviceListRepository;
import by.artezio.hackathon.service.AdviceListService;
import by.artezio.hackathon.service.dto.HistoryTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Service
@Transactional
public class AdviceListServiceImpl implements AdviceListService {

    @Autowired
    private AdviceListRepository adviceListRepository;

    @Override
    public AdviceList findActiveList(User currentUser) {
        return adviceListRepository.findByUserIdAndEndDateIsNull(currentUser.getId());
    }

    @Override
    public Page<HistoryTaskDto> getHistoryTasks(User user, Pageable pageable) {
        //todo implement me
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }
}
