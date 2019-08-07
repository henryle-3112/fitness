package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import henry.greenwich.fitness.service.feed.NewFeedCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("feed-management")
public class NewFeedCommentReactionController {
    private NewFeedCommentReactionService newFeedCommentReactionService;

    /**
     * @param newFeedCommentReactionService - inject newFeedCommentReactionService
     */
    public NewFeedCommentReactionController(NewFeedCommentReactionService newFeedCommentReactionService) {
        this.newFeedCommentReactionService = newFeedCommentReactionService;
    }

    /**
     * @param newFeedCommentReaction - newFeedCommentReaction that user want to add
     *                               to the database
     * @return inserted newFeedCommentReaction
     */
    @PostMapping(value = "/comment-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public NewFeedCommentReaction addNewFeedCommentReaction(
            @RequestBody NewFeedCommentReaction newFeedCommentReaction) {
        return this.newFeedCommentReactionService.addNewFeedCommentReaction(newFeedCommentReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      newfeed's comment's reactions (1 is like and 0 is
     *                      dislike)
     * @return list of new's feed's comment's reactions
     */
    @GetMapping(value = "/users/{userProfileId}/comment-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<NewFeedCommentReaction> getNewFeedCommentReactions(@PathVariable Integer userProfileId) {
        return this.newFeedCommentReactionService.getNewFeedCommentReactions(userProfileId);
    }
}
