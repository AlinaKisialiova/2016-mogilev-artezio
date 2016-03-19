package by.artezio.hackathon.web.controller;
import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.service.AdviceListService;
import by.artezio.hackathon.service.EmotionService;
import by.artezio.hackathon.service.dto.HistoryTaskDto;
import by.artezio.hackathon.util.security.SecurityUtils;
import by.artezio.hackathon.util.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;


/**
 * Created by rezerv on 19.03.2016.
 */
@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private AdviceListService adviceListService;

    @Autowired
    private EmotionService emotionService;

    @ModelAttribute("currentUser")
    private UserDetails currentUser() {
        return SecurityUtils.getCurrentUserDetails();
    }

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public String viewHistoryList(Model model, @PathVariable("pageNumber") int pageNumber) {
        Page<HistoryTaskDto> adviceListPage = adviceListService.getHistoryTasks(SecurityUtils.getCurrentUser(),
                new PageRequest(pageNumber - 1, 10));
        model.addAttribute("historyTasks", adviceListPage);
        int current = adviceListPage.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = adviceListPage.getTotalPages() != 0 ? Math.min(begin + 10, adviceListPage.getTotalPages()) : 1;
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "history";
    }

    @RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
    public String viewHistoryTask(@PathVariable("id") Long id, Model model) {
        AdviceList adviceList = adviceListService.findById(id);
        if (Objects.nonNull(adviceList) && Objects.nonNull(adviceList.getEndDate())) {
            model.addAttribute("historyAdviceList", adviceList);
            model.addAttribute("emotions", emotionService.deserializeUserEmotions(adviceList.getCurrentEmotion()));
            return "complete_task";
        }
        return "redirect:/progress";
    }

}
