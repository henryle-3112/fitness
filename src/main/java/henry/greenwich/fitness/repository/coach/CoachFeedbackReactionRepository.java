package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachFeedbackReactionRepository extends JpaRepository<CoachFeedbackReaction, Long> {

    /**
     * @param coachFeedback - coach's feedback
     * @param reaction      - reaction
     * @return number of coach feedback reactions
     */
    int countCoachFeedbackReactionsByCoachFeedbackAndReaction(CoachFeedback coachFeedback, int reaction);

    /**
     * @param userProfile   - user's profile
     * @param coachFeedback - coach's feedback
     * @return
     */
    CoachFeedbackReaction findCoachFeedbackReactionByUserProfileAndCoachFeedback(UserProfile userProfile, CoachFeedback coachFeedback);


    /**
     *
     * @param userProfile - user's profile
     * @return list of coach feedback reactions
     */
    List<CoachFeedbackReaction> findCoachFeedbackReactionsByUserProfile(UserProfile userProfile);
}
