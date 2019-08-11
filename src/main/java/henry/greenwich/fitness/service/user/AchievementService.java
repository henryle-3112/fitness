package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.Achievement;
import henry.greenwich.fitness.repository.user.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {
    /**
     * achievementRepository - interact with achievement's data
     */
    private AchievementRepository achievementRepository;

    /**
     *
     * @param achievementRepository - inject achievementRepository
     */
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    /**
     *
     * @param userId - userId that user want to get achievement
     * @return list of achievements
     */
    public List<Object> getAchievementsById(Long userId) {
        return this.achievementRepository.getAchievementsById(userId);
    }

    /**
     *
     * @param achievement - achievement that user want to add to the database
     * @return achievement - that was inserted to the database
     */
    public Achievement addAchievement(Achievement achievement) {
        return this.achievementRepository.saveAndFlush(achievement);
    }

    /**
     *
     * @param userProfileId - user's profile's id
     * @param startIndex - start's index
     * @return list of achievements
     */
    public List<Object> findAchievementsByUserProfileIdAndByPage(int userProfileId, int startIndex) {
        return this.achievementRepository.findAchievementsByUserProfileIdAndByPage(userProfileId, startIndex);
    }

    /**
     *
     * @param userProfileId - user's profile's id
     * @return number of achievements
     */
    public List<Object> countAchievementsByUserProfileId(int userProfileId) {
        return this.achievementRepository.countAchievementsByUserProfileId(userProfileId);
    }
}
