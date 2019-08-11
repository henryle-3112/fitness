package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.ReplyOnCoachFeedbackReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnCoachFeedbackReactionService {
    /**
     * replyOnCoachFeedbackRepository - interact reply on coach feedback reaction
     */
    private ReplyOnCoachFeedbackReactionRepository replyOnCoachFeedbackReactionRepository;

    /**
     * @param replyOnCoachFeedbackReactionRepository - inject replyOnCoachFeedbackRepository
     */
    public ReplyOnCoachFeedbackReactionService(ReplyOnCoachFeedbackReactionRepository replyOnCoachFeedbackReactionRepository) {
        this.replyOnCoachFeedbackReactionRepository = replyOnCoachFeedbackReactionRepository;
    }

    /**
     * @param replyOnCoachFeedback - replyOnCoachFeedback
     * @param reaction             - reaction
     * @return number of reply on coach feedback reactions
     */
    public int countReplyOnCoachFeedbackReactionsByReplyOnCoachFeedbackAndReaction(ReplyOnCoachFeedback replyOnCoachFeedback, int reaction) {
        return this.replyOnCoachFeedbackReactionRepository.countReplyOnCoachFeedbackReactionsByReplyOnCoachFeedbackAndReaction(
                replyOnCoachFeedback, reaction);
    }

    /**
     * @param replyOnCoachFeedbackReaction - replyOnCoachFeedbackReaction
     * @return inserted replyOnCoachFeedbackReaction
     */
    public ReplyOnCoachFeedbackReaction addReplyOnCoachFeedbackReaction(ReplyOnCoachFeedbackReaction replyOnCoachFeedbackReaction) {
        // check reply on coach feedback reaction existed in the database or not
        // if not create new one, if yes, update coach feedback reaction
        ReplyOnCoachFeedbackReaction selectedReplyOnCoachFeedbackReaction = this.replyOnCoachFeedbackReactionRepository
                .findReplyOnCoachFeedbackReactionByUserProfileAndReplyOnCoachFeedback(
                        replyOnCoachFeedbackReaction.userProfile,
                        replyOnCoachFeedbackReaction.replyOnCoachFeedback
                );
        if (selectedReplyOnCoachFeedbackReaction != null) {
            selectedReplyOnCoachFeedbackReaction.setReaction(replyOnCoachFeedbackReaction.getReaction());
            return this.replyOnCoachFeedbackReactionRepository.saveAndFlush(selectedReplyOnCoachFeedbackReaction);
        }
        return this.replyOnCoachFeedbackReactionRepository.saveAndFlush(replyOnCoachFeedbackReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of replies reactions
     */
    public List<ReplyOnCoachFeedbackReaction> getReplyOnCoachFeedbackReactionsByUserProfile(UserProfile userProfile) {
        return this.replyOnCoachFeedbackReactionRepository.findReplyOnCoachFeedbackReactionsByUserProfile(userProfile);
    }


}

