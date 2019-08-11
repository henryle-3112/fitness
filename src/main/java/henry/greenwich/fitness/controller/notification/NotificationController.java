package henry.greenwich.fitness.controller.notification;

import henry.greenwich.fitness.model.notification.Notification;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.notification.NotificationService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class NotificationController {
    /**
     * notificationService - inject notificationService
     * userProfileService - inject userProfileService
     */
    private NotificationService notificationService;
    private UserProfileService userProfileService;

    /**
     * @param notificationService - inject notificationService
     * @param userProfileService  - inject userProfileService
     */
    public NotificationController(NotificationService notificationService,
                                  UserProfileService userProfileService) {
        this.notificationService = notificationService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param userProfileId - user's profile's id
     * @param page          - page
     * @param keyword       - keyword
     * @return list of notifications
     */
    @GetMapping(value = "/notifications/paging/{userProfileId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Notification> getNotificationsByPage(
            @PathVariable int userProfileId, @PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Notification> notifications = new ArrayList<>();
        List<Object> notificationsObject = this.notificationService
                .getNotificationsByUserProfileAndByPage(userProfileId, selectedKeyWord, startIndex - 1);
        for (int i = 0; i < notificationsObject.size(); i++) {
            Object[] eachNotificationObject = (Object[]) notificationsObject.get(i);
            int id = (int) eachNotificationObject[0];
            String content = (String) eachNotificationObject[1];
            Date createdDate = (Date) eachNotificationObject[2];
            int status = (int) eachNotificationObject[3];
            // get selected user's profile
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            // create notification object
            Notification notification = new Notification(
                    (long) id,
                    content,
                    userProfile,
                    createdDate,
                    status
            );
            notifications.add(notification);
        }
        return notifications;
    }

    /**
     * @param keyword - number of notifications based on keywords
     * @return number of notifications
     */
    @GetMapping(value = "/notifications/count/{userProfileId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNotifications(
            @PathVariable int userProfileId, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countNotificationsObject = this.notificationService
                .getNumberOfNotificationsByUserProfile(userProfileId, selectedKeyWord);
        Object eachCountNotification = countNotificationsObject.get(0);
        return new ResponseMessage(eachCountNotification.toString());
    }

    /**
     *
     * @param notification - notification
     * @return inserted notification
     */
    @PostMapping(value = "/notifications/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Notification addNotification(@RequestBody  Notification notification) {
        return this.notificationService.addNotification(notification);
    }
}
