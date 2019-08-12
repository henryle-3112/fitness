package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.ReplyOnPostCommentReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyOnPostCommentReactionService {
    private ReplyOnPostCommentReactionRepository replyOnPostCommentReactionRepository;
    private ReplyOnPostCommentService replyOnPostCommentService;
    private UserProfileService userProfileService;

    /**
     * @param replyOnPostCommentReactionRepository - inject
     *                                             replyOnPostCommentReactionRepository
     * @param replyOnPostCommentService            - inject
     *                                             replyOnPostCommentService
     * @param userProfileService                   - inject userProfileService
     */
    public ReplyOnPostCommentReactionService(ReplyOnPostCommentReactionRepository replyOnPostCommentReactionRepository,
            ReplyOnPostCommentService replyOnPostCommentService, UserProfileService userProfileService) {
        this.replyOnPostCommentReactionRepository = replyOnPostCommentReactionRepository;
        this.replyOnPostCommentService = replyOnPostCommentService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param replyOnPostCommentReaction - reply on post's comment's reaction that
     *                                   user want to add to reply on post's
     *                                   comment's reaction
     * @return inserted reply on post's comment's reaction
     */
    public ReplyOnPostCommentReaction addReplyOnPostCommentReaction(
            ReplyOnPostCommentReaction replyOnPostCommentReaction) {
        // check reply on post's comment's reaction existed in the database or not
        // if not create new one, if yes, update reply on post's comment's reaction
        Long userProfileId = replyOnPostCommentReaction.getUserProfile().getId();
        Long replyOnPostCommentId = replyOnPostCommentReaction.getReplyOnPostComment().getId();
        ReplyOnPostCommentReaction selectedReplyOnPostCommentReaction = this
                .getReplyOnPostCommentReaction(userProfileId, replyOnPostCommentId);
        if (selectedReplyOnPostCommentReaction != null) {
            selectedReplyOnPostCommentReaction.setReaction(replyOnPostCommentReaction.getReaction());
            return this.replyOnPostCommentReactionRepository.saveAndFlush(selectedReplyOnPostCommentReaction);
        }
        return this.replyOnPostCommentReactionRepository.saveAndFlush(replyOnPostCommentReaction);
    }

    private ReplyOnPostCommentReaction getReplyOnPostCommentReaction(Long userProfileId, Long replyOnPostCommentId) {
        List<Object> replyOnPostCommentReactionsObjectList = this.replyOnPostCommentReactionRepository
                .getReplyOnPostCommentReaction(userProfileId, replyOnPostCommentId);
        // because userProfileId and replyOnPostCommentId are unique. Therefore, the
        // maximum length of the list is one.
        // That's why .get(0) will be used in this situation
        if (replyOnPostCommentReactionsObjectList.size() > 0) {
            return this.getReplyOnPostCommentReactionsFromObjectList(replyOnPostCommentReactionsObjectList).get(0);

        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on post's comment
     * @return list of reactions of reply on post's comment
     */
    public List<ReplyOnPostCommentReaction> getReplyOnPostCommentReactions(Integer userProfileId) {
        List<Object> replyOnPostCommentReactionsObjectList = this.replyOnPostCommentReactionRepository
                .getReplyOnPostCommentReactions(userProfileId);
        return this.getReplyOnPostCommentReactionsFromObjectList(replyOnPostCommentReactionsObjectList);
    }

    /**
     * @param replyOnPostCommentReactionsObjectList - list of reactions of reply on
     *                                              post's comment object list that
     *                                              user want to convert to list of
     *                                              reactions of reply on post's
     *                                              comment
     * @return list of reactions of reply on post's comment
     */
    private List<ReplyOnPostCommentReaction> getReplyOnPostCommentReactionsFromObjectList(
            List<Object> replyOnPostCommentReactionsObjectList) {
        List<ReplyOnPostCommentReaction> replyOnPostCommentReactions = new ArrayList<>();
        for (Object o : replyOnPostCommentReactionsObjectList) {
            Object[] replyOnPostCommentReactionObjectArr = (Object[]) o;
            ReplyOnPostCommentReaction replyOnPostCommentReaction = this
                    .createReplyOnPostCommentReactionFromObjectList(replyOnPostCommentReactionObjectArr);
            replyOnPostCommentReactions.add(replyOnPostCommentReaction);
        }
        return replyOnPostCommentReactions;
    }

    /**
     * @param replyOnPostCommentReactionObjectArr - reaction of reply on post's
     *                                            comment object array that user
     *                                            want to convert to reaction of
     *                                            reply on post's comment object
     * @return converted reaction of reply on post's comment object
     */
    private ReplyOnPostCommentReaction createReplyOnPostCommentReactionFromObjectList(
            Object[] replyOnPostCommentReactionObjectArr) {
        int replyOnPostCommentReactionId = (int) replyOnPostCommentReactionObjectArr[0];
        int replyOnPostCommentReactionValue = (int) replyOnPostCommentReactionObjectArr[1];
        int replyOnPostCommentId = (int) replyOnPostCommentReactionObjectArr[2];
        ReplyOnPostComment replyOnPostComment = this.getReplyOnPostComment((long) replyOnPostCommentId);
        int userProfileId = (int) replyOnPostCommentReactionObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new ReplyOnPostCommentReaction((long) replyOnPostCommentReactionId, replyOnPostCommentReactionValue,
                replyOnPostComment, userProfile);
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
     * @param replyOnPostCommentId - id of reply on post's comment that user want to
     *                             get selectedreply on post' comment
     * @return selected reply on post's comment
     */
    private ReplyOnPostComment getReplyOnPostComment(Long replyOnPostCommentId) {
        return this.replyOnPostCommentService.getReplyOnPostComment(replyOnPostCommentId);
    }
}
