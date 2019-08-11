package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ReplyOnProductFeedbackReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnProductFeedbackReactionService {
    /**
     * replyOnProductFeedbackReactionRepository - interact with reply on product feedback reaction data
     */
    private ReplyOnProductFeedbackReactionRepository replyOnProductFeedbackReactionRepository;

    /**
     * @param replyOnProductFeedbackReactionRepository - inject replyOnProductFeedbackReactionRepository
     */
    public ReplyOnProductFeedbackReactionService(ReplyOnProductFeedbackReactionRepository replyOnProductFeedbackReactionRepository) {
        this.replyOnProductFeedbackReactionRepository = replyOnProductFeedbackReactionRepository;
    }

    /**
     * @param replyOnProductFeedback - that user want to count reactions
     * @param reaction               - that user want to count
     * @return number of reactions
     */
    public int countNumberOfReplyOnProductFeedbackReactionsByReplyOnProductFeedbackAndReaction(ReplyOnProductFeedback replyOnProductFeedback, int reaction) {
        return this.replyOnProductFeedbackReactionRepository.countReplyOnProductFeedbackReactionsByReplyOnProductFeedbackAndReaction(replyOnProductFeedback, reaction);
    }


    /**
     * @param replyOnProductFeedbackReaction - replyOnProductFeedbackReaction
     * @return inserted replyOnProductFeedbackReaction
     */
    public ReplyOnProductFeedbackReaction addReplyOnProductFeedbackReaction(ReplyOnProductFeedbackReaction replyOnProductFeedbackReaction) {
        // check reply on product's feedback's reaction existed in the database or not
        // if not create new one, if yes, update product's feedback
        ReplyOnProductFeedbackReaction selectedReplyOnProductFeedbackReaction = this.replyOnProductFeedbackReactionRepository.findReplyOnProductFeedbackReactionByUserProfileAndReplyOnProductFeedback(
                replyOnProductFeedbackReaction.userProfile,
                replyOnProductFeedbackReaction.replyOnProductFeedback
        );
        if (selectedReplyOnProductFeedbackReaction != null) {
            selectedReplyOnProductFeedbackReaction.setReaction(replyOnProductFeedbackReaction.getReaction());
            return this.replyOnProductFeedbackReactionRepository.saveAndFlush(selectedReplyOnProductFeedbackReaction);
        }
        return this.replyOnProductFeedbackReactionRepository.saveAndFlush(replyOnProductFeedbackReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    public List<ReplyOnProductFeedbackReaction> getReplyOnProductFeedbackReactionsByUserProfile(UserProfile userProfile) {
        return this.replyOnProductFeedbackReactionRepository.findReplyOnProductFeedbackReactionsByUserProfile(userProfile);
    }
}
