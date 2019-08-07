package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CoachFeedbackReactionRepository extends JpaRepository<CoachFeedbackReaction, Long> {

        String GET_COACH_FEEDBACK_REACTIONS = "select * from " + Constants.COACH_FEEDBACK_REACTION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.COACH_FEEDBACK_REACTION_TABLE + "."
                        + Constants.COACH_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)";

        String GET_COACH_FEEDBACK_REACTION = "select * from " + Constants.COACH_FEEDBACK_REACTION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.COACH_FEEDBACK_REACTION_TABLE + "."
                        + Constants.COACH_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:coachFeedbackId is null or " + Constants.COACH_FEEDBACK_REACTION_TABLE + "."
                        + Constants.COACH_FEEDBACK_REACTION_COACH_FEEDBACK_ID + " = :coachFeedbackId)";

        /**
         * @param userProfileId - user's profile's id that user want to get
         * @return list of coach's feedback's reactions
         */
        @Query(nativeQuery = true, value = GET_COACH_FEEDBACK_REACTIONS)
        List<Object> getCoachFeedbackReactions(@RequestParam("userProfileId") int userProfileId);

        /**
         * @param userProfileId   - user's profile's id that user want to get
         * @param coachFeedbackId - coach's feedback's id that user want to get
         * @return selected coach's feedback's reaction
         */
        @Query(nativeQuery = true, value = GET_COACH_FEEDBACK_REACTION)
        List<Object> getCoachFeedbackReaction(@RequestParam("userProfileId") Long userProfileId,
                        @RequestParam("coachFeedbackId") Long coachFeedbackId);
}
