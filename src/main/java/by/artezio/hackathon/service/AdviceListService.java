package by.artezio.hackathon.service;

import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.service.dto.HistoryTaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface AdviceListService {
    AdviceList findActiveList(User currentUser);


    Page<HistoryTaskDto> getHistoryTasks(User user, Pageable pageable);
}
