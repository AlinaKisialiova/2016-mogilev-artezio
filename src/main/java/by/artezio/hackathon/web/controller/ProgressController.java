package by.artezio.hackathon.web.controller;

import by.artezio.hackathon.service.AdviceListService;
import by.artezio.hackathon.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Controller
@RequestMapping(path = "/progress")
public class ProgressController {

    @Autowired
    private AdviceListService adviceListService;

    @ModelAttribute("currentUser")
    public UserDetails currentUser() {
        return SecurityUtils.getCurrentUserDetails();
    }

    @RequestMapping(path = "")
    public String index(Model model) {
        model.addAttribute("activeTask", adviceListService.findActiveList(SecurityUtils.getCurrentUser()));
        model.addAttribute("historyTasks", adviceListService.getHistoryTasks(SecurityUtils.getCurrentUser(),
                new PageRequest(0, 5)));
        return "progress";
    }
}
