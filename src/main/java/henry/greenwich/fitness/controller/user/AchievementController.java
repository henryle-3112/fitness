package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.Achievement;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.user.AchievementService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AchievementController {
    /**
     * achievementService - interact with achievement's data
     * userProfileService - interact with user's profile's data
     */
    private AchievementService achievementService;
    private UserProfileService userProfileService;

    /**
     * @param achievementService - inject achievementService
     */
    public AchievementController(AchievementService achievementService, UserProfileService userProfileService) {
        this.achievementService = achievementService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param userId - userId that user want to get achievement
     * @return list of achievements
     */
    @GetMapping(value = "/achievements/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Achievement> getAchievementsById(@PathVariable Long userId) {
        // get list achievements as object
        List<Achievement> achievements = new ArrayList<>();
        List<Object> achievementObjects = this.achievementService.getAchievementsById(userId);
        for (Object eachAchievementObject : achievementObjects) {
            Object[] currentAchievementObject = (Object[]) eachAchievementObject;
            String title = (String) currentAchievementObject[0];
            String time = (String) currentAchievementObject[1];
            int userProfileId = (int) currentAchievementObject[2];
            UserProfile selectedUserProfile = this.userProfileService.getUserProfile((long) userProfileId);
            int achievementId = (int) currentAchievementObject[3];
            int nReps = (int) currentAchievementObject[4];
            String log = (String) currentAchievementObject[5];
            String currentHealth = (String) currentAchievementObject[6];
            Achievement achievement = new Achievement(
                    (long) achievementId,
                    title,
                    time,
                    selectedUserProfile,
                    nReps,
                    log,
                    currentHealth);
            achievements.add(achievement);
        }
        return achievements;
    }

    /**
     * @param achievement - that user want to add to the database
     * @return achievement that was inserted to the database
     */
    @PostMapping(value = "/achievements/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Achievement addAchievement(@RequestBody Achievement achievement) {
        return this.achievementService.addAchievement(achievement);
    }

    @GetMapping(value = "/achievements/{userId}/paging/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Achievement> findAchievementsByUserProfileIdAndByPage(@PathVariable int userId, @PathVariable int page) {
        int startIndex = ((page - 1) * 8) + 1;
        List<Achievement> achievements = new ArrayList<>();
        List<Object> achievementsObject = this.achievementService.findAchievementsByUserProfileIdAndByPage(
                userId, startIndex - 1);
        for (Object o : achievementsObject) {
            Object[] eachAchievementObject = (Object[]) o;
            String title = (String) eachAchievementObject[0];
            String time = (String) eachAchievementObject[1];
            int userProfileId = (int) eachAchievementObject[2];
            UserProfile selectedUserProfile = this.userProfileService.getUserProfile((long) userProfileId);
            int achievementId = (int) eachAchievementObject[3];
            int nReps = (int) eachAchievementObject[4];
            String log = (String) eachAchievementObject[5];
            String currentHealth = (String) eachAchievementObject[6];
            Achievement achievement = new Achievement(
                    (long) achievementId,
                    title,
                    time,
                    selectedUserProfile,
                    nReps,
                    log,
                    currentHealth
            );
            achievement.setAchievementId((long) achievementId);
            achievements.add(achievement);
        }
        return achievements;
    }

    @GetMapping(value = "/achievements/count/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countAchievements(@PathVariable int userId) {
        List<Object> countAchievementsObject = this.achievementService.countAchievementsByUserProfileId(userId);
        Object eachCountGallery = countAchievementsObject.get(0);
        return new ResponseMessage(eachCountGallery.toString());
    }
}
