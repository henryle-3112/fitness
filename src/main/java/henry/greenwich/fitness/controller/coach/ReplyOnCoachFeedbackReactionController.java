package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnCoachFeedbackReactionController {
    /**
     * replyOnCoachFeedbackReactionService - interact with reply on coach feedback data
     */
    private ReplyOnCoachFeedbackReactionService replyOnCoachFeedbackReactionService;

    /**
     *
     * @param replyOnCoachFeedbackReactionService - inject replyOnCoachFeedbackReactionService
     */
    public ReplyOnCoachFeedbackReactionController(ReplyOnCoachFeedbackReactionService replyOnCoachFeedbackReactionService) {
        this.replyOnCoachFeedbackReactionService = replyOnCoachFeedbackReactionService;
    }

    /**
     *
     * @param replyOnCoachFeedbackReaction - replyOnCoachFeedbackReaction
     * @return inserted ReplyOnCoachFeedbackReaction
     */
    @PostMapping(value = "/coach/feedback/reply/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnCoachFeedbackReaction addReplyOnCoachFeedbackReaction(
            @RequestBody ReplyOnCoachFeedbackReaction replyOnCoachFeedbackReaction) {
        return this.replyOnCoachFeedbackReactionService.addReplyOnCoachFeedbackReaction(replyOnCoachFeedbackReaction);
    }

    /**
     *
     * @param replyOnCoachFeedback - replyOnCoachFeedback
     * @param reaction - reaction
     * @return number of reactions
     */
    @PostMapping(value = "/coach/feedback/reply/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countReplyOnCoachFeedbackReactionsByReplyOnCoachFeedbackAndReaction(
            @RequestBody ReplyOnCoachFeedback replyOnCoachFeedback,
            @PathVariable int reaction) {
        int nReactions = this.replyOnCoachFeedbackReactionService
                .countReplyOnCoachFeedbackReactionsByReplyOnCoachFeedbackAndReaction(
                        replyOnCoachFeedback, reaction
        );
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     *
     * @param userProfile - user's profile
     * @return list of reply's reactions
     */
    @PostMapping(value = "/coach/feedback/reply/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnCoachFeedbackReaction> getReplyOnCoachFeedbackReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.replyOnCoachFeedbackReactionService.getReplyOnCoachFeedbackReactionsByUserProfile(userProfile);
    }
}
