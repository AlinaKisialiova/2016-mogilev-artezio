package by.artezio.hackathon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SignController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String signUpGet() {
        return "registration";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String signUpPost() {
        return "registration";
    }
}
