package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnCoachFeedbackReactionRepository extends JpaRepository<ReplyOnCoachFeedbackReaction, Long> {

    /**
     * @param replyOnCoachFeedback - replyOnCoachFeedback
     * @param reaction             - reaction
     * @return number of reactions
     */
    int countReplyOnCoachFeedbackReactionsByReplyOnCoachFeedbackAndReaction(ReplyOnCoachFeedback replyOnCoachFeedback, int reaction);

    /**
     * @param userProfile          - user's profile
     * @param replyOnCoachFeedback - replyOnCoachFeedback
     * @return
     */
    ReplyOnCoachFeedbackReaction findReplyOnCoachFeedbackReactionByUserProfileAndReplyOnCoachFeedback(UserProfile userProfile, ReplyOnCoachFeedback replyOnCoachFeedback);


    /**
     * @param userProfile - user's profile
     * @return list of reply on coach feedback reaction
     */
    List<ReplyOnCoachFeedbackReaction> findReplyOnCoachFeedbackReactionsByUserProfile(UserProfile userProfile);

}
