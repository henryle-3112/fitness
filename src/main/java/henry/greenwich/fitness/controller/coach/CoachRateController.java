package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.CoachRate;
import henry.greenwich.fitness.service.coach.CoachRateService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("coach-management")
public class CoachRateController {
    private CoachRateService coachRateService;

    /**
     * @param coachRateService - inject coachRateService
     */
    public CoachRateController(CoachRateService coachRateService) {
        this.coachRateService = coachRateService;
    }

    /**
     * @param coachRate - coach's arte
     * @return inserted coach's rate
     */
    @PostMapping(value = "/rates", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachRate addCoachRate(@RequestBody CoachRate coachRate) {
        return this.coachRateService.addCoachRate(coachRate);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach's rate
     * @param coachId       - coach's id that user want to get coach's rate
     * @return selected coach's rate
     */
    @GetMapping(value = "/users/{userProfileId}/coaches/{coachId}/rates", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachRate getCoachRate(@PathVariable Long userProfileId, @PathVariable Long coachId) {
        return this.coachRateService.getCoachRate(userProfileId, coachId);
    }
}
