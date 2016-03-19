package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.AdviceListItem;
import by.artezio.hackathon.repository.AdviceListItemRepository;
import by.artezio.hackathon.service.AdviceListItemService;
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

    @Override
    public void completeItem(Long id) {
        AdviceListItem adviceListItem = findById(id);
        adviceListItem.setEndDate(new Date());
        adviceListItem.setComplete(Boolean.TRUE);
        saveOrUpdate(adviceListItem);
    }

    @Override
    public void completeItems(Iterable<Long> ids) {
        for (Long id : ids) {
            completeItem(id);
        }
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
