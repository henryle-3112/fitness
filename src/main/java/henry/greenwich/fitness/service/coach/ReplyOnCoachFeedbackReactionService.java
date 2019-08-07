package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.ReplyOnCoachFeedbackReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyOnCoachFeedbackReactionService {
    private ReplyOnCoachFeedbackReactionRepository replyOnCoachFeedbackReactionRepository;
    private ReplyOnCoachFeedbackService replyOnCoachFeedbackService;
    private UserProfileService userProfileService;

    /**
     * @param replyOnCoachFeedbackReactionRepository - inject
     *                                               replyOnCoachFeedbackReactionRepository
     * @param replyOnCoachFeedbackService            - inject
     *                                               replyOnCoachFeedbackService
     * @param userProfileService                     - inject userProfileService
     */
    public ReplyOnCoachFeedbackReactionService(
            ReplyOnCoachFeedbackReactionRepository replyOnCoachFeedbackReactionRepository,
            ReplyOnCoachFeedbackService replyOnCoachFeedbackService, UserProfileService userProfileService) {
        this.replyOnCoachFeedbackReactionRepository = replyOnCoachFeedbackReactionRepository;
        this.replyOnCoachFeedbackService = replyOnCoachFeedbackService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param replyOnCoachFeedbackReaction - reaction of reply on coach's feedback
     *                                     that user want to add to the database
     * @return inserted reaction of reply on coach's feedback
     */
    public ReplyOnCoachFeedbackReaction addReplyOnCoachFeedbackReaction(
            ReplyOnCoachFeedbackReaction replyOnCoachFeedbackReaction) {
        // check reply on coach's feedback reaction existed in the database or not
        // if not create new one, if yes, update reply on coach's feedback reaction
        Long userProfileId = replyOnCoachFeedbackReaction.getUserProfile().getId();
        Long replyOnCoachFeedbackId = replyOnCoachFeedbackReaction.getReplyOnCoachFeedback().getId();
        ReplyOnCoachFeedbackReaction selectedReplyOnCoachFeedbackReaction = this
                .getReplyOnCoachFeedbackReaction(userProfileId, replyOnCoachFeedbackId);
        if (selectedReplyOnCoachFeedbackReaction != null) {
            selectedReplyOnCoachFeedbackReaction.setReaction(replyOnCoachFeedbackReaction.getReaction());
            return this.replyOnCoachFeedbackReactionRepository.saveAndFlush(selectedReplyOnCoachFeedbackReaction);
        }
        return this.replyOnCoachFeedbackReactionRepository.saveAndFlush(replyOnCoachFeedbackReaction);
    }

    /**
     * @param userProfileId          - user's profile' id that user want to get
     *                               selected reaction of reply on coach's feedback
     * @param replyOnCoachFeedbackId - id of reply on coach's feedback that user
     *                               want to get selected reaction of reply on
     *                               coach's feedback
     * @return selected reaction of reply on coach's feedback
     */
    private ReplyOnCoachFeedbackReaction getReplyOnCoachFeedbackReaction(Long userProfileId,
            Long replyOnCoachFeedbackId) {
        List<Object> replyOnCoachFeedbackReactionsObjectList = this.replyOnCoachFeedbackReactionRepository
                .getReplyOnCoachFeedbackReaction(userProfileId, replyOnCoachFeedbackId);
        // because userProfileId and replyOnCoachFeedbackId. Therefore, the maximum
        // length of the list is one.
        // That's why .get(0) will be used in this situation
        if (replyOnCoachFeedbackReactionsObjectList.size() > 0) {
            return this.getReplyOnCoachFeedbackReactionsFromObjectList(replyOnCoachFeedbackReactionsObjectList).get(0);

        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on coach's feedbacks
     * @return list of reactions of reply on coach's feedbacks
     */
    public List<ReplyOnCoachFeedbackReaction> getReplyOnCoachFeedbackReactions(Integer userProfileId) {
        List<Object> replyOnCoachFeedbackReactionsObjectList = this.replyOnCoachFeedbackReactionRepository
                .getReplyOnCoachFeedbackReactions(userProfileId);
        return this.getReplyOnCoachFeedbackReactionsFromObjectList(replyOnCoachFeedbackReactionsObjectList);
    }

    /**
     * @param replyOnCoachFeedbackReactionsObjectList - list of reactions of reply
     *                                                on coach's feedback object
     *                                                that user want to convert to
     *                                                list of reactions of reply on
     *                                                coach's feedback
     * @return list of reactions of reply on coach's feedback
     */
    private List<ReplyOnCoachFeedbackReaction> getReplyOnCoachFeedbackReactionsFromObjectList(
            List<Object> replyOnCoachFeedbackReactionsObjectList) {
        List<ReplyOnCoachFeedbackReaction> replyOnCoachFeedbackReactions = new ArrayList<>();
        for (Object o : replyOnCoachFeedbackReactionsObjectList) {
            Object[] replyOnCoachFeedbackReactionObjectArr = (Object[]) o;
            ReplyOnCoachFeedbackReaction replyOnCoachFeedbackReaction = this
                    .createReplyOnCoachFeedbackReactionFromObjectList(replyOnCoachFeedbackReactionObjectArr);
            replyOnCoachFeedbackReactions.add(replyOnCoachFeedbackReaction);
        }
        return replyOnCoachFeedbackReactions;
    }

    /**
     * @param replyOnCoachFeedbackReactionObjectArr - reaction of reply on coach's
     *                                              feedback object array that user
     *                                              want to convert to reaction of
     *                                              reply on coach's feedback object
     * @return converted reaction of reply on coach's feedback object
     */
    private ReplyOnCoachFeedbackReaction createReplyOnCoachFeedbackReactionFromObjectList(
            Object[] replyOnCoachFeedbackReactionObjectArr) {
        int replyOnCoachFeedbackReactionId = (int) replyOnCoachFeedbackReactionObjectArr[0];
        int replyOnCoachFeedbackReactionValue = (int) replyOnCoachFeedbackReactionObjectArr[1];
        int replyOnCoachFeedbackId = (int) replyOnCoachFeedbackReactionObjectArr[2];
        ReplyOnCoachFeedback replyOnCoachFeedback = this.getReplyOnCoachFeedback((long) replyOnCoachFeedbackId);
        int userProfileId = (int) replyOnCoachFeedbackReactionObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new ReplyOnCoachFeedbackReaction((long) replyOnCoachFeedbackReactionId,
                replyOnCoachFeedbackReactionValue, replyOnCoachFeedback, userProfile);
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
     * @param replyOnCoachFeedbackId - id of reply on coach's feedback that user
     *                               want to get selected reply on coach's feedback
     * @return selected reply on coach's feedback
     */
    private ReplyOnCoachFeedback getReplyOnCoachFeedback(Long replyOnCoachFeedbackId) {
        return this.replyOnCoachFeedbackService.getRelyOnCoachFeedback(replyOnCoachFeedbackId);
    }
}
