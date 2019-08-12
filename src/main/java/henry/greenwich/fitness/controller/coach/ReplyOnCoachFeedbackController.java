package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("coach-management")
public class ReplyOnCoachFeedbackController {
    private ReplyOnCoachFeedbackService replyOnCoachFeedbackService;

    /**
     * @param replyOnCoachFeedbackService - inject replyOnCoachFeedbackService
     */
    public ReplyOnCoachFeedbackController(ReplyOnCoachFeedbackService replyOnCoachFeedbackService) {
        this.replyOnCoachFeedbackService = replyOnCoachFeedbackService;
    }

    /**
     * @param coachFeedbackId - coach's feedback's id that user want to get list of
     *                        replies
     * @param status          - coach's feedback's status that user want to get list
     *                        of replies (this parameter could be optional)
     * @return list of replies
     */
    @GetMapping(value = "/feedbacks/{coachFeedbackId}/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnCoachFeedback> getReplyOnCoachFeedbacks(@PathVariable Integer coachFeedbackId,
                                                               @RequestParam(required = false) Integer status) {
        return this.replyOnCoachFeedbackService.getRepliesOnCoachFeedback(coachFeedbackId, status);
    }

    /**
     * @param replyOnCoachFeedback - reply on coach's feedback that user want to add
     *                             to the database
     * @return inserted reply on coach's feedback
     */
    @PostMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnCoachFeedback addReplyOnCoachFeedback(@RequestBody ReplyOnCoachFeedback replyOnCoachFeedback) {
        return this.replyOnCoachFeedbackService.addReplyOnCoachFeedback(replyOnCoachFeedback);
    }

    /**
     * @param replyOnCoachFeedback - reply on coach's feedback that user want to update
     * @return updated reply on coach's feedback
     */
    @PutMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnCoachFeedback updateReplyOnCoachFeedback(@RequestBody ReplyOnCoachFeedback replyOnCoachFeedback) {
        return this.replyOnCoachFeedbackService.updateReplyOnCoachFeedback(replyOnCoachFeedback);
    }


}
