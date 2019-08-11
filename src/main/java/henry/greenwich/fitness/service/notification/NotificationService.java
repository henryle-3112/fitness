package henry.greenwich.fitness.service.notification;

import henry.greenwich.fitness.model.notification.Notification;
import henry.greenwich.fitness.repository.notification.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    /**
     * notificationRepository - inject notificationRepository
     */
    private NotificationRepository notificationRepository;

    /**
     * @param notificationRepository - inject notificationRepository
     */
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @param startIndex    - start's index
     * @return list of object
     */
    public List<Object> getNotificationsByUserProfileAndByPage(int userProfileId, String keyword, int startIndex) {
        return this.notificationRepository.findNotificationsByUserProfileIdAndByPage(userProfileId, keyword, startIndex);
    }

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @return number of notifications
     */
    public List<Object> getNumberOfNotificationsByUserProfile(int userProfileId, String keyword) {
        return this.notificationRepository.countNumberOfNotificationByUserProfileId(userProfileId, keyword);
    }

    /**
     *
     * @param notification - notification
     * @return inserted notification
     */
    public Notification addNotification(Notification notification) {
        return this.notificationRepository.saveAndFlush(notification);
    }
}
