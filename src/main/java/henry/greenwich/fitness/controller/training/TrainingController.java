package henry.greenwich.fitness.controller.training;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.training.Training;
import henry.greenwich.fitness.service.training.TrainingService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("training-management")
public class TrainingController {
    private TrainingService trainingService;

    /**
     * @param trainingService - inject trainingService
     */
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /**
     * @param response      - response to add number of trainings and number of
     *                      pages
     * @param coachId       - coach's id that user want to get list of trainings
     *                      (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get list of
     *                      trainings (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of trainings
     */
    @GetMapping(value = "/trainings", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Training> getTrainings(HttpServletResponse response,
            @RequestParam(required = false) Integer userProfileId, @RequestParam(required = false) Integer coachId,
            @RequestParam(required = false) Integer page) {
        if (page != null) {
            return this.getTrainingsPaging(response, userProfileId, coachId, page);
        }
        return this.trainingService.getTrainings(coachId, userProfileId);
    }

    /**
     * @param response      - response to add number of trainings and number of
     *                      pages
     * @param coachId       - coach's id that user want to get list of trainings
     *                      (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get list of
     *                      trainings (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of trainings
     */
    private List<Training> getTrainingsPaging(HttpServletResponse response, Integer userProfileId, Integer coachId,
            Integer page) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nTrainings = this.trainingService.getNumberOfTrainings(coachId, userProfileId);
        response.addHeader("X-Total-Count", String.valueOf(nTrainings));
        response.addHeader("X-Total-Page", String.valueOf(nTrainings / Constants.NUMBER_ITEMS_PER_PAGE));
        return this.trainingService.getTrainingsPaging(coachId, userProfileId, startIndex);
    }

    /**
     * @param trainings - trainings that user want to add to the database
     * @return inserted list of trainings
     */
    @PostMapping(value = "/trainings", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Training> addTrainings(@RequestBody List<Training> trainings) {
        return this.trainingService.addTrainings(trainings);
    }

    /**
     * @param trainingDate  - training's date that user want to get list of
     *                      trainings
     * @param userProfileId - user's profile's id that user want to get list of
     *                      trainings
     * @param coachId       - coach's id that user want to get list of trainings
     * @return list of trainings
     */
    @PostMapping(value = "users/{userProfileId}/coaches/{coachId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Training> getTrainings(@PathVariable int userProfileId, @PathVariable int coachId,
            @RequestParam(required = false) String trainingDate) {
        return this.trainingService.getTrainings(userProfileId, coachId, trainingDate);
    }
}
