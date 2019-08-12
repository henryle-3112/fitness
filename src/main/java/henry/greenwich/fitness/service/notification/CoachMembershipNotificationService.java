package henry.greenwich.fitness.service.notification;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.notification.CoachMembershipNotification;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.notification.CoachMembershipNotificationRepository;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CoachMembershipNotificationService {
    private CoachMembershipNotificationRepository coachMembershipNotificationRepository;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param coachMembershipNotificationRepository - inject coachMembershipNotificationRepository
     * @param userProfileService                    - inject userProfileService
     * @param coachService                          - inject coachService
     */
    public CoachMembershipNotificationService(CoachMembershipNotificationRepository coachMembershipNotificationRepository,
                                              UserProfileService userProfileService,
                                              CoachService coachService) {
        this.coachMembershipNotificationRepository = coachMembershipNotificationRepository;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param coachId                     - coach's id that user want to get coach
     *                                    membership notifications (this parameter
     *                                    could be optional)
     * @param userProfileId               - user's profile's id that user want to
     *                                    get coach memberships notifications (this
     *                                    parameter could be optional)
     * @param notificationContentKeywords - notifications' content's keywords that
     *                                    user want to get coach membership
     *                                    notifications (this parameter could be
     *                                    optional)
     * @param startIndex                  - start index (for pagination) (this
     *                                    parameter could be optional)
     * @return list of coach membership notifications
     */
    public List<CoachMembershipNotification> getCoachMembershipNotificationsPaging(Integer coachId,
                                                                                   Integer userProfileId, String notificationContentKeywords, Integer startIndex) {
        List<Object> coachMembershipNotificationsObjectList = this.coachMembershipNotificationRepository
                .getCoachMembershipNotificationsPaging(coachId, userProfileId, notificationContentKeywords, startIndex);
        return this.getCoachMembershipNotificationsFromObjectList(coachMembershipNotificationsObjectList);
    }

    /**
     * @param coachId                     - coach's id that user want to count
     *                                    number of coach membership notifications
     * @param userProfileId               - user's profile's id that user want to
     *                                    count number of coach membership
     *                                    notifications
     * @param notificationContentKeywords - notification's content's keywords that
     *                                    user want to count number of coach
     *                                    membership notifications
     * @return number of notifications
     */
    public int getNumberOfCoachMembershipNotifications(Integer coachId, Integer userProfileId,
                                                       String notificationContentKeywords) {
        List<Object> nCoachMembershipNotificationsObjectList = this.coachMembershipNotificationRepository
                .getNumberOfCoachMembershipNotifications(coachId, userProfileId, notificationContentKeywords);
        if (nCoachMembershipNotificationsObjectList.size() > 0) {
            return Integer.valueOf(nCoachMembershipNotificationsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param coachId                     - coach's id that user want to get coach
     *                                    membership notifications (this parameter
     *                                    could be optional)
     * @param userProfileId               - user's profile's id that user want to
     *                                    get coach memberships notifications (this
     *                                    parameter could be optional)
     * @param notificationContentKeywords - notifications' content's keywords that
     *                                    user want to get coach membership
     *                                    notifications (this parameter could be
     *                                    optional)
     * @return list of coach membership notifications
     */
    public List<CoachMembershipNotification> getCoachMembershipNotifications(Integer coachId, Integer userProfileId,
                                                                             String notificationContentKeywords) {
        List<Object> coachMembershipNotificationsObjectList = this.coachMembershipNotificationRepository
                .getCoachMembershipNotifications(coachId, userProfileId, notificationContentKeywords);
        return this.getCoachMembershipNotificationsFromObjectList(coachMembershipNotificationsObjectList);
    }

    /**
     * @param coachMembershipNotification - coach membership notification
     * @return inserted coach membership notification
     */
    public CoachMembershipNotification addCoachMembershipNotification(
            CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationRepository.saveAndFlush(coachMembershipNotification);
    }

    /**
     * @param coachMembershipNotification - coach membership notification
     * @return updated coach membership notification
     */
    public CoachMembershipNotification updateCoachMembershipNotification(
            CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationRepository.saveAndFlush(coachMembershipNotification);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach
     *                      membership notification
     * @param coachId       - coach's id that user want to get coach membership
     *                      notification
     * @param status        - status of coach membership notification
     * @return selected coach membership notification
     */
    public CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoachAndStatus(
            Integer userProfileId, Integer coachId, Integer status) {
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        Coach coach = this.getCoach(coachId, null);
        return this.coachMembershipNotificationRepository
                .findCoachMembershipNotificationByUserProfileAndCoachAndStatus(userProfile, coach, status);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach
     *                      membership notification
     * @param coachId       - coach's id that user want to get coach membership
     *                      notification
     * @return selected coach membership notification
     */
    public CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoach(Integer userProfileId,
                                                                                            Integer coachId) {
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        Coach coach = this.getCoach(coachId, null);
        return this.coachMembershipNotificationRepository
                .findCoachMembershipNotificationByUserProfileAndCoach(userProfile, coach);
    }

    /**
     * @param coachMembershipNotificationsObjectList - coach membership
     *                                               notiification objects list that
     *                                               user want to convert to coach
     *                                               membership notifications list
     * @return list of coach membership notifications
     */
    private List<CoachMembershipNotification> getCoachMembershipNotificationsFromObjectList(
            List<Object> coachMembershipNotificationsObjectList) {
        List<CoachMembershipNotification> coachMembershipNotifications = new ArrayList<>();
        for (Object o : coachMembershipNotificationsObjectList) {
            Object[] coachMembershipNotificationObjectArr = (Object[]) o;
            CoachMembershipNotification coachMembershipNotification = this
                    .createCoachMembershipNotificationFromObjectArray(coachMembershipNotificationObjectArr);
            coachMembershipNotifications.add(coachMembershipNotification);
        }
        return coachMembershipNotifications;
    }

    /**
     * @param coachMembershipNotificationObjectArr - coach membership notification
     *                                             object array that user want to
     *                                             convert to coach membership
     *                                             notification object
     * @return converted coach membership notification
     */
    private CoachMembershipNotification createCoachMembershipNotificationFromObjectArray(
            Object[] coachMembershipNotificationObjectArr) {
        int coachMembershipNotificationId = (int) coachMembershipNotificationObjectArr[0];
        String coachMembershipNotificationContent = (String) coachMembershipNotificationObjectArr[1];
        int userProfileId = (int) coachMembershipNotificationObjectArr[2];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int coachId = (int) coachMembershipNotificationObjectArr[3];
        Coach coach = this.getCoach(coachId, null);
        int coachMembershipNotificationStatus = (int) coachMembershipNotificationObjectArr[4];
        Date coachMembershipNotificationCreatedDate = (Date) coachMembershipNotificationObjectArr[5];
        return new CoachMembershipNotification((long) coachMembershipNotificationId, userProfile, coach,
                coachMembershipNotificationStatus, coachMembershipNotificationContent,
                coachMembershipNotificationCreatedDate);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param coachId     - coach's id that user want to get selected coach
     * @param coachStatus - coach's status that user want to get selected coach
     * @return selected coach
     */
    private Coach getCoach(Integer coachId, Integer coachStatus) {
        return this.coachService.getCoach(coachId, coachStatus);
    }
}
