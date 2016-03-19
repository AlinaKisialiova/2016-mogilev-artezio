package by.artezio.hackathon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/task")
public class TaskController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index() {
        return "task";
    }

}
