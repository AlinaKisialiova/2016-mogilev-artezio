package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.model.AdviceListItem;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.AdviceListItemRepository;
import by.artezio.hackathon.service.AdviceListItemService;
import by.artezio.hackathon.service.AdviceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Service
@Transactional
public class AdviceListItemServiceImpl implements AdviceListItemService {

    @Autowired
    private AdviceListItemRepository adviceListItemRepository;

    @Autowired
    private AdviceListService adviceListService;

    @Override
    public void complete(Long id) {
        AdviceListItem adviceListItem = findById(id);
        adviceListItem.setEndDate(new Date());
        adviceListItem.setComplete(Boolean.TRUE);
        saveOrUpdate(adviceListItem);
    }

    @Override
    public AdviceList complete(Iterable<Long> ids, User user) {
        for (Long id : ids) {
            complete(id);
        }
        AdviceList adviceList = adviceListService.findActiveList(user);
        if (adviceList.getItems().stream().filter(AdviceListItem::getComplete).count() == adviceList.getItems().size()) {
            return adviceListService.finish(user);
        }
        return adviceList;
    }

    @Override
    public AdviceListItem findById(Long id) {
        return adviceListItemRepository.findOne(id);
    }

    @Override
    public AdviceListItem saveOrUpdate(AdviceListItem item) {
        return adviceListItemRepository.save(item);
    }
}
