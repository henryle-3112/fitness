package henry.greenwich.fitness.controller.training;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.training.Training;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.training.TrainingService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainingController {
    /**
     * trainingService - interact with training's data
     * userProfileService - interact with user's profile's data
     * coachService - interact with coach's data
     */
    private TrainingService trainingService;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param trainingService - inject trainingService
     */
    public TrainingController(TrainingService trainingService,
                              UserProfileService userProfileService,
                              CoachService coachService) {
        this.trainingService = trainingService;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param userProfileId - user's profile's id
     * @param coachId       - coach's id
     * @param page          - page
     * @return list of trainings
     */
    @GetMapping(value = "/trainings/paging/{userProfileId}/{coachId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Training> findTrainingsByUserProfileIdAndCoachIdAndPage(
            @PathVariable int userProfileId,
            @PathVariable int coachId,
            @PathVariable int page) {
        int startIndex = ((page - 1) * 8) + 1;
        List<Training> trainings = new ArrayList<>();
        List<Object> trainingsObject = this.trainingService.findTrainingsByUserProfileIdAndCoachIdAndPage(
                coachId,
                userProfileId,
                startIndex - 1);
        for (Object o : trainingsObject) {
            String trainingDate = (String) o;
            Training training = new Training();
            training.setTrainingDate(trainingDate);
            trainings.add(training);
        }
        return trainings;
    }

    /**
     * @param userProfileId - user's profile's id
     * @param coachId       - coach's id
     * @return number of trainings
     */
    @GetMapping(value = "/trainings/count/{userProfileId}/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countTrainingsByUserProfileIdAndCoachId(@PathVariable int userProfileId, @PathVariable int coachId) {
        List<Object> countTrainingsObject = this.trainingService.countTrainingsByUserProfileIdAndCoachId(
                coachId,
                userProfileId
        );
        int nTrainings = 0;
        for (Object eachTrainingObject : countTrainingsObject) {
            nTrainings += Integer.valueOf(eachTrainingObject.toString());
        }
        return new ResponseMessage(String.valueOf(nTrainings));
    }


    /**
     * @param trainings - trainings
     * @return list of trainings
     */
    @PostMapping(value = "/trainings", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Training> addTrainings(@RequestBody List<Training> trainings) {
        return this.trainingService.addTrainings(trainings);
    }

    /**
     * @param trainingDate - training's date
     * @return list of trainings
     */
    @PostMapping(value = "/trainings/date/{userProfileId}/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Training> findTrainingsByTrainingDate(@PathVariable int userProfileId, @PathVariable int coachId, @RequestBody String trainingDate) {
        UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
        Coach coach = this.coachService.getCoachById((long) coachId);
        return this.trainingService.findTrainingsByTrainingDateAndUserProfileAndCoach(trainingDate, userProfile, coach);
    }

    /**
     * @return responseMessage
     */
    @PostMapping(value = "/trainings/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Training updateTraining(@RequestBody Training training) {
        return this.trainingService.updateTraining(training);
    }

}
