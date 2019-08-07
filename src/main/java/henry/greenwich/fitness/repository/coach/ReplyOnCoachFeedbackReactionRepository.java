package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReplyOnCoachFeedbackReactionRepository extends JpaRepository<ReplyOnCoachFeedbackReaction, Long> {

        String GET_REPLY_ON_COACH_FEEDBACK_REACTIONS = "select * from "
                        + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_TABLE + "" + " where (:userProfileId is null or "
                        + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_TABLE + "."
                        + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)";

        String GET_REPLY_ON_COACH_FEEDBACK_REACTION = "select * from "
                        + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_TABLE + "" + " where (:userProfileId is null or "
                        + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_TABLE + "."
                        + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:replyOnCoachFeedbackId is null or " + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_TABLE
                        + "." + Constants.REPLY_ON_COACH_FEEDBACK_REACTION_REPLY_ON_COACH_FEEDBACK_ID
                        + " = :replyOnCoachFeedbackId)";

        /**
         * @param userProfileId - user's profile's id that user want to get reactions of
         *                      reply on coach's feedback
         * @return list reactions of reply on coach's feedback
         */
        @Query(nativeQuery = true, value = GET_REPLY_ON_COACH_FEEDBACK_REACTIONS)
        List<Object> getReplyOnCoachFeedbackReactions(@RequestParam("userProfileId") Integer userProfileId);

        /**
         * @param userProfileId          - user's profile's id that user want to get
         *                               reaction of reply on coach's feedback
         * @param replyOnCoachFeedbackId - id of reply on coach's feedback that user
         *                               want to get reaction
         * @return reaction of reply on coach's feedback
         */
        @Query(nativeQuery = true, value = GET_REPLY_ON_COACH_FEEDBACK_REACTION)
        List<Object> getReplyOnCoachFeedbackReaction(@RequestParam("userProfileId") Long userProfileId,
                        @RequestParam("replyOnCoachFeedbackId") Long replyOnCoachFeedbackId);
}
