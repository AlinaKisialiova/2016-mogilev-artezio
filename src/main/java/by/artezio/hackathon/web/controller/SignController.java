package by.artezio.hackathon.web.controller;

import by.artezio.hackathon.model.enumeration.GenderEnum;
import by.artezio.hackathon.service.UserService;
import by.artezio.hackathon.service.dto.UserRegistrationDto;
import by.artezio.hackathon.util.security.SecurityUtils;
import by.artezio.hackathon.web.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping(path = "/")
public class SignController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userRegistrationValidator);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(@RequestParam(name = "error", required = false)Optional<String> error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("currentUser", SecurityUtils.getCurrentUserDetails());
        return "index";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String signUpGet(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        model.addAttribute("genders", GenderEnum.values());
        return "registration";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") UserRegistrationDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genders", GenderEnum.values());
            return "registration";
        } else {
            userService.register(dto);
            return "redirect:/";
        }
    }
}
