package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ReplyOnProductFeedbackReaction;
import henry.greenwich.fitness.service.product.ReplyOnProductFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product-management")
public class ReplyOnProductFeedbackReactionController {
    private ReplyOnProductFeedbackReactionService replyOnProductFeedbackReactionService;

    /**
     * @param replyOnProductFeedbackReactionService - inject
     *                                              replyOnProductFeedbackReactionService
     */
    public ReplyOnProductFeedbackReactionController(ReplyOnProductFeedbackReactionService replyOnProductFeedbackReactionService) {
        this.replyOnProductFeedbackReactionService = replyOnProductFeedbackReactionService;
    }

    /**
     * @param replyOnProductFeedbackReaction - reaction of reply on product's
     *                                       feedback that user want to add to the
     *                                       database
     * @return inserted reaction of reply on product's feedback
     */
    @PostMapping(value = "/reply-reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnProductFeedbackReaction addReplyOnProductFeedbackReaction(@RequestBody ReplyOnProductFeedbackReaction replyOnProductFeedbackReaction) {
        return this.replyOnProductFeedbackReactionService
                .addReplyOnProductFeedbackReaction(replyOnProductFeedbackReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      reactions
     * @return list of reactions
     */
    @GetMapping(value = "/users/{userProfileId}/reply-reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnProductFeedbackReaction> getReplyOnProductFeedbackReactions(@PathVariable Integer userProfileId) {
        return this.replyOnProductFeedbackReactionService.getReplyOnProductFeedbackReactions(userProfileId);
    }
}
