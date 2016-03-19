package by.artezio.hackathon.web.controller;
import by.artezio.hackathon.service.AdviceListService;
import by.artezio.hackathon.service.dto.HistoryTaskDto;
import by.artezio.hackathon.util.security.SecurityUtils;
import by.artezio.hackathon.util.security.UserDetails;
import org.omg.CORBA.Object;
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

    @ModelAttribute("currentUser")
    private UserDetails currentUser() {
        return SecurityUtils.getCurrentUserDetails();
    }

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public String viewHistoryList(Model model, @PathVariable("pageNumber") int pageNumber){
        if(Objects.nonNull(currentUser())) {
            Page<HistoryTaskDto> adviceListPage = adviceListService.getTaskHistory(SecurityUtils.getCurrentUser(),
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
        else {
            return "redirect:/";
        }
    }

    @RequestMapping(path = "/view/{id}",  method = RequestMethod.GET)
    public String viewHistoryTask () {
            return "complete_task";
    }
}
