package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.model.AdviceListItem;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.AdviceListRepository;
import by.artezio.hackathon.service.*;
import by.artezio.hackathon.service.dto.ActiveTaskDto;
import by.artezio.hackathon.service.dto.HistoryTaskDto;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import by.artezio.hackathon.service.dto.enumeration.EmotionTypes;
import by.artezio.hackathon.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Service
@Transactional
public class AdviceListServiceImpl implements AdviceListService {

    @Autowired
    private AdviceListRepository adviceListRepository;

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private AdviceListItemService adviceListItemService;

    @Autowired
    private UserAdviceService userAdviceService;

    @Autowired
    private UserService userService;

    @Override
    public AdviceList findById(Long id) {
        return adviceListRepository.findOne(id);
    }

    @Override
    public AdviceList findActiveList(User currentUser) {
        return adviceListRepository.findByUserIdAndEndDateIsNull(currentUser.getId());
    }

    @Override
    public ActiveTaskDto findActiveTaskPreview(User user) {
        AdviceList actvList = adviceListRepository.findByUserIdAndEndDateIsNull(user.getId());
        if(actvList == null) {
            return null;
        }

        ActiveTaskDto activeTaskDto = new ActiveTaskDto();
        activeTaskDto.setEmotions(emotionService.deserializeUserEmotions(actvList.getCurrentEmotion()));

        List<AdviceListItem> items = actvList.getItems();
        List<Boolean> booleanList = new ArrayList<>();
        for (AdviceListItem item : items) {
            booleanList.add(item.getComplete());
        }
        activeTaskDto.setItemComplete(booleanList);

        return activeTaskDto;
    }

    @Override
    public AdviceList createAdviceList(List<Integer> selected, List<Advice> adviceList, List<UserEmotionDto> emotions,
                                       User user) {
        List<Advice> selectedAdvice = selected.stream().map(adviceList::get).collect(Collectors.toList());
        AdviceList list = new AdviceList();
        list.setCreateDate(new Date());
        list.setCurrentEmotion(emotionService.serializeUserEmotions(emotions));
        list.setUser(user);
        list = saveOrUpdate(list);

        for (Advice advice : selectedAdvice) {
            AdviceListItem item = new AdviceListItem();
            item.setUserAdvice(userAdviceService.findOrCreate(advice.getId(), user));
            item.setAdviceList(list);
            item.setComplete(false);
            adviceListItemService.saveOrUpdate(item);
        }

        return saveOrUpdate(list);
    }

    @Override
    public void complete(User user, List<Long> adviceIds) {
        AdviceList list = findActiveList(user);
        list.setEndDate(new Date());
        if (adviceIds != null && !adviceIds.isEmpty()) {
            adviceListItemService.completeItems(adviceIds);
        }
        //TODO userService.updateMood(user, list.getItems());
        saveOrUpdate(list);
    }

    @Override
    public Page<HistoryTaskDto> getHistoryTasks(User user, Pageable pageable) {
        Page<AdviceList> tasks = adviceListRepository.findByUserIdAndEndDateIsNotNullOrderByEndDateDesc(user.getId(), pageable);
        List<HistoryTaskDto> historyTaskDtos = Collections.emptyList();
        if(Objects.nonNull(tasks) && !tasks.getContent().isEmpty()) {
            historyTaskDtos  = tasks.getContent().stream().map(this::convertAdviceListToHistoryTaskDto).collect(Collectors.toList());
        }
        return new PageImpl<>(historyTaskDtos, pageable, tasks.getTotalElements());
    }

    private HistoryTaskDto convertAdviceListToHistoryTaskDto(AdviceList adviceList) {
        HistoryTaskDto task = new HistoryTaskDto();
        task.setId(adviceList.getId());
        task.setEndDate(DateFormatter.formatDate(adviceList.getEndDate()));
        List<EmotionTypes> emotionList = emotionService.deserializeUserEmotions(adviceList.getCurrentEmotion()).stream().map(UserEmotionDto::getType)
                .collect(Collectors.toList());
        task.setEmotions(emotionList.stream().map(EmotionTypes::getTitle).collect(Collectors.toList()));
        task.setItemComplete(adviceList.getItems().stream().map(AdviceListItem::getComplete).collect(Collectors.toList()));
        return task;
    }

    @Override
    public AdviceList saveOrUpdate(AdviceList adviceList) {
        return adviceListRepository.save(adviceList);
    }
}
