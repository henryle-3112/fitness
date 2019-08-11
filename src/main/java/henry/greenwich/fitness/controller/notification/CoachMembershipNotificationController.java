package henry.greenwich.fitness.controller.notification;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.notification.CoachMembershipNotification;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.notification.CoachMembershipNotificationService;
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
public class CoachMembershipNotificationController {
    /**
     * coachMembershipNotificationService - interact with coach membership notification data
     * coachService - interact with coach's data
     * userProfileService - interact with user's profile's data
     */
    private CoachMembershipNotificationService coachMembershipNotificationService;
    private CoachService coachService;
    private UserProfileService userProfileService;

    /**
     * @param coachMembershipNotificationService - inject coachMembershipNotificationService
     * @param coachService                       - inject coachService
     * @param userProfileService                 - inject userProfileService
     */
    public CoachMembershipNotificationController(CoachMembershipNotificationService coachMembershipNotificationService,
                                                 CoachService coachService,
                                                 UserProfileService userProfileService) {
        this.coachMembershipNotificationService = coachMembershipNotificationService;
        this.coachService = coachService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param coachId - coach id
     * @param page    - page
     * @param keyword - keyword
     * @return list of notifications
     */
    @GetMapping(value = "/coach/notifications/paging/{coachId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CoachMembershipNotification> findCoachMembershipNotificationsByCoachIdAndByKeywordAndByPage(
            @PathVariable int coachId, @PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<CoachMembershipNotification> coachMembershipNotifications = new ArrayList<>();
        List<Object> coachMembershipNotificationObject = this.coachMembershipNotificationService
                .findCoachMembershipNotificationsByCoachIdAndByKeywordAndByPage(coachId, selectedKeyWord, startIndex - 1);
        for (Object o : coachMembershipNotificationObject) {
            Object[] eachCoachMembershipNotificationObject = (Object[]) o;
            int id = (int) eachCoachMembershipNotificationObject[0];
            String content = (String) eachCoachMembershipNotificationObject[1];
            int userProfileId = (int) eachCoachMembershipNotificationObject[2];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            Coach coach = this.coachService.getCoachById((long) coachId);
            int status = (int) eachCoachMembershipNotificationObject[4];
            Date createdDate = (Date) eachCoachMembershipNotificationObject[5];
            CoachMembershipNotification coachMembershipNotification = new CoachMembershipNotification(
                    (long) id,
                    userProfile,
                    coach,
                    status,
                    content,
                    createdDate
            );
            coachMembershipNotifications.add(coachMembershipNotification);
        }
        return coachMembershipNotifications;
    }

    /**
     * @param coachId - coach's id
     * @param keyword - keyword
     * @return number of notifications
     */
    @GetMapping(value = "/coach/notifications/count/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachMembershipNotificationsByCoachIdAndByKeyword(
            @PathVariable int coachId,
            @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countCoachMembershipNotificationsObject = this.coachMembershipNotificationService
                .countCoachMembershipNotificationsByCoachIdAndByKeyword(coachId, selectedKeyWord);
        Object eachCountMembershipNotificationObject = countCoachMembershipNotificationsObject.get(0);
        return new ResponseMessage(eachCountMembershipNotificationObject.toString());
    }

    /**
     * @param userProfileId - user's profile's id
     * @param page          - page
     * @param keyword       - keyword
     * @return list of notifications
     */
    @GetMapping(value = "/membership/notifications/paging/{userProfileId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CoachMembershipNotification> findCoachMembershipNotificationsByUserProfileIdAndByKeywordAndByPage(
            @PathVariable int userProfileId, @PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<CoachMembershipNotification> coachMembershipNotifications = new ArrayList<>();
        List<Object> coachMembershipNotificationObject = this.coachMembershipNotificationService
                .findCoachMembershipNotificationsByUserProfileIdAndByKeywordAndByPage(userProfileId, selectedKeyWord, startIndex - 1);
        for (Object o : coachMembershipNotificationObject) {
            Object[] eachCoachMembershipNotificationObject = (Object[]) o;
            int id = (int) eachCoachMembershipNotificationObject[0];
            String content = (String) eachCoachMembershipNotificationObject[1];
            int coachId = (int) eachCoachMembershipNotificationObject[3];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            Coach coach = this.coachService.getCoachById((long) coachId);
            int status = (int) eachCoachMembershipNotificationObject[4];
            Date createdDate = (Date) eachCoachMembershipNotificationObject[5];
            CoachMembershipNotification coachMembershipNotification = new CoachMembershipNotification(
                    (long) id,
                    userProfile,
                    coach,
                    status,
                    content,
                    createdDate
            );
            coachMembershipNotifications.add(coachMembershipNotification);
        }
        return coachMembershipNotifications;
    }

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @return number of notifications
     */
    @GetMapping(value = "/membership/notifications/count/{userProfileId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachMembershipNotificationsByUserProfileIdAndByKeyword(
            @PathVariable int userProfileId,
            @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countCoachMembershipNotificationsObject = this.coachMembershipNotificationService
                .countCoachMembershipNotificationsByUserProfileIdAndByKeyword(userProfileId, selectedKeyWord);
        Object eachCountMembershipNotificationObject = countCoachMembershipNotificationsObject.get(0);
        return new ResponseMessage(eachCountMembershipNotificationObject.toString());
    }

    /**
     * @param coachMembershipNotification - coach membership notification
     * @return inserted coach membership notification
     */
    @PostMapping(value = "/coach/membership/notifications/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachMembershipNotification addCoachMembershipNotification(@RequestBody CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationService.addCoachMembershipNotification(coachMembershipNotification);
    }

    /**
     * @param coachMembershipNotification - coach membership notification
     * @return updated coach membership notification
     */
    @PostMapping(value = "/coach/membership/notifications/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachMembershipNotification updateCoachMembershipNotification(@RequestBody CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationService.updateCoachMembershipNotification(coachMembershipNotification);
    }

    /**
     * @param userProfileId - user's profile's id
     * @param coachId       - coach's id
     * @param status        - status
     * @return selected coach membership notification
     */
    @GetMapping(value = "/coach/membership/notifications/{userProfileId}/{coachId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoachAndStatus(
            @PathVariable int userProfileId,
            @PathVariable int coachId,
            @PathVariable int status) {
        // get selected user profile
        UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
        // get selected coach
        Coach coach = this.coachService.getCoachById((long) coachId);
        return this.coachMembershipNotificationService.findCoachMembershipNotificationByUserProfileAndCoachAndStatus(userProfile, coach, status);
    }

}
