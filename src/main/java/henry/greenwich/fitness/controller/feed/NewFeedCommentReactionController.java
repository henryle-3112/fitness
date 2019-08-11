package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.feed.NewFeedCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewFeedCommentReactionController {
    /**
     * newFeedCommentReactionService - interact with new's feed's comment's reaction's data
     */
    private NewFeedCommentReactionService newFeedCommentReactionService;

    /**
     * @param newFeedCommentReactionService - inject newFeedCommentReactionService
     */
    public NewFeedCommentReactionController(NewFeedCommentReactionService newFeedCommentReactionService) {
        this.newFeedCommentReactionService = newFeedCommentReactionService;
    }

    /**
     * @param newFeedCommentReaction - newFeedCommentReaction
     * @return inserted newFeedCommentReaction
     */
    @PostMapping(value = "/feed/comment/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeedCommentReaction addNewFeedCommentReaction(@RequestBody NewFeedCommentReaction newFeedCommentReaction) {
        return this.newFeedCommentReactionService.addNewFeedCommentReaction(newFeedCommentReaction);
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @param reaction       - reaction
     * @return number of reactions
     */
    @PostMapping(value = "/feed/comment/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNewFeedCommentReactionsByNewFeedCommentAndReaction(
            @RequestBody NewFeedComment newFeedComment,
            @PathVariable int reaction) {
        int nReactions = this.newFeedCommentReactionService.countNewFeedCommentReactionsByNewFeedCommentAndReaction(newFeedComment, reaction);
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile - user's profile
     * @return list of new's feed's comment's reactions
     */
    @PostMapping(value = "/feed/comment/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeedCommentReaction> findNewFeedCommentReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.newFeedCommentReactionService.findNewFeedCommentReactionsByUserProfile(userProfile);
    }
}
