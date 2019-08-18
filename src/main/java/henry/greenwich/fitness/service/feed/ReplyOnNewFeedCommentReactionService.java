package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.ReplyOnNewFeedCommentReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyOnNewFeedCommentReactionService {
    private ReplyOnNewFeedCommentReactionRepository replyOnNewFeedCommentReactionRepository;
    private UserProfileService userProfileService;
    private ReplyOnNewFeedCommentService replyOnNewFeedCommentService;

    /**
     * @param replyOnNewFeedCommentReactionRepository - inject
     *                                                replyOnNewFeedCommentReactionRepository
     * @param userProfileService                      - inject userProfileService
     * @param replyOnNewFeedCommentService            - inject
     *                                                replyOnNewFeedCommentService
     */
    public ReplyOnNewFeedCommentReactionService(ReplyOnNewFeedCommentReactionRepository replyOnNewFeedCommentReactionRepository,
                                                UserProfileService userProfileService,
                                                ReplyOnNewFeedCommentService replyOnNewFeedCommentService) {
        this.replyOnNewFeedCommentReactionRepository = replyOnNewFeedCommentReactionRepository;
        this.userProfileService = userProfileService;
        this.replyOnNewFeedCommentService = replyOnNewFeedCommentService;
    }

    /**
     * @param replyOnNewFeedCommentReaction - reaction of reply on newfeed's comment
     *                                      that user want to add to the database
     * @return inserted reaction of reply on newfeed's comment
     */
    public ReplyOnNewFeedCommentReaction addReplyOnNewFeedCommentReaction(
            ReplyOnNewFeedCommentReaction replyOnNewFeedCommentReaction) {
        // check reply on newfeed's comment reaction existed in the database or not
        // if not create new one, if yes, update reply on newfeed's comment reaction
        Long userProfileId = replyOnNewFeedCommentReaction.getUserProfile().getId();
        Long replyOnNewFeedCommentId = replyOnNewFeedCommentReaction.getReplyOnNewFeedComment().getId();
        ReplyOnNewFeedCommentReaction selectedReplyOnNewFeedCommentReaction = this
                .getReplyOnNewFeedCommentReaction(userProfileId, replyOnNewFeedCommentId);
        if (selectedReplyOnNewFeedCommentReaction != null) {
            selectedReplyOnNewFeedCommentReaction.setReaction(replyOnNewFeedCommentReaction.getReaction());
            return this.replyOnNewFeedCommentReactionRepository.saveAndFlush(selectedReplyOnNewFeedCommentReaction);
        }
        return this.replyOnNewFeedCommentReactionRepository.saveAndFlush(replyOnNewFeedCommentReaction);
    }

    /**
     * @param userProfileId           - user's profile' id that user want to get
     * @param replyOnNewFeedCommentId - id of reply on newfeed's comment that user
     *                                want to get
     * @return selected reaction of reply on newfeed's comment
     */
    private ReplyOnNewFeedCommentReaction getReplyOnNewFeedCommentReaction(Long userProfileId,
                                                                           Long replyOnNewFeedCommentId) {
        List<Object> replyOnNewFeedCommentReactionObjectList = this.replyOnNewFeedCommentReactionRepository
                .getReplyOnNewFeedCommentReaction(userProfileId, replyOnNewFeedCommentId);
        // because userProfileId and replyOnNewFeedCommentId. Therefore, there is just
        // one result in the list.
        // That's why .get(0) will be used in this situation
        return replyOnNewFeedCommentReactionObjectList.size() > 0 ? this.getReplyOnNewFeedCommentReactionsFromObjectList(replyOnNewFeedCommentReactionObjectList).get(0) : null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get
     * @return list of reactions of reply on newfeed's comments
     */
    public List<ReplyOnNewFeedCommentReaction> getReplyOnNewFeedCommentReactions(Integer userProfileId) {
        List<Object> replyOnNewfeedCommentReactionsObjectList = this.replyOnNewFeedCommentReactionRepository
                .getReplyOnNewFeedCommentReactions(userProfileId);
        return this.getReplyOnNewFeedCommentReactionsFromObjectList(replyOnNewfeedCommentReactionsObjectList);
    }

    /**
     * @param replyOnNewFeedCommentReactionsObjectList - list of reactions of reply
     *                                                 on newfeed's comment object
     *                                                 that user want to convert to
     *                                                 list of reactions of reply on
     *                                                 newfeed's comment
     * @return list of reactions of reply on newfeed's comment
     */
    private List<ReplyOnNewFeedCommentReaction> getReplyOnNewFeedCommentReactionsFromObjectList(
            List<Object> replyOnNewFeedCommentReactionsObjectList) {
        List<ReplyOnNewFeedCommentReaction> replyOnNewFeedCommentReactions = new ArrayList<>();
        for (Object o : replyOnNewFeedCommentReactionsObjectList) {
            Object[] replyOnNewFeedCommentReactionObjectArr = (Object[]) o;
            ReplyOnNewFeedCommentReaction replyOnNewFeedCommentReaction = this
                    .createReplyOnNewFeedCommentReactionFromObjectArray(replyOnNewFeedCommentReactionObjectArr);
            replyOnNewFeedCommentReactions.add(replyOnNewFeedCommentReaction);
        }
        return replyOnNewFeedCommentReactions;
    }

    /**
     * @param replyOnNewFeedCommentReactionObjectArr - reaction of reply on
     *                                               newfeed's comment that user
     *                                               want to convert to reaction of
     *                                               reply on newfeed comment object
     * @return converted reply on newfeed's comment
     */
    private ReplyOnNewFeedCommentReaction createReplyOnNewFeedCommentReactionFromObjectArray(
            Object[] replyOnNewFeedCommentReactionObjectArr) {
        int replyOnNewFeedCommentReactionId = (int) replyOnNewFeedCommentReactionObjectArr[0];
        int replyOnNewFeedCommentReactionValue = (int) replyOnNewFeedCommentReactionObjectArr[1];
        int userProfileId = (int) replyOnNewFeedCommentReactionObjectArr[2];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int replyOnNewFeedCommentId = (int) replyOnNewFeedCommentReactionObjectArr[3];
        ReplyOnNewFeedComment replyOnNewFeedComment = this.getReplyOnNewFeedComment((long) replyOnNewFeedCommentId);
        return new ReplyOnNewFeedCommentReaction((long) replyOnNewFeedCommentReactionId,
                replyOnNewFeedCommentReactionValue, replyOnNewFeedComment, userProfile);
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
     * @param replyOnNewFeedCommentId - id of reply on newfeed's comment that user
     *                                want to get selected reply on newfeed's
     *                                comment
     * @return selected reply on newfeed's comment
     */
    private ReplyOnNewFeedComment getReplyOnNewFeedComment(Long replyOnNewFeedCommentId) {
        return this.replyOnNewFeedCommentService.getReplyOnNewFeedComment(replyOnNewFeedCommentId);
    }
}
