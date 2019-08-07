package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.service.coach.CoachFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("coach-management")
public class CoachFeedbackController {
    private CoachFeedbackService coachFeedbackService;

    /**
     * @param coachFeedbackService - inject coach's feedback
     */
    public CoachFeedbackController(CoachFeedbackService coachFeedbackService) {
        this.coachFeedbackService = coachFeedbackService;
    }

    /**
     * @param status - coach's feedback's status that user want to get (this
     *               parameter could be optional)
     * @return list of coach's feedbacks
     */
    @GetMapping(value = "/feedbacks", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<CoachFeedback> getCoachFeedbacks(@RequestParam(required = false) Integer status) {
        return this.coachFeedbackService.getCoachFeedbacks(status);
    }

    /**
     * @param coachFeedback - coach's feedback's status that user want to add to the
     *                      database
     * @return inserted coach's feedback
     */
    @PostMapping(value = "/feedbacks", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public CoachFeedback addCoachFeedback(@RequestBody CoachFeedback coachFeedback) {
        return this.coachFeedbackService.addCoachFeedback(coachFeedback);
    }
}
