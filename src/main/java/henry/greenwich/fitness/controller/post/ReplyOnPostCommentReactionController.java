package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostCommentReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.post.ReplyOnPostCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnPostCommentReactionController {
    /**
     * replyOnPostCommentReactionService - interact with reply on post comment data
     */
    private ReplyOnPostCommentReactionService replyOnPostCommentReactionService;

    /**
     * @param replyOnPostCommentReactionService - inject replyOnPostCommentReactionService
     */
    public ReplyOnPostCommentReactionController(ReplyOnPostCommentReactionService replyOnPostCommentReactionService) {
        this.replyOnPostCommentReactionService = replyOnPostCommentReactionService;
    }

    /**
     * @param replyOnPostCommentReaction - that user want to add to the database
     * @return inserted replyOnPostCommentReaction
     */
    @PostMapping(value = "/post/comment/reply/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnPostCommentReaction addReplyOnPostCommentReaction(@RequestBody ReplyOnPostCommentReaction replyOnPostCommentReaction) {
        return this.replyOnPostCommentReactionService.addReplyOnPostCommentReaction(replyOnPostCommentReaction);
    }

    /**
     * @param replyOnPostComment - reply on post's comment
     * @param reaction           - reaction value
     * @return number of reactions
     */
    @PostMapping(value = "/post/comment/reply/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfReplyOnPostCommentReactionsByReplyOnPostCommentAndReaction(
            @RequestBody ReplyOnPostComment replyOnPostComment,
            @PathVariable int reaction) {
        int nReactions = this.replyOnPostCommentReactionService.countNumberOfReplyOnPostCommentReactionsByReplyOnPostCommentAndReaction(
                replyOnPostComment, reaction
        );
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile - user's profile
     * @return list of reply on post comment reactions
     */
    @PostMapping(value = "/post/comment/reply/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnPostCommentReaction> getReplyOnPostCommentReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.replyOnPostCommentReactionService.getReplyOnPostCommentReactionsByUserProfile(userProfile);
    }
}
