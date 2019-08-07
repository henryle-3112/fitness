package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.ReplyOnPostCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReplyOnPostCommentReactionRepository extends JpaRepository<ReplyOnPostCommentReaction, Long> {

        String GET_REPLY_ON_POST_COMMENT_REACTIONS = "select * from " + Constants.REPLY_ON_POST_COMMENT_REACTION_TABLE
                        + "" + " where (:userProfileId is null or " + Constants.REPLY_ON_POST_COMMENT_REACTION_TABLE
                        + "." + Constants.REPLY_ON_POST_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)";

        String GET_REPLY_ON_POST_COMMENT_REACTION = "select * from " + Constants.REPLY_ON_POST_COMMENT_REACTION_TABLE
                        + "" + " where (:userProfileId is null or " + Constants.REPLY_ON_POST_COMMENT_REACTION_TABLE
                        + "." + Constants.REPLY_ON_POST_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:replyOnPostCommentId is null or " + Constants.REPLY_ON_POST_COMMENT_REACTION_TABLE
                        + "." + Constants.REPLY_ON_POST_COMMENT_REACTION_REPLY_ON_POST_COMMENT_ID
                        + " = :replyOnPostCommentId)";

        /**
         * @param userProfileId - user's profile's id that user want to get reactions of
         *                      reply on post's comment
         * @return list of reactions of reply on post's comment
         */
        @Query(nativeQuery = true, value = GET_REPLY_ON_POST_COMMENT_REACTIONS)
        List<Object> getReplyOnPostCommentReactions(@RequestParam("userProfileId") Integer userProfileId);

        /**
         * @param userProfileId        - user's profile's id that user want to get
         *                             reaction of reply on post's comment
         * @param replyOnPostCommentId - id of reply on post's comment that user want to
         *                             get reaction
         * @return reaction of reply on post's comment
         */
        @Query(nativeQuery = true, value = GET_REPLY_ON_POST_COMMENT_REACTION)
        List<Object> getReplyOnPostCommentReaction(@RequestParam("userProfileId") Long userProfileId,
                        @RequestParam("replyOnPostCommentId") Long replyOnPostCommentId);
}
