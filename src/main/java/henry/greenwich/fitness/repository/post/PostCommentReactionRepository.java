package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.PostCommentReaction;
import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentReactionRepository extends JpaRepository<PostCommentReaction, Long> {

    /**
     * @param postComment - post's comment
     * @param reaction    - reaction
     * @return number of reactions
     */
    int countPostCommentReactionsByPostCommentAndReaction(PostComment postComment, int reaction);

    /**
     * @param userProfile - user's profile
     * @param postcomment - post's comment
     * @return selected post's comment's reaction
     */
    PostCommentReaction findPostCommentReactionByUserProfileAndPostComment(UserProfile userProfile, PostComment postcomment);


    /**
     * @param userProfile - user's profile
     * @return list of post's comment's reaction
     */
    List<PostCommentReaction> findPostCommentReactionsByUserProfile(UserProfile userProfile);
}
