package henry.greenwich.fitness.repository.notification;

import henry.greenwich.fitness.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @param startIndex    - start index
     * @return list of notifications
     */
    @Query(nativeQuery = true, value = "select * from notification where notification.user_profile_id = :userProfileId and lower(notification.content) like :keyword limit :startIndex, 8")
    List<Object> findNotificationsByUserProfileIdAndByPage(int userProfileId, String keyword, int startIndex);

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @return number of notifications
     */
    @Query(nativeQuery = true, value = "select count(*) from notification where notification.user_profile_id = :userProfileId and lower(notification.content) like :keyword")
    List<Object> countNumberOfNotificationByUserProfileId(int userProfileId, String keyword);
}
