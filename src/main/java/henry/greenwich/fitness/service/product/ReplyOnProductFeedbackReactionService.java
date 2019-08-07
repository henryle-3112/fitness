package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ReplyOnProductFeedbackReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyOnProductFeedbackReactionService {
    private ReplyOnProductFeedbackReactionRepository replyOnProductFeedbackReactionRepository;
    private UserProfileService userProfileService;
    private ReplyOnProductFeedbackService replyOnProductFeedbackService;

    /**
     * @param replyOnProductFeedbackReactionRepository - inject
     *                                                 replyOnProductFeedbackReactionRepository
     * @param userProfileService                       - inject
     *                                                 replyOnProductFeedbackReactionRepository
     * @param replyOnProductFeedbackService            - inject
     *                                                 replyOnProductFeedbackReactionRepository
     */
    public ReplyOnProductFeedbackReactionService(
            ReplyOnProductFeedbackReactionRepository replyOnProductFeedbackReactionRepository,
            UserProfileService userProfileService, ReplyOnProductFeedbackService replyOnProductFeedbackService) {
        this.replyOnProductFeedbackReactionRepository = replyOnProductFeedbackReactionRepository;
        this.userProfileService = userProfileService;
        this.replyOnProductFeedbackService = replyOnProductFeedbackService;
    }

    /**
     * @param replyOnProductFeedbackReaction - reply on product's feedbac's reaction
     *                                       that user want to add to reply on
     *                                       product's feedback's reaction
     * @return inserted reply on product's feedback's reaction
     */
    public ReplyOnProductFeedbackReaction addReplyOnProductFeedbackReaction(
            ReplyOnProductFeedbackReaction replyOnProductFeedbackReaction) {
        // check reply on product's feedback's reaction existed in the database or not
        // if not create new one, if yes, update reply on product's feedback's reaction
        Long userProfileId = replyOnProductFeedbackReaction.getUserProfile().getId();
        Long replyOnProductFeedbackId = replyOnProductFeedbackReaction.getReplyOnProductFeedback().getId();
        ReplyOnProductFeedbackReaction selectedReplyOnProductFeedbackReaction = this
                .getReplyOnProductFeedbackReaction(userProfileId, replyOnProductFeedbackId);
        if (selectedReplyOnProductFeedbackReaction != null) {
            selectedReplyOnProductFeedbackReaction.setReaction(replyOnProductFeedbackReaction.getReaction());
            return this.replyOnProductFeedbackReactionRepository.saveAndFlush(selectedReplyOnProductFeedbackReaction);
        }
        return this.replyOnProductFeedbackReactionRepository.saveAndFlush(replyOnProductFeedbackReaction);
    }

    private ReplyOnProductFeedbackReaction getReplyOnProductFeedbackReaction(Long userProfileId,
            Long replyOnProductFeedbackId) {
        List<Object> replyOnProductFeedbackReactionsObjectList = this.replyOnProductFeedbackReactionRepository
                .getReplyOnProductFeedbackReaction(userProfileId, replyOnProductFeedbackId);
        // because userProfileId and replyOnProductFeedbackId are unique. Therefore, the
        // maximum length of the list is one.
        // That's why .get(0) will be used in this situation
        if (replyOnProductFeedbackReactionsObjectList.size() > 0) {
            return this.getReplyOnProductFeedbackReactionsFromObjectList(replyOnProductFeedbackReactionsObjectList)
                    .get(0);

        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on product's feedback
     * @return list of reactions of reply on product's feedback
     */
    public List<ReplyOnProductFeedbackReaction> getReplyOnProductFeedbackReactions(Integer userProfileId) {
        List<Object> replyOnProductFeedbackReactionsObjectList = this.replyOnProductFeedbackReactionRepository
                .getReplyOnProductFeedbackReactions(userProfileId);
        return this.getReplyOnProductFeedbackReactionsFromObjectList(replyOnProductFeedbackReactionsObjectList);
    }

    /**
     * @param replyOnProductFeedbackReactionsObjectList - list of reactions of reply
     *                                                  on product's feedback object
     *                                                  list that user want to
     *                                                  convert to list of reactions
     *                                                  of reply on product's
     *                                                  feedback
     * @return list of reactions of reply on product's feedback
     */
    private List<ReplyOnProductFeedbackReaction> getReplyOnProductFeedbackReactionsFromObjectList(
            List<Object> replyOnProductFeedbackReactionsObjectList) {
        List<ReplyOnProductFeedbackReaction> replyOnProductFeedbackReactions = new ArrayList<>();
        for (Object o : replyOnProductFeedbackReactionsObjectList) {
            Object[] replyOnProductFeedbackReactionObjectArr = (Object[]) o;
            ReplyOnProductFeedbackReaction replyOnProductFeedbackReaction = this
                    .createReplyOnProductFeedbackReactionFromObjectArray(replyOnProductFeedbackReactionObjectArr);
            replyOnProductFeedbackReactions.add(replyOnProductFeedbackReaction);
        }
        return replyOnProductFeedbackReactions;
    }

    /**
     * @param replyOnProductFeedbackReactionObjectArr - reaction of reply on
     *                                                product's feedback object
     *                                                array that user want to
     *                                                convert to reaction of reply
     *                                                on product's feedback object
     * @return converted reaction of reply on product's feedback object
     */
    private ReplyOnProductFeedbackReaction createReplyOnProductFeedbackReactionFromObjectArray(
            Object[] replyOnProductFeedbackReactionObjectArr) {
        int replyOnProductFeedbackReactionId = (int) replyOnProductFeedbackReactionObjectArr[0];
        int replyOnProductFeedbackId = (int) replyOnProductFeedbackReactionObjectArr[1];
        ReplyOnProductFeedback replyOnProductFeedback = this.getReplyOnProductFeedback((long) replyOnProductFeedbackId);
        int userProfileId = (int) replyOnProductFeedbackReactionObjectArr[2];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int reactionValue = (int) replyOnProductFeedbackReactionObjectArr[3];
        return new ReplyOnProductFeedbackReaction((long) replyOnProductFeedbackReactionId, reactionValue,
                replyOnProductFeedback, userProfile);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param replyOnProductFeedbackId - id of reply on product's feedback's id that
     *                                 user want to get selected reply on product's
     *                                 feedback
     * @return selected reply on product's feedback
     */
    private ReplyOnProductFeedback getReplyOnProductFeedback(Long replyOnProductFeedbackId) {
        return this.replyOnProductFeedbackService.getReplyOnProductFeedback(replyOnProductFeedbackId);
    }
}
