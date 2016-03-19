package by.artezio.hackathon.service;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.service.dto.ActiveTaskDto;
import by.artezio.hackathon.service.dto.HistoryTaskDto;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface AdviceListService {

    AdviceList findById(Long id);

    AdviceList findActiveList(User user);

    ActiveTaskDto findActiveTaskPreview(User user);

    AdviceList createAdviceList(List<Integer> selected, List<Advice> adviceList,
                                List<UserEmotionDto> emotions, User user);

    Page<HistoryTaskDto> getHistoryTasks(User user, Pageable pageable);

    AdviceList saveOrUpdate(AdviceList adviceList);

    AdviceList completeAndFinish(User user, List<Long> adviceIds);

    AdviceList finish(User user);

}

