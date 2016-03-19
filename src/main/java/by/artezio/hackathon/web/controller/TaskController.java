package by.artezio.hackathon.web.controller;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.model.AdviceList;
import by.artezio.hackathon.service.AdviceListItemService;
import by.artezio.hackathon.service.AdviceListService;
import by.artezio.hackathon.service.AdviceService;
import by.artezio.hackathon.service.EmotionService;
import by.artezio.hackathon.service.dto.UploadImageFormDto;
import by.artezio.hackathon.service.dto.UserEmotionDto;
import by.artezio.hackathon.service.dto.UserPhotoDto;
import by.artezio.hackathon.util.security.SecurityUtils;
import by.artezio.hackathon.util.security.UserDetails;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes(names = {"emotions", "adviceList", "photo"})
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private AdviceListService adviceListService;

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private AdviceListItemService adviceListItemService;

    @ModelAttribute("currentUser")
    private UserDetails currentUser() {
        return SecurityUtils.getCurrentUserDetails();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index() {
        return "task";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String buildPreliminaryList(Model model, UploadImageFormDto form) {

        MultipartFile image = form.getImage();
        String imageUrl = form.getImageUrl();
        String imageBase64 = form.getImageBase64();
        UserPhotoDto photo;
        List<UserEmotionDto> emotions;
        if (image == null || image.isEmpty()) {
            if (imageUrl == null || imageUrl.isEmpty()) {
                if (imageBase64 == null || imageBase64.isEmpty()) {
                    return "redirect:/task";
                } else {
                    photo = emotionService.loadEmotionsByBase64Data(imageBase64);
                }
            } else {
                photo = emotionService.loadEmotionsByUrl(imageUrl);
            }
        } else {
            photo = emotionService.loadEmotionsByImage(image);
        }

        if(photo == null) {
            return "redirect:/task";
        }
        model.addAttribute("emotions", photo.getEmotions());
        model.addAttribute("photo", "data:image/jpeg;base64," + Base64.encodeBase64String(photo.getPhoto()));
        model.addAttribute("adviceList", adviceService.findByEmotions(photo.getEmotions()));
        return "redirect:/task/take";
    }

    @RequestMapping(path = "/take", method = RequestMethod.GET)
    public String take(Model model, @ModelAttribute("adviceList") List<Advice> adviceList,
                       @ModelAttribute("emotions") List<UserEmotionDto> emotions, @ModelAttribute("photo") byte[] photo) {
        return "task_take";
    }

    @RequestMapping(path = "/take", method = RequestMethod.POST)
    public String takePost(@RequestParam(value = "adviceIds") List<Integer> adviceIds,
                           @ModelAttribute("adviceList") List<Advice> adviceList,
                           @ModelAttribute("emotions") List<UserEmotionDto> emotions) {
        if(Objects.isNull(adviceIds) || adviceIds.isEmpty()) {
            return "redirect:/task";
        }

        adviceListService.createAdviceList(adviceIds, adviceList, emotions, SecurityUtils.getCurrentUser());
        return "redirect:/task/manage";
    }

    @RequestMapping(path = "/manage",  method = RequestMethod.GET)
    public String manage(Model model) {
        AdviceList activeList = adviceListService.findActiveList(SecurityUtils.getCurrentUser());

        if (activeList == null) {
            return "redirect:/task";
        }

        model.addAttribute("adviceList", activeList);
        model.addAttribute("emotions", emotionService.deserializeUserEmotions(activeList.getCurrentEmotion()));

        return "task_manage";
    }

    @RequestMapping(path = "/manage",  method = RequestMethod.POST)
    public String managePost(@RequestParam(value = "adviceIds", required = false) List<Long> adviceIds,
                             @RequestParam("action") String action) {
        AdviceList lastList = null;
        if ("complete".equals(action)) {
            lastList = adviceListService.completeAndFinish(SecurityUtils.getCurrentUser(), adviceIds);
        } else {
            lastList = adviceListItemService.complete(adviceIds, SecurityUtils.getCurrentUser());
        }
        if (lastList.getEndDate() != null) {
            return "redirect:/progress?score=" + lastList.getScores();
        }
        return "redirect:/task/manage";

    }

}
