package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.Achievement;
import henry.greenwich.fitness.service.user.AchievementService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("user-management")
public class AchievementController {
    private AchievementService achievementService;

    /**
     * @param achievementService - inject achievementService
     */
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    /**
     * @param achievement - that user want to add to the database
     * @return inserted user's achievement
     */
    @PostMapping(value = "/achievements", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Achievement addAchievement(@RequestBody Achievement achievement) {
        return this.achievementService.addAchievement(achievement);
    }

    /**
     * @param response      - response to add number of achievements and number of
     *                      pages (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get achievements
     *                      (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of achievements
     */
    @GetMapping(value = "/achievements", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Achievement> getAchievements(HttpServletResponse response,
            @RequestParam(required = false) Integer userProfileId, @RequestParam(required = false) Integer page) {
        if (page != null) {
            return this.getAchievementsPaging(response, userProfileId, page);
        }
        return this.achievementService.getAchievements(userProfileId);
    }

    /**
     * @param response      - response to add number of achievements and number of
     *                      pages (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get achievements
     *                      (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of achievements
     */
    private List<Achievement> getAchievementsPaging(HttpServletResponse response, Integer userProfileId, Integer page) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nAchievements = this.achievementService.getNumberOfAchievements(userProfileId);
        response.addHeader("X-Total-Count", String.valueOf(nAchievements));
        response.addHeader("X-Total-Page", String.valueOf(nAchievements / Constants.NUMBER_ITEMS_PER_PAGE));
        return this.achievementService.getAchievementPaging(userProfileId, startIndex);
    }

}
