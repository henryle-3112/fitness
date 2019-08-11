package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.ReplyOnPostCommentReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnPostCommentReactionService {
    /**
     * replyOnPostCommentReactionRepository - interact with reply on post comment reaction data
     */
    private ReplyOnPostCommentReactionRepository replyOnPostCommentReactionRepository;

    /**
     * @param replyOnPostCommentReactionRepository - inject replyOnPostCommentReactionRepository
     */
    public ReplyOnPostCommentReactionService(ReplyOnPostCommentReactionRepository replyOnPostCommentReactionRepository) {
        this.replyOnPostCommentReactionRepository = replyOnPostCommentReactionRepository;
    }

    /**
     * @param replyOnPostComment - that user want to count reactions
     * @param reaction           - that user want to count
     * @return number of reactions
     */
    public int countNumberOfReplyOnPostCommentReactionsByReplyOnPostCommentAndReaction(ReplyOnPostComment replyOnPostComment, int reaction) {
        return this.replyOnPostCommentReactionRepository.countReplyOnPostCommentReactionsByReplyOnPostCommentAndReaction(
                replyOnPostComment, reaction);
    }


    /**
     * @param replyOnPostCommentReaction - replyOnPostCommentReaction
     * @return inserted replyOnPostCommentReaction
     */
    public ReplyOnPostCommentReaction addReplyOnPostCommentReaction(ReplyOnPostCommentReaction replyOnPostCommentReaction) {
        // check reply on post's comment's reaction existed in the database or not
        // if not create new one, if yes, update post's comment's reaction
        ReplyOnPostCommentReaction selectedReplyOnPosCommentReaction = this.replyOnPostCommentReactionRepository
                .findReplyOnPostCommentReactionByUserProfileAndReplyOnPostComment(
                        replyOnPostCommentReaction.userProfile,
                        replyOnPostCommentReaction.replyOnPostComment
                );
        if (selectedReplyOnPosCommentReaction != null) {
            selectedReplyOnPosCommentReaction.setReaction(replyOnPostCommentReaction.getReaction());
            return this.replyOnPostCommentReactionRepository.saveAndFlush(selectedReplyOnPosCommentReaction);
        }
        return this.replyOnPostCommentReactionRepository.saveAndFlush(replyOnPostCommentReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    public List<ReplyOnPostCommentReaction> getReplyOnPostCommentReactionsByUserProfile(UserProfile userProfile) {
        return this.replyOnPostCommentReactionRepository.findReplyOnPostCommentReactionsByUserProfile(userProfile);
    }
}
