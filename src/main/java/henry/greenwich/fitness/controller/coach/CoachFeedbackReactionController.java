package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import henry.greenwich.fitness.service.coach.CoachFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("coach-management")
public class CoachFeedbackReactionController {
    private CoachFeedbackReactionService coachFeedbackReactionService;

    /**
     * @param coachFeedbackReactionService - inject coachFeedbackReactionService
     */
    public CoachFeedbackReactionController(CoachFeedbackReactionService coachFeedbackReactionService) {
        this.coachFeedbackReactionService = coachFeedbackReactionService;
    }

    /**
     * @param coachFeedbackReaction - coach's feedback's reaction that user want to
     *                              add to the database
     * @return inserted coach's feedback's reaction
     */
    @PostMapping(value = "/feedback-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public CoachFeedbackReaction addCoachFeedbackReaction(@RequestBody CoachFeedbackReaction coachFeedbackReaction) {
        return this.coachFeedbackReactionService.addCoachFeedbackReaction(coachFeedbackReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      coach's feedback's reactions (1 is like and 0 is
     *                      dislike)
     * @return list of coach's feedback's reactions
     */
    @GetMapping(value = "/users/{userProfileId}/feedback-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<CoachFeedbackReaction> getCoachFeedbackReactionsByUserProfile(@PathVariable Integer userProfileId) {
        return this.coachFeedbackReactionService.getCoachFeedbackReactions(userProfileId);
    }
}
