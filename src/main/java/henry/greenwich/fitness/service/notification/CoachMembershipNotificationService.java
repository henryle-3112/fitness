package henry.greenwich.fitness.service.notification;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.notification.CoachMembershipNotification;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.notification.CoachMembershipNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachMembershipNotificationService {
    /**
     * coachMembershipNotificationRepository - interact with coach membership notification
     */
    private CoachMembershipNotificationRepository coachMembershipNotificationRepository;

    /**
     * @param coachMembershipNotificationRepository - inject coachMembershipNotificationRepository
     */
    public CoachMembershipNotificationService(CoachMembershipNotificationRepository coachMembershipNotificationRepository) {
        this.coachMembershipNotificationRepository = coachMembershipNotificationRepository;
    }

    /**
     * @param coachId    - coach id
     * @param keyword    - keyword
     * @param startIndex - start's index
     * @return list of notifications
     */
    public List<Object> findCoachMembershipNotificationsByCoachIdAndByKeywordAndByPage(int coachId, String keyword, int startIndex) {
        return this.coachMembershipNotificationRepository.findCoachMembershipNotificationsByCoachIdAndByKeywordAndByPage(
                coachId,
                keyword,
                startIndex
        );
    }

    /**
     * @param coachId - coach's id
     * @param keyword - keyword
     * @return number of notifications
     */
    public List<Object> countCoachMembershipNotificationsByCoachIdAndByKeyword(int coachId, String keyword) {
        return this.coachMembershipNotificationRepository.countCoachMembershipNotificationsByCoachIdAndByKeyword(
                coachId,
                keyword
        );
    }

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @param startIndex    - start's index
     * @return list of notifications
     */
    public List<Object> findCoachMembershipNotificationsByUserProfileIdAndByKeywordAndByPage(int userProfileId, String keyword, int startIndex) {
        return this.coachMembershipNotificationRepository.findCoachMembershipNotificationsByUserProfileIdAndByKeywordAndByPage(
                userProfileId,
                keyword,
                startIndex
        );
    }

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @return number of notifications
     */
    public List<Object> countCoachMembershipNotificationsByUserProfileIdAndByKeyword(int userProfileId, String keyword) {
        return this.coachMembershipNotificationRepository.countCoachMembershipNotificationsByUserProfileIdAndByKeyword(
                userProfileId,
                keyword
        );
    }

    /**
     * @param coachMembershipNotification - coach membership notification
     * @return inserted coach membership notification
     */
    public CoachMembershipNotification addCoachMembershipNotification(CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationRepository.saveAndFlush(coachMembershipNotification);
    }

    /**
     * @param coachMembershipNotification - coach membership notification
     * @return updated coach membership notification
     */
    public CoachMembershipNotification updateCoachMembershipNotification(CoachMembershipNotification coachMembershipNotification) {
        return this.coachMembershipNotificationRepository.saveAndFlush(coachMembershipNotification);
    }

    /**
     * @param userProfile - user's profile
     * @param coach       - coach
     * @param status      - status
     * @return selected coach membership notification
     */
    public CoachMembershipNotification findCoachMembershipNotificationByUserProfileAndCoachAndStatus(UserProfile userProfile, Coach coach, int status) {
        return this.coachMembershipNotificationRepository.findCoachMembershipNotificationByUserProfileAndCoachAndStatus(userProfile, coach, status);
    }
}
