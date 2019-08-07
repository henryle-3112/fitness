package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.Achievement;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.user.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementService {
    private AchievementRepository achievementRepository;
    private UserProfileService userProfileService;

    /**
     * @param achievementRepository - inject achievementRepository
     * @param userProfileService    - inject userProfileService
     */
    public AchievementService(AchievementRepository achievementRepository, UserProfileService userProfileService) {
        this.achievementRepository = achievementRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param achievement - achievement that user want to add to the database
     * @return inserted achievement
     */
    public Achievement addAchievement(Achievement achievement) {
        return this.achievementRepository.saveAndFlush(achievement);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get achievements
     *                      (this parameter could be optional)
     * @param startIndex    - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of achievements
     */
    public List<Achievement> getAchievementPaging(Integer userProfileId, Integer startIndex) {
        List<Object> achievementsObjectList = this.achievementRepository.getAchievementsPaging(userProfileId,
                startIndex);
        return this.createAchievementsFromObjectList(achievementsObjectList);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get achievements
     *                      (this parameter could be optional)
     * @return list of achievements
     */
    public List<Achievement> getAchievements(Integer userProfileId) {
        List<Object> achievementsObjectList = this.achievementRepository.getAchievements(userProfileId);
        return this.createAchievementsFromObjectList(achievementsObjectList);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get number of
     *                      achievements (this parameter could be optional)
     * @return number of achievements
     */
    public int getNumberOfAchievements(Integer userProfileId) {
        List<Object> nAchievementsObjectList = this.achievementRepository.getNumberOfAchievements(userProfileId);
        if (nAchievementsObjectList.size() > 0) {
            return Integer.valueOf(nAchievementsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param achievementsObjectList - achievements object list that user want to
     *                               convert to achievements list
     * @return list of achievements
     */
    private List<Achievement> createAchievementsFromObjectList(List<Object> achievementsObjectList) {
        List<Achievement> achievements = new ArrayList<>();
        for (Object o : achievementsObjectList) {
            Object[] achievementObjectArr = (Object[]) o;
            Achievement achievement = this.createAchievementFromObjectArray(achievementObjectArr);
            achievements.add(achievement);
        }
        return achievements;
    }

    /**
     * @param achievementObjectArr - achievement object array that user want to
     *                             convert to achievement
     * @return converted achievement
     */
    private Achievement createAchievementFromObjectArray(Object[] achievementObjectArr) {
        String title = (String) achievementObjectArr[0];
        String time = (String) achievementObjectArr[1];
        int userProfileId = (int) achievementObjectArr[2];
        UserProfile selectedUserProfile = this.getUserProfile((long) userProfileId);
        int achievementId = (int) achievementObjectArr[3];
        int nReps = (int) achievementObjectArr[4];
        String log = (String) achievementObjectArr[5];
        String currentHealth = (String) achievementObjectArr[6];
        return new Achievement((long) achievementId, title, time, selectedUserProfile, nReps, log, currentHealth);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }
}
