package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.feed.NewFeedReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewFeedReactionController {
    /**
     * newFeedReactionService - interact with new's feed's reaction's data
     */
    private NewFeedReactionService newFeedReactionService;

    /**
     * @param newFeedReactionService - inject new's feed's reaction's service
     */
    public NewFeedReactionController(NewFeedReactionService newFeedReactionService) {
        this.newFeedReactionService = newFeedReactionService;
    }

    /**
     * @param newFeedReaction - new's feed's reaction
     * @return inserted new's feed's reaction
     */
    @PostMapping(value = "/feed/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeedReaction addNewFeedReaction(@RequestBody NewFeedReaction newFeedReaction) {
        return this.newFeedReactionService.addNewFeedReaction(newFeedReaction);
    }

    /**
     * @param newFeed  - new's feed
     * @param reaction - reaction
     * @return number of reactions
     */
    @PostMapping(value = "/feed/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNewFeedReactionsByNewFeedAndReaction(
            @RequestBody NewFeed newFeed,
            @PathVariable int reaction) {
        int nReactions = this.newFeedReactionService.countNewFeedReactionsByNewFeedAndReaction(newFeed, reaction);
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile - user's profile
     * @return list of new's feed's reactions
     */
    @PostMapping(value = "/feed/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeedReaction> findNewFeedReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.newFeedReactionService.findNewFeedReactionsByUserProfile(userProfile);
    }
}
