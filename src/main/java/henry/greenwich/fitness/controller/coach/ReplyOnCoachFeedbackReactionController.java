package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("coach-management")
public class ReplyOnCoachFeedbackReactionController {
    private ReplyOnCoachFeedbackReactionService replyOnCoachFeedbackReactionService;

    /**
     * @param replyOnCoachFeedbackReactionService - inject
     *                                            replyOnCoachFeedbackReactionService
     */
    public ReplyOnCoachFeedbackReactionController(
            ReplyOnCoachFeedbackReactionService replyOnCoachFeedbackReactionService) {
        this.replyOnCoachFeedbackReactionService = replyOnCoachFeedbackReactionService;
    }

    /**
     * @param replyOnCoachFeedbackReaction - reply on coach's feedback reaction that
     *                                     user want to add to the database
     * @return inserted reply on coach's feedback reaction
     */
    @PostMapping(value = "/reply-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ReplyOnCoachFeedbackReaction addReplyOnCoachFeedbackReaction(
            @RequestBody ReplyOnCoachFeedbackReaction replyOnCoachFeedbackReaction) {
        return this.replyOnCoachFeedbackReactionService.addReplyOnCoachFeedbackReaction(replyOnCoachFeedbackReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on coach's feedback
     * @return list of reactions of reply on coach's feedback
     */
    @GetMapping(value = "/users/{userProfileId}/reply-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ReplyOnCoachFeedbackReaction> getReplyOnCoachFeedbackReactions(@PathVariable Integer userProfileId) {
        return this.replyOnCoachFeedbackReactionService.getReplyOnCoachFeedbackReactions(userProfileId);
    }
}
