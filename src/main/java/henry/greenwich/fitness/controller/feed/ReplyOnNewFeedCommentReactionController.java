package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("feed-management")
public class ReplyOnNewFeedCommentReactionController {
    private ReplyOnNewFeedCommentReactionService replyOnNewFeedCommentReactionService;

    /**
     * @param replyOnNewFeedCommentReactionService - inject
     *                                             replyOnNewFeedCommentReactionService
     */
    public ReplyOnNewFeedCommentReactionController(
            ReplyOnNewFeedCommentReactionService replyOnNewFeedCommentReactionService) {
        this.replyOnNewFeedCommentReactionService = replyOnNewFeedCommentReactionService;
    }

    /**
     * @param replyOnNewFeedCommentReaction - reply on newfeed's comment's reaction
     *                                      that user want to add to the database
     * @return inserted replyOnNewFeedCommentReaction
     */
    @PostMapping(value = "/reply-reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnNewFeedCommentReaction addReplyOnNewFeedCommentReaction(
            @RequestBody ReplyOnNewFeedCommentReaction replyOnNewFeedCommentReaction) {
        return this.replyOnNewFeedCommentReactionService
                .addReplyOnNewFeedCommentReaction(replyOnNewFeedCommentReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on newfeed's comment
     * @return list of reactions of reply on newfeed's comment
     */
    @GetMapping(value = "/users/{userProfileId}/reply-reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnNewFeedCommentReaction> getReplyOnNewFeedCommentReactions(@PathVariable Integer userProfileId) {
        return this.replyOnNewFeedCommentReactionService.getReplyOnNewFeedCommentReactions(userProfileId);
    }
}
