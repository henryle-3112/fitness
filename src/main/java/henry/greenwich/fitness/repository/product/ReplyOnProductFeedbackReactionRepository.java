package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnProductFeedbackReactionRepository extends JpaRepository<ReplyOnProductFeedbackReaction, Long> {

    /**
     * @param replyOnProductFeedback - that user want to count reactions
     * @param reaction               - that user want to count
     * @return number of reactions
     */
    int countReplyOnProductFeedbackReactionsByReplyOnProductFeedbackAndReaction(ReplyOnProductFeedback replyOnProductFeedback, int reaction);

    /**
     * @param userProfile            - user's profile
     * @param replyOnProductFeedback - reply on product's feedback
     * @return selected replyOnProductFeedbackReaction
     */
    ReplyOnProductFeedbackReaction findReplyOnProductFeedbackReactionByUserProfileAndReplyOnProductFeedback(UserProfile userProfile, ReplyOnProductFeedback replyOnProductFeedback);

    /**
     *
     * @param userProfile - user's profile
     * @return list of reactions
     */
    List<ReplyOnProductFeedbackReaction> findReplyOnProductFeedbackReactionsByUserProfile(UserProfile userProfile);
}
