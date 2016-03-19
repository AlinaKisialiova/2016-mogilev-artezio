package by.artezio.hackathon.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by rezerv on 19.03.2016.
 */
@Controller
@RequestMapping("/history")
public class HistoryController {

    @RequestMapping(path = "/view/{id}",  method = RequestMethod.GET)
    public String viewHistoryTask () {
            return "complete_task";
    }
}
