package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.PostCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface PostCommentReactionRepository extends JpaRepository<PostCommentReaction, Long> {

    String GET_POST_COMMENT_REACTIONS = "select * from " + Constants.POST_COMMENT_REACTION_TABLE + ""
            + " where (:userProfileId is null or " + Constants.POST_COMMENT_REACTION_TABLE + "."
            + Constants.POST_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)";

    String GET_POST_COMMENT_REACTION = "select * from " + Constants.POST_COMMENT_REACTION_TABLE + ""
            + " where (:userProfileId is null or " + Constants.POST_COMMENT_REACTION_TABLE + "."
            + Constants.POST_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:postCommentId is null or " + Constants.POST_COMMENT_REACTION_TABLE + "."
            + Constants.POST_COMMENT_REACTION_POST_COMMENT_ID + " = :postCommentId)";

    /**
     * @param userProfileId - user's profile's id that user want to get post's
     *                      comment's reactions
     * @return list of post's comment's reactions
     */
    @Query(nativeQuery = true, value = GET_POST_COMMENT_REACTIONS)
    List<Object> getPostCommentReactions(@RequestParam("userProfileId") int userProfileId);

    /**
     * @param userProfileId - user's profile's id that user want to get post's
     *                      comment's reaction
     * @param postCommentId - post's comment's id that user want to get post's
     *                      comment' id
     * @return selected post's comment's reaction
     */
    @Query(nativeQuery = true, value = GET_POST_COMMENT_REACTION)
    List<Object> getPostCommentReaction(@RequestParam("userProfileId") Long userProfileId,
                                        @RequestParam("postCommentId") Long postCommentId);
}
