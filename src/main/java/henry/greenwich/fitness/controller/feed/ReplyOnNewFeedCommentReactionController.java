package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnNewFeedCommentReactionController {
    /**
     * replyOnNewFeedCommentReactionService - interact with reply on new feed comment data
     */
    private ReplyOnNewFeedCommentReactionService replyOnNewFeedCommentReactionService;

    /**
     * @param replyOnNewFeedCommentReactionService - inject replyOnNewFeedCommentReactionService
     */
    public ReplyOnNewFeedCommentReactionController(ReplyOnNewFeedCommentReactionService replyOnNewFeedCommentReactionService) {
        this.replyOnNewFeedCommentReactionService = replyOnNewFeedCommentReactionService;
    }

    /**
     * @param replyOnNewFeedCommentReaction - replyOnNewFeedCommentReaction
     * @return inserted replyOnNewFeedCommentReaction
     */
    @PostMapping(value = "/feed/comment/reply/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnNewFeedCommentReaction addReplyOnNewFeedCommentReaction(
            @RequestBody ReplyOnNewFeedCommentReaction replyOnNewFeedCommentReaction) {
        return this.replyOnNewFeedCommentReactionService.addReplyOnNewFeedCommentReaction(replyOnNewFeedCommentReaction);
    }

    /**
     * @param replyOnNewFeedComment - replyOnNewFeedComment
     * @param reaction              - reaction
     * @return number of reactions
     */
    @PostMapping(value = "/feed/comment/reply/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countReplyOnNewFeedCommentReactionsByReplyOnNewFeedCommentAndReaction(
            @RequestBody ReplyOnNewFeedComment replyOnNewFeedComment,
            @PathVariable int reaction) {
        int nReactions = this.replyOnNewFeedCommentReactionService
                .countReplyOnNewFeedCommentReactionsByReplyOnNewFeedCommentAndReaction(
                        replyOnNewFeedComment, reaction
                );
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    @PostMapping(value = "/feed/comment/reply/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnNewFeedCommentReaction> findReplyOnNewFeedCommentReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.replyOnNewFeedCommentReactionService.findReplyOnNewFeedCommentReactionsByUserProfile(userProfile);
    }
}
