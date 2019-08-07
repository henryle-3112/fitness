package henry.greenwich.fitness.controller.notification;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.notification.Notification;
import henry.greenwich.fitness.service.notification.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("notification-management")
public class NotificationController {
    private NotificationService notificationService;

    /**
     * @param notificationService - inject notificationService
     */
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get
     *                      notifications (this parameter could be optional)
     * @param search        - notification's content's keywords that user want to
     *                      get notifications (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of notifications
     */
    @GetMapping(value = "/users/{userProfileId}/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Notification> getNotificationsPaging(HttpServletResponse response, @PathVariable Integer userProfileId,
            @RequestParam(required = false) Integer page, @RequestParam(required = false) String search) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nNotifications = this.notificationService.getNumberOfNotifications(userProfileId, search);
            response.addHeader("X-Total-Count", String.valueOf(nNotifications));
            response.addHeader("X-Total-Page", String.valueOf(nNotifications / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.notificationService.getNotificationsPaging(userProfileId, search, startIndex);
        }
        return this.notificationService.getNotifications(userProfileId, search);
    }

    /**
     * @param notification - notification
     * @return inserted notification
     */
    @PostMapping(value = "/notifications", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Notification addNotification(@RequestBody Notification notification) {
        return this.notificationService.addNotification(notification);
    }
}
