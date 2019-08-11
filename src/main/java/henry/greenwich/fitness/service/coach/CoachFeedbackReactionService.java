package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachFeedbackReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachFeedbackReactionService {
    /**
     * coachFeedbackReactionRepository - interact with coach feedback reaction data
     */
    private CoachFeedbackReactionRepository coachFeedbackReactionRepository;

    /**
     * @param coachFeedbackReactionRepository - inject coachFeedbackReactionRepository
     */
    public CoachFeedbackReactionService(CoachFeedbackReactionRepository coachFeedbackReactionRepository) {
        this.coachFeedbackReactionRepository = coachFeedbackReactionRepository;
    }

    /**
     * @param coachFeedback - coach's feedback
     * @param reaction      - reaction
     * @return number of coach feedback reaction
     */
    public int countCoachFeedbackReactionsByCoachFeedbackAndReaction(CoachFeedback coachFeedback, int reaction) {
        return this.coachFeedbackReactionRepository.countCoachFeedbackReactionsByCoachFeedbackAndReaction(coachFeedback, reaction);
    }


    public CoachFeedbackReaction addCoachFeedbackReaction(CoachFeedbackReaction coachFeedbackReaction) {
        // check coach feedback reaction existed in the database or not
        // if not create new one, if yes, update coach feedback reactioin
        CoachFeedbackReaction selectedCoachFeedbackReaction = this.coachFeedbackReactionRepository.findCoachFeedbackReactionByUserProfileAndCoachFeedback(
                coachFeedbackReaction.userProfile,
                coachFeedbackReaction.coachFeedback
        );
        if (selectedCoachFeedbackReaction != null) {
            selectedCoachFeedbackReaction.setReaction(coachFeedbackReaction.getReaction());
            return this.coachFeedbackReactionRepository.saveAndFlush(selectedCoachFeedbackReaction);
        }
        return this.coachFeedbackReactionRepository.saveAndFlush(coachFeedbackReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of coach feedback reaction
     */
    public List<CoachFeedbackReaction> getCoachFeedbackReactionsByUserProfile(UserProfile userProfile) {
        return this.coachFeedbackReactionRepository.findCoachFeedbackReactionsByUserProfile(userProfile);
    }
}
