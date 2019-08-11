package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachRate;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.CoachRateService;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CoachRateController {
    /**
     * coachRateService - interact coach's rate data
     * coachService - interact coach's data
     * userProfileService - interact user's profile's data
     */
    private CoachRateService coachRateService;
    private CoachService coachService;
    private UserProfileService userProfileService;

    /**
     * @param coachRateService   - inject coachRateService
     * @param coachService       - inject ccoachService
     * @param userProfileService - inject userProfileService
     */
    public CoachRateController(CoachRateService coachRateService,
                               CoachService coachService,
                               UserProfileService userProfileService) {
        this.coachRateService = coachRateService;
        this.coachService = coachService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param coachRate - coach's arte
     * @return inserted coach's rate
     */
    @PostMapping(value = "/coach/rates/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachRate addCoachRate(@RequestBody CoachRate coachRate) {
        return this.coachRateService.addCoachRate(coachRate);
    }

    /**
     * @param userId  - user's id
     * @param coachId - coach's id
     * @return selected coach's rate
     */
    @GetMapping(value = "/coach/rates/{userId}/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachRate getCoachRate(@PathVariable Long userId, @PathVariable Long coachId) {
        CoachRate coachRate = new CoachRate();
        List<Object> coachRateObjectList = this.coachRateService.getCoachRateByUserIdAndCoachId(userId, coachId);
        if (!coachRateObjectList.isEmpty()) {
            Object[] coachRateObjectArray = (Object[]) coachRateObjectList.get(0);
            int coachRateId = (int) coachRateObjectArray[0];
            int coachRateValue = (int) coachRateObjectArray[1];
            Coach coach = this.coachService.getCoachById(coachId);
            UserProfile userProfile = this.userProfileService.getUserProfile(userId);
            coachRate = new CoachRate(
                    (long) coachRateId,
                    coachRateValue,
                    coach,
                    userProfile
            );
            return coachRate;
        }
        return coachRate;
    }

    /**
     * @param coachId - coach's id
     * @return rate's average
     */
    @GetMapping(value = "/coach/rates/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage findRateAverage(@PathVariable int coachId) {
        List<Object> rateAverageObject = this.coachRateService.findRateAverate(coachId);
        Object eachRateAverateObject = rateAverageObject.get(0);
        return new ResponseMessage(eachRateAverateObject.toString());
    }
}
