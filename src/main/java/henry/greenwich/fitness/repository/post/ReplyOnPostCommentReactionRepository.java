package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnPostCommentReactionRepository extends JpaRepository<ReplyOnPostCommentReaction, Long> {

    /**
     * @param replyOnPostComment - reply on post's comment
     * @param reaction           - reaction value
     * @return number of reactions
     */
    int countReplyOnPostCommentReactionsByReplyOnPostCommentAndReaction(ReplyOnPostComment replyOnPostComment, int reaction);

    /**
     * @param userProfile        - user's profile
     * @param replyOnPostComment - reply on post's comment
     * @return selected reply on post's comment reaction
     */
    ReplyOnPostCommentReaction findReplyOnPostCommentReactionByUserProfileAndReplyOnPostComment(UserProfile userProfile, ReplyOnPostComment replyOnPostComment);

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    List<ReplyOnPostCommentReaction> findReplyOnPostCommentReactionsByUserProfile(UserProfile userProfile);
}
