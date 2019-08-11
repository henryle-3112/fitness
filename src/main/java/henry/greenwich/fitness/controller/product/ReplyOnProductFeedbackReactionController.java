package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedbackReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.product.ReplyOnProductFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnProductFeedbackReactionController {
    /**
     * replyOnProductFeedbackReactionService - interact with reply on product feedback reaction data
     */
    private ReplyOnProductFeedbackReactionService replyOnProductFeedbackReactionService;

    /**
     * @param replyOnProductFeedbackReactionService - inject replyOnProductFeedbackReactionService
     */
    public ReplyOnProductFeedbackReactionController(ReplyOnProductFeedbackReactionService replyOnProductFeedbackReactionService) {
        this.replyOnProductFeedbackReactionService = replyOnProductFeedbackReactionService;
    }

    /**
     * @param replyOnProductFeedbackReaction - that user want to add to the database
     * @return inserted replyOnProductFeedbackReaction
     */
    @PostMapping(value = "/product/feedback/reply/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnProductFeedbackReaction addReplyOnProductFeedbackReaction(@RequestBody ReplyOnProductFeedbackReaction replyOnProductFeedbackReaction) {
        return this.replyOnProductFeedbackReactionService.addReplyOnProductFeedbackReaction(replyOnProductFeedbackReaction);
    }

    /**
     * @param replyOnProductFeedback - replyOnProductFeedback
     * @param reaction               - reaction
     * @return number of reactions
     */
    @PostMapping(value = "/product/feedback/reply/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfReplyOnProductFeedbackReactionsByReplyOnProductFeedbackAndReaction(
            @RequestBody ReplyOnProductFeedback replyOnProductFeedback,
            @PathVariable int reaction) {
        int nReactions = this.replyOnProductFeedbackReactionService.countNumberOfReplyOnProductFeedbackReactionsByReplyOnProductFeedbackAndReaction(
                replyOnProductFeedback, reaction
        );
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile user's profile
     * @return list of reactions
     */
    @PostMapping(value = "/product/feedback/reply/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnProductFeedbackReaction> getReplyOnProductFeedbackReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.replyOnProductFeedbackReactionService.getReplyOnProductFeedbackReactionsByUserProfile(userProfile);
    }
}
