package henry.greenwich.fitness.repository.notification;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.notification.CoachMembershipNotification;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachMembershipNotificationRepository extends JpaRepository<CoachMembershipNotification, Long> {

    /**
     * @param coachId    - coach's id
     * @param keyword    - keyword
     * @param startIndex - start's index
     * @return list of notifications
     */
    @Query(nativeQuery = true, value = "select * from coach_membership_notification where coach_id = :coachId and lower(content) like :keyword limit :startIndex, 8")
    List<Object> findCoachMembershipNotificationsByCoachIdAndByKeywordAndByPage(int coachId, String keyword, int startIndex);

    /**
     * @param coachId - coach id
     * @param keyword - keyword
     * @return number of notifications
     */
    @Query(nativeQuery = true, value = "select count(*) from coach_membership_notification where coach_id = :coachId and lower(content) like :keyword")
    List<Object> countCoachMembershipNotificationsByCoachIdAndByKeyword(int coachId, String keyword);

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @param startIndex    - start index
     * @return list of notifications
     */
    @Query(nativeQuery = true, value = "select * from coach_membership_notification where user_profile_id = :userProfileId and lower(content) like :keyword limit :startIndex, 8")
    List<Object> findCoachMembershipNotificationsByUserProfileIdAndByKeywordAndByPage(int userProfileId, String keyword, int startIndex);

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @return number of notifications
     */
    @Query(nativeQuery = true, value = "select count(*) from coach_membership_notification where user_profile_id = :userProfileId and lower(content) like :keyword")
    List<Object> countCoachMembershipNotificationsByUserProfileIdAndByKeyword(int userProfileId, String keyword);

    /**
     * @param userProfile - user's profile
     * @param coach       - coach
     * @param status      - status
     * @return selected coach membership notification
     */
    CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoachAndStatus(UserProfile userProfile, Coach coach, int status);
}
