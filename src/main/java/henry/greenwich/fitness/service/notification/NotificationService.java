package henry.greenwich.fitness.service.notification;

import henry.greenwich.fitness.model.notification.Notification;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.notification.NotificationRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;
    private UserProfileService userProfileService;

    /**
     * @param notificationRepository - inject notificationRepository
     */
    public NotificationService(NotificationRepository notificationRepository, UserProfileService userProfileService) {
        this.notificationRepository = notificationRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param userProfileId               - user's profile's id that user want to
     *                                    get notifications (this parameter could be
     *                                    optional)
     * @param notificationContentKeywords - notification's content's keywords that
     *                                    user want to get notifications (this
     *                                    parameter could be optional)
     * @param startIndex                  start index (for pagination) (this
     *                                    parameter could be optional)
     * @return list of notifications
     */
    public List<Notification> getNotificationsPaging(Integer userProfileId, String notificationContentKeywords,
            Integer startIndex) {
        List<Object> notificationsObjectList = this.notificationRepository.getNotificationsPaging(userProfileId,
                notificationContentKeywords, startIndex);
        return this.getNotificationsFromObjectList(notificationsObjectList);
    }

    /**
     * @param userProfileId               - user's profile's id that user want to
     *                                    get number of notifications (this
     *                                    parameter could be optional)
     * @param notificationContentKeywords - notification's content's keywords that
     *                                    user want to get number of notifications
     *                                    (this parameter could be optional)
     * @return number of notifications
     */
    public int getNumberOfNotifications(Integer userProfileId, String notificationContentKeywords) {
        List<Object> nNotificationsObjectList = this.notificationRepository.getNumberOfNotifications(userProfileId,
                notificationContentKeywords);
        if (nNotificationsObjectList.size() > 0) {
            return Integer.valueOf(nNotificationsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param userProfileId               - user's profile's id that user want to
     *                                    get notifications (this parameter could be
     *                                    optional)
     * @param notificationContentKeywords - notification's content's keywords that
     *                                    user want to get notifications (this
     *                                    parameter could be optional)
     * @return list of notifications
     */
    public List<Notification> getNotifications(Integer userProfileId, String notificationContentKeywords) {
        List<Object> notificationsObjectList = this.notificationRepository.getNotifications(userProfileId,
                notificationContentKeywords);
        return this.getNotificationsFromObjectList(notificationsObjectList);
    }

    /**
     * @param notificationsObjectList - notification objects list that user want to
     *                                convert to notifcations list
     * @return list of notifications
     */
    private List<Notification> getNotificationsFromObjectList(List<Object> notificationsObjectList) {
        List<Notification> notifications = new ArrayList<>();
        for (Object o : notificationsObjectList) {
            Object[] notificationObjectArr = (Object[]) o;
            Notification notification = this.createNotificationFromObjectArray(notificationObjectArr);
            notifications.add(notification);
        }
        return notifications;
    }

    /**
     * @param notificationObjectArr - notification object array that user want to
     *                              convert to notification object
     * @return converted notifications
     */
    private Notification createNotificationFromObjectArray(Object[] notificationObjectArr) {
        int notificationId = (int) notificationObjectArr[0];
        String notificationContent = (String) notificationObjectArr[1];
        Date createdDate = (Date) notificationObjectArr[2];
        int notificationStatus = (int) notificationObjectArr[3];
        int userProfileId = (int) notificationObjectArr[4];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new Notification((long) notificationId, notificationContent, userProfile, createdDate,
                notificationStatus);
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
     * @param notification - notification
     * @return inserted notification
     */
    public Notification addNotification(Notification notification) {
        return this.notificationRepository.saveAndFlush(notification);
    }
}
