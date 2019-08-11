package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.service.coach.CoachFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CoachFeedbackController {
    /**
     * coachFeedbackService - interact with coach's feedback's data
     */
    private CoachFeedbackService coachFeedbackService;

    /**
     * @param coachFeedbackService - inject coach's feedback
     */
    public CoachFeedbackController(CoachFeedbackService coachFeedbackService) {
        this.coachFeedbackService = coachFeedbackService;
    }

    /**
     * @return list of coach's feedbacks
     */
    @PostMapping(value = "/coach/feedbacks/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CoachFeedback> getCoachFeedbacks(@RequestBody Coach coach, @PathVariable int status) {
        return this.coachFeedbackService.getCoachFeedbacksByCoachAndCoachFeedbackStatus(coach, status);
    }

    @PostMapping(value = "/coach/feedbacks/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachFeedback addCoachFeedback(@RequestBody CoachFeedback coachFeedback) {
        return this.coachFeedbackService.addCoachFeedback(coachFeedback);
    }
}
