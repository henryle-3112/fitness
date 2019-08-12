package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeedReaction;
import henry.greenwich.fitness.service.feed.NewFeedReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("feed-management")
public class NewFeedReactionController {
    private NewFeedReactionService newFeedReactionService;

    /**
     * @param newFeedReactionService - inject new's feed's reaction's service
     */
    public NewFeedReactionController(NewFeedReactionService newFeedReactionService) {
        this.newFeedReactionService = newFeedReactionService;
    }

    /**
     * @param newFeedReaction - new's feed's reaction that user want to add to the
     *                        database
     * @return inserted newfeed's reactions
     */
    @PostMapping(value = "/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeedReaction addNewFeedReaction(@RequestBody NewFeedReaction newFeedReaction) {
        return this.newFeedReactionService.addNewFeedReaction(newFeedReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get newfeed's
     *                      reactions
     * @return list of newfeed's reactions
     */
    @GetMapping(value = "/users/{userProfileId}/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeedReaction> getNewFeedReactions(@PathVariable Integer userProfileId) {
        return this.newFeedReactionService.getNewFeedReactions(userProfileId);
    }
}
