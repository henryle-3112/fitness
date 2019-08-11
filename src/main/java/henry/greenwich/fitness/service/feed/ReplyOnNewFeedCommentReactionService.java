package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.ReplyOnNewFeedCommentReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnNewFeedCommentReactionService {
    /**
     * replyOnNewFeedCommentReactionRepository - interact with reply on new feed comment reaction data
     */
    private ReplyOnNewFeedCommentReactionRepository replyOnNewFeedCommentReactionRepository;

    /**
     * @param replyOnNewFeedCommentReactionRepository - inject replyOnNewFeedCommentReactionRepository
     */
    public ReplyOnNewFeedCommentReactionService(ReplyOnNewFeedCommentReactionRepository replyOnNewFeedCommentReactionRepository) {
        this.replyOnNewFeedCommentReactionRepository = replyOnNewFeedCommentReactionRepository;
    }

    /**
     * @param replyOnNewFeedComment - replyOnNewFeedComment
     * @param reaction              - reaction
     * @return number of reactions
     */
    public int countReplyOnNewFeedCommentReactionsByReplyOnNewFeedCommentAndReaction(ReplyOnNewFeedComment replyOnNewFeedComment, int reaction) {
        return this.replyOnNewFeedCommentReactionRepository.countReplyOnNewFeedCommentReactionsByReplyOnNewFeedCommentAndReaction(
                replyOnNewFeedComment, reaction);
    }


    /**
     * @param replyOnNewFeedCommentReaction - replyOnNewFeedCommentReaction
     * @return inserted replyOnNewFeedCommentReaction
     */
    public ReplyOnNewFeedCommentReaction addReplyOnNewFeedCommentReaction(ReplyOnNewFeedCommentReaction replyOnNewFeedCommentReaction) {
        // check reply on new's feed's comment's reaction existed in the database or not
        // if not create new one, if yes, update reply on new's feed's comment's reaction
        ReplyOnNewFeedCommentReaction selectedReplyOnNewFeedCommentReaction = this.replyOnNewFeedCommentReactionRepository
                .findReplyOnNewFeedCommentReactionByUserProfileAndReplyOnNewFeedComment(
                        replyOnNewFeedCommentReaction.userProfile,
                        replyOnNewFeedCommentReaction.replyOnNewFeedComment
                );
        if (selectedReplyOnNewFeedCommentReaction != null) {
            selectedReplyOnNewFeedCommentReaction.setReaction(replyOnNewFeedCommentReaction.getReaction());
            return this.replyOnNewFeedCommentReactionRepository.saveAndFlush(selectedReplyOnNewFeedCommentReaction);
        }
        return this.replyOnNewFeedCommentReactionRepository.saveAndFlush(replyOnNewFeedCommentReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    public List<ReplyOnNewFeedCommentReaction> findReplyOnNewFeedCommentReactionsByUserProfile(UserProfile userProfile) {
        return this.replyOnNewFeedCommentReactionRepository.findReplyOnNewFeedCommentReactionsByUserProfile(userProfile);
    }
}
