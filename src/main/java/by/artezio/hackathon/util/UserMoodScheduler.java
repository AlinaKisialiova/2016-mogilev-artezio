package by.artezio.hackathon.util;

import by.artezio.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Component
public class UserMoodScheduler {

    @Autowired
    private UserService userService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 */1 * * *")
    public void decreaseUsersMood() {
        userService.decreaseUsersMood();
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
