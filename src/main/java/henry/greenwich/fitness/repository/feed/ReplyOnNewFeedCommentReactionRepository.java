package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnNewFeedCommentReactionRepository extends JpaRepository<ReplyOnNewFeedCommentReaction, Long> {

    /**
     * @param replyOnNewFeedComment - reply on new feed comment
     * @param reaction              - reaction
     * @return number of reactions
     */
    int countReplyOnNewFeedCommentReactionsByReplyOnNewFeedCommentAndReaction(ReplyOnNewFeedComment replyOnNewFeedComment, int reaction);


    /**
     * @param userProfile           - user's profile
     * @param replyOnNewFeedComment - reply on new feed comment
     * @return selected ReplyOnNewFeedCommentReaction
     */
    ReplyOnNewFeedCommentReaction findReplyOnNewFeedCommentReactionByUserProfileAndReplyOnNewFeedComment(
            UserProfile userProfile, ReplyOnNewFeedComment replyOnNewFeedComment);

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    List<ReplyOnNewFeedCommentReaction> findReplyOnNewFeedCommentReactionsByUserProfile(UserProfile userProfile);
}
