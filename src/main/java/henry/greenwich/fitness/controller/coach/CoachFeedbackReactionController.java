package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.CoachFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CoachFeedbackReactionController {
    /**
     * coachFeedbackReactionService - interact with coach feedback reaction
     */
    private CoachFeedbackReactionService coachFeedbackReactionService;

    /**
     * @param coachFeedbackReactionService - inject coachFeedbackReactionService
     */
    public CoachFeedbackReactionController(CoachFeedbackReactionService coachFeedbackReactionService) {
        this.coachFeedbackReactionService = coachFeedbackReactionService;
    }


    @PostMapping(value = "/coach/feedback/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachFeedbackReaction addCoachFeedbackReaction(@RequestBody CoachFeedbackReaction coachFeedbackReaction) {
        return this.coachFeedbackReactionService.addCoachFeedbackReaction(coachFeedbackReaction);
    }

    @PostMapping(value = "/coach/feedback/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachFeedbackReactionsByCoachFeedbackAndReaction(
            @RequestBody CoachFeedback coachFeedback,
            @PathVariable int reaction) {
        int nReactions = this.coachFeedbackReactionService.countCoachFeedbackReactionsByCoachFeedbackAndReaction(coachFeedback, reaction);
        return new ResponseMessage(String.valueOf(nReactions));
    }

    @PostMapping(value = "/coach/feedback/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CoachFeedbackReaction> getCoachFeedbackReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.coachFeedbackReactionService.getCoachFeedbackReactionsByUserProfile(userProfile);
    }
}
