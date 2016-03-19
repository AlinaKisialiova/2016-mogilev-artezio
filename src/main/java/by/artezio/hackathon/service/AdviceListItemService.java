package by.artezio.hackathon.service;

import by.artezio.hackathon.model.AdviceListItem;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface AdviceListItemService {

    void completeItem(Long id);

    void completeItems(Iterable<Long> ids);

    AdviceListItem findById(Long id);

    AdviceListItem saveOrUpdate(AdviceListItem item);
}
