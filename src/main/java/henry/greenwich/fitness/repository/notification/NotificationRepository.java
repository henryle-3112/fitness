package henry.greenwich.fitness.repository.notification;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

        String GET_NOTIFICATIONS_BY_PAGE = "select * from " + Constants.NOTIFICATION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.NOTIFICATION_TABLE + "."
                        + Constants.NOTIFICATION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:notificationContentKeywords is null or lower(" + Constants.NOTIFICATION_TABLE + "."
                        + Constants.NOTIFICATION_CONTENT
                        + ") like %:notificationContentKeywords%) and (:notificationStatus is null or "
                        + Constants.NOTIFICATION_TABLE + "." + Constants.NOTIFICATION_STATUS + " = :notificationStatus)"
                        + " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

        String GET_NUMBER_OF_NOTIFICATIONS = "select count(*) from " + Constants.NOTIFICATION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.NOTIFICATION_TABLE + "."
                        + Constants.NOTIFICATION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:notificationContentKeywords is null or lower(" + Constants.NOTIFICATION_TABLE + "."
                        + Constants.NOTIFICATION_CONTENT
                        + ") like %:notificationContentKeywords%) and (:notificationStatus is null or "
                        + Constants.NOTIFICATION_TABLE + "." + Constants.NOTIFICATION_STATUS
                        + " = :notificationStatus)";

        String GET_NOTIFICATIONS = "select * from " + Constants.NOTIFICATION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.NOTIFICATION_TABLE + "."
                        + Constants.NOTIFICATION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:notificationContentKeywords is null or lower(" + Constants.NOTIFICATION_TABLE + "."
                        + Constants.NOTIFICATION_CONTENT
                        + ") like %:notificationContentKeywords%) and (:notificationStatus is null or "
                        + Constants.NOTIFICATION_TABLE + "." + Constants.NOTIFICATION_STATUS
                        + " = :notificationStatus)";

        /**
         * @param userProfileId               - user's profile's id that user want to
         *                                    get notifications (this parameter could be
         *                                    optional)
         * @param notificationContentKeywords - notification's content's keywords that
         *                                    user want to get notifications (this
         *                                    parameter could be optional)
         * @param notificationStatus          - notification's staus that user want to
         *                                    get notifications (this parameter could be
         *                                    optional)
         * @param startIndex                  start index (for pagination) (this
         *                                    parameter could be optional)
         * @return list of notifications
         */
        @Query(nativeQuery = true, value = GET_NOTIFICATIONS_BY_PAGE)
        List<Object> getNotificationsPaging(Integer userProfileId, String notificationContentKeywords,
                        Integer notificationStatus, Integer startIndex);

        /**
         * @param userProfileId               - user's profile's id that user want to
         *                                    get number of notifications (this
         *                                    parameter could be optional)
         * @param notificationContentKeywords - notification's content's keywords that
         *                                    user want to get number of notifications
         *                                    (this parameter could be optional)
         * @param notificationStatus          - notification's staus that user want to
         *                                    get numbere of notifications (this
         *                                    parameter could be optional)
         * @return number of notifications
         */
        @Query(nativeQuery = true, value = GET_NUMBER_OF_NOTIFICATIONS)
        List<Object> getNumberOfNotifications(Integer userProfileId, String notificationContentKeywords,
                        Integer notificationStatus);

        /**
         * @param userProfileId               - user's profile's id that user want to
         *                                    get notifications (this parameter could be
         *                                    optional)
         * @param notificationContentKeywords - notification's content's keywords that
         *                                    user want to get notifications (this
         *                                    parameter could be optional)
         * @param notificationContentKeywords - notification's status that user want to
         *                                    get notifications (this parameter could be
         *                                    optional)
         * @param notificationStatus          - notification's staus that user want to
         *                                    get notifications (this parameter could be
         *                                    optional)
         * @return list of notifications
         */
        @Query(nativeQuery = true, value = GET_NOTIFICATIONS)
        List<Object> getNotifications(Integer userProfileId, String notificationContentKeywords,
                        Integer notificationStatus);
}
