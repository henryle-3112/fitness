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
     * @param response      - response to add number of pages and number of
     *                      notifications to header
     * @param userProfileId - user's profile's id that user want to get
     *                      notifications (this parameter could be optional)
     * @param search        - notification's content's keywords that user want to
     *                      get notifications (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @param status        - notification's status that user want to get
     *                      notifications (this parameter could be optional)
     * @return list of notifications
     */
    @GetMapping(value = "/users/{userProfileId}/notifications", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Notification> getNotifications(HttpServletResponse response,
                                               @PathVariable Integer userProfileId,
                                               @RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) String search,
                                               @RequestParam(required = false) Integer status) {
        if (page != null) {
            return this.getNotificationsPaging(response, userProfileId, page, search, status);
        }
        return this.notificationService.getNotifications(userProfileId, search, status);
    }

    /**
     * @param response      - response to add number of pages and number of
     *                      notifications to header
     * @param userProfileId - user's profile's id that user want to get
     *                      notifications (this parameter could be optional)
     * @param search        - notification's content's keywords that user want to
     *                      get notifications (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of notifications
     */
    private List<Notification> getNotificationsPaging(HttpServletResponse response,
                                                      Integer userProfileId,
                                                      Integer page,
                                                      String search,
                                                      Integer status) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nNotifications = this.notificationService.getNumberOfNotifications(userProfileId, search, status);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nNotifications));
        int nPages = nNotifications > 0 ? (nNotifications >= Constants.NUMBER_ITEMS_PER_PAGE ? nNotifications / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.notificationService.getNotificationsPaging(userProfileId, search, status, startIndex - 1);
    }

    /**
     * @param notification - notification
     * @return inserted notification
     */
    @PostMapping(value = "/notifications", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Notification addNotification(@RequestBody Notification notification) {
        return this.notificationService.addNotification(notification);
    }
}
