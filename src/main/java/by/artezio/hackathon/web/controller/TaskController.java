package by.artezio.hackathon.web.controller;

import by.artezio.hackathon.service.EmotionService;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import by.artezio.hackathon.util.security.SecurityUtils;
import by.artezio.hackathon.util.security.UserDetails;
import by.artezio.hackathon.web.form.UploadImageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private EmotionService emotionService;

    @ModelAttribute("currentUser")
    private UserDetails currentUser() {
        return SecurityUtils.getCurrentUserDetails();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index() {
        return "task";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String buildPreliminaryList(Model model, UploadImageForm form) {

        MultipartFile image = form.getImage();
        String imageUrl = form.getImageUrl();
        String imageBase64 = form.getImageBase64();

        List<UserEmotionDto> emotions;
        if(image.isEmpty()) {
            if(imageUrl.isEmpty()) {
                if(imageBase64.isEmpty()) {
                    return "redirect:/task";
                } else {
                    emotions = emotionService.loadEmotionsByBase64Data(imageBase64);
                }
            } else {
                emotions = emotionService.loadEmotionsByUrl(imageUrl);
            }
        } else {
            emotions = emotionService.loadEmotionsByImage(image);
        }

        if(emotions == null) {
            return "redirect:/task";
        }
        model.addAttribute("emotions", emotions);
        return "redirect:/task/take";
    }

}
