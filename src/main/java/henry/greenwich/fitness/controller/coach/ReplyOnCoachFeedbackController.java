package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnCoachFeedbackController {
    /**
     * replyOnCoachFeedbackService - interact with reply on coach feedback
     */
    private ReplyOnCoachFeedbackService replyOnCoachFeedbackService;

    /**
     * @param replyOnCoachFeedbackService - inject replyOnCoachFeedbackService
     */
    public ReplyOnCoachFeedbackController(ReplyOnCoachFeedbackService replyOnCoachFeedbackService) {
        this.replyOnCoachFeedbackService = replyOnCoachFeedbackService;
    }

    @PostMapping(value = "/coach/feedback/replies/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnCoachFeedback> getReplyOnCoachFeedbacks(@RequestBody CoachFeedback coachFeedback, @PathVariable int status) {
        return this.replyOnCoachFeedbackService.getReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(coachFeedback, status);
    }

    @PostMapping(value = "/coach/feedback/replies/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnCoachFeedback addReplyOnCoachFeedback(@RequestBody ReplyOnCoachFeedback replyOnCoachFeedback) {
        return this.replyOnCoachFeedbackService.addReplyOnCoachFeedback(replyOnCoachFeedback);
    }

    @PostMapping(value = "/coach/feedback/replies/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfReplyOnCoachFeedbacksByCoachFeedback(@RequestBody CoachFeedback coachFeedback, @PathVariable int status) {
        int nReplies = this.replyOnCoachFeedbackService.countReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(
                coachFeedback, status);
        return new ResponseMessage(String.valueOf(nReplies));
    }
}
