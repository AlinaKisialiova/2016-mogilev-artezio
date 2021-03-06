package by.artezio.hackathon.service;

import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.model.AdviceListItem;
import by.artezio.hackathon.model.User;

import java.util.List;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface AdviceListItemService {

    void complete(Long id);

    AdviceList complete(List<Long> ids, User user);

    AdviceListItem findById(Long id);

    AdviceListItem saveOrUpdate(AdviceListItem item);
}
