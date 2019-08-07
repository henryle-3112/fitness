package henry.greenwich.fitness.controller.notification;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.notification.CoachMembershipNotification;
import henry.greenwich.fitness.service.notification.CoachMembershipNotificationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("notification-management")
public class CoachMembershipNotificationController {
    private CoachMembershipNotificationService coachMembershipNotificationService;

    /**
     * @param coachMembershipNotificationService - inject
     *                                           coachMembershipNotificationService
     */
    public CoachMembershipNotificationController(
            CoachMembershipNotificationService coachMembershipNotificationService) {
        this.coachMembershipNotificationService = coachMembershipNotificationService;
    }

    /**
     * @param coachId - coach's id that user want to get coach membership
     *                notifications (this parameter could be optional)
     * @param search  - notifications' content's keywords that user want to get
     *                coach membership notifications (this parameter could be
     *                optional)
     * @param page    - start index (for pagination) (this parameter could be
     *                optional)
     * @return list of coach membership notifications
     */
    @GetMapping(value = "/coaches/{coachId}/trainings", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<CoachMembershipNotification> getCoachMembershipNotificationForCoach(HttpServletResponse response,
            @PathVariable Integer coachId, @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String search) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nCoachMembershipNotifications = this.coachMembershipNotificationService
                    .getNumberOfCoachMembershipNotifications(coachId, null, search);
            response.addHeader("X-Total-Count", String.valueOf(nCoachMembershipNotifications));
            response.addHeader("X-Total-Page",
                    String.valueOf(nCoachMembershipNotifications / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.coachMembershipNotificationService.getCoachMembershipNotificationsPaging(coachId, null, search,
                    startIndex);
        }
        return this.coachMembershipNotificationService.getCoachMembershipNotifications(coachId, null, search);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach
     *                      memberships notifications (this parameter could be
     *                      optional)
     * @param search        - notifications' content's keywords that user want to
     *                      get coach membership notifications (this parameter could
     *                      be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of coach membership notifications
     */
    @GetMapping(value = "/users/{userProfileId}/trainings", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<CoachMembershipNotification> getCoachMembershipNotificationForUser(HttpServletResponse response,
            @PathVariable Integer userProfileId, @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String search) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nCoachMembershipNotifications = this.coachMembershipNotificationService
                    .getNumberOfCoachMembershipNotifications(null, userProfileId, search);
            response.addHeader("X-Total-Count", String.valueOf(nCoachMembershipNotifications));
            response.addHeader("X-Total-Page",
                    String.valueOf(nCoachMembershipNotifications / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.coachMembershipNotificationService.getCoachMembershipNotificationsPaging(null, userProfileId,
                    search, startIndex);
        }
        return this.coachMembershipNotificationService.getCoachMembershipNotifications(null, userProfileId, search);
    }

    /**
     * @param coachMembershipNotification - coach membership notification that user
     *                                    want to add to the database
     * @return inserted coach membership notification
     */
    @PostMapping(value = "/trainings", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public CoachMembershipNotification addCoachMembershipNotification(
            @RequestBody CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationService.addCoachMembershipNotification(coachMembershipNotification);
    }

    /**
     * @param coachMembershipNotification - coach membership notification that user
     *                                    want to update to the database
     * @return updated coach membership notification
     */
    @PutMapping(value = "/trainings", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public CoachMembershipNotification updateCoachMembershipNotification(
            @RequestBody CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationService.updateCoachMembershipNotification(coachMembershipNotification);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach
     *                      membership notification
     * @param coachId       - coach's id that user want to get coach membership
     *                      notification
     * @param status        - status of coach membership notification that user want
     *                      to get coach membership notification
     * @return selected coach membership notification
     */
    @GetMapping(value = "/coach/{coachId}/users/{userProfileId}/trainings", produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoachAndStatus(
            @PathVariable Integer userProfileId, @PathVariable Integer coachId,
            @RequestParam(required = false) Integer status) {
        if (status != null) {
            return this.coachMembershipNotificationService
                    .findCoachMembershipNotificationByUserProfileAndCoachAndStatus(userProfileId, coachId, status);
        }
        return this.coachMembershipNotificationService
                .findCoachMembershipNotificationByUserProfileAndCoach(userProfileId, coachId);
    }

}
