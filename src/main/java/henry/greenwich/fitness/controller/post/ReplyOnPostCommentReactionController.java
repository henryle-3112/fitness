package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.ReplyOnPostCommentReaction;
import henry.greenwich.fitness.service.post.ReplyOnPostCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("post-management")
public class ReplyOnPostCommentReactionController {
    private ReplyOnPostCommentReactionService replyOnPostCommentReactionService;

    /**
     * @param replyOnPostCommentReactionService - inject
     *                                          replyOnPostCommentReactionService
     */
    public ReplyOnPostCommentReactionController(ReplyOnPostCommentReactionService replyOnPostCommentReactionService) {
        this.replyOnPostCommentReactionService = replyOnPostCommentReactionService;
    }

    /**
     * @param replyOnPostCommentReaction - reaction of reply on post's comment that
     *                                   user want to add to the database
     * @return inserted reaction of reply on post's comment
     */
    @PostMapping(value = "/reply-reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnPostCommentReaction addReplyOnPostCommentReaction(
            @RequestBody ReplyOnPostCommentReaction replyOnPostCommentReaction) {
        return this.replyOnPostCommentReactionService.addReplyOnPostCommentReaction(replyOnPostCommentReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      reactions
     * @return list of reactions
     */
    @GetMapping(value = "/users/{userProfileId}/reply-reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnPostCommentReaction> getReplyOnPostCommentReactions(@PathVariable Integer userProfileId) {
        return this.replyOnPostCommentReactionService.getReplyOnPostCommentReactions(userProfileId);
    }
}
