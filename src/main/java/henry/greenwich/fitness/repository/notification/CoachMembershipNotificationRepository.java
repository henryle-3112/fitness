package henry.greenwich.fitness.repository.notification;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.notification.CoachMembershipNotification;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachMembershipNotificationRepository extends JpaRepository<CoachMembershipNotification, Long> {

    String GET_COACH_MEMBERSHIP_NOTIFICATIONS_PAGING = "select * from " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "" +
            " where (:coachId is null or " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:notificationContentKeywords is null or lower(" + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_CONTENT + ") like %:notificationContentKeywords%)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NUMBER_OF_COACH_MEMBERSHIP_NOTIFICATIONS_PAGING = "select count(*) from " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "" +
            " where (:coachId is null or " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:notificationContentKeywords is null or lower(" + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_CONTENT + ") like %:notificationContentKeywords%)";

    String GET_COACH_MEMBERSHIP_NOTIFICATIONS = "select * from " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "" +
            " where (:coachId is null or " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:notificationContentKeywords is null or lower(" + Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE + "." + Constants.COACH_MEMBERSHIP_NOTIFICATION_CONTENT + ") like %:notificationContentKeywords%)";

    /**
     * @param coachId                     - coach's id that user want to get coach membership notifications (this parameter could be optional)
     * @param userProfileId               - user's profile's id that user want to get coach memberships notifications (this parameter could be optional)
     * @param notificationContentKeywords - notifications' content's keywords that user want to get coach membership notifications (this parameter could be optional)
     * @param startIndex                  - start index (for pagination) (this parameter could be optional)
     * @return list of coach membership notifications
     */
    @Query(nativeQuery = true, value = GET_COACH_MEMBERSHIP_NOTIFICATIONS_PAGING)
    List<Object> getCoachMembershipNotificationsPaging(Integer coachId,
                                                       Integer userProfileId,
                                                       String notificationContentKeywords,
                                                       Integer startIndex);

    /**
     * @param coachId                     - coach's id that user want to count number of coach membership notifications
     * @param userProfileId               - user's profile's id that user want to count number of coach membership notifications
     * @param notificationContentKeywords - notification's content's keywords that user want to count number of coach membership notifications
     * @return number of notifications
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_COACH_MEMBERSHIP_NOTIFICATIONS_PAGING)
    List<Object> getNumberOfCoachMembershipNotifications(Integer coachId,
                                                         Integer userProfileId,
                                                         String notificationContentKeywords);

    /**
     * @param coachId                     - coach's id that user want to get coach membership notifications (this parameter could be optional)
     * @param userProfileId               - user's profile's id that user want to get coach memberships notifications (this parameter could be optional)
     * @param notificationContentKeywords - notifications' content's keywords that user want to get coach membership notifications (this parameter could be optional)
     * @return list of coach membership notifications
     */
    @Query(nativeQuery = true, value = GET_COACH_MEMBERSHIP_NOTIFICATIONS)
    List<Object> getCoachMembershipNotifications(Integer coachId,
                                                 Integer userProfileId,
                                                 String notificationContentKeywords);

    /**
     * @param userProfile - user's profile that user want to get coach membership notification
     * @param coach       - coach that user want to get coach membership notification
     * @param status      - status of coach membership notification
     * @return selected coach membership notification
     */
    CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoachAndStatus(UserProfile userProfile, Coach coach, int status);

    /**
     * @param userProfile - user's profile that user want to get coach membership notification
     * @param coach       - coach that user want to get coach membership notification
     * @return selected coach membership notification
     */
    CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoach(UserProfile userProfile, Coach coach);
}
