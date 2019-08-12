package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReplyOnNewFeedCommentReactionRepository extends JpaRepository<ReplyOnNewFeedCommentReaction, Long> {

    String GET_REPLY_ON_NEW_FEED_COMMENT_REACTIONS = "select * from "
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE + "" + " where (:userProfileId is null or "
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE + "."
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)";

    String GET_REPLY_ON_NEW_FEED_COMMENT_REACTION = "select * from "
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE + "" + " where (:userProfileId is null or "
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE + "."
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:replyOnNewFeedCommentId is null or "
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE + "."
            + Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_REPLY_ON_NEW_FEED_COMMENT_ID
            + " = :replyOnNewFeedCommentId)";

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on newfeed's comment
     * @return list reactions of reply on newfeed's comment
     */
    @Query(nativeQuery = true, value = GET_REPLY_ON_NEW_FEED_COMMENT_REACTIONS)
    List<Object> getReplyOnNewFeedCommentReactions(@RequestParam("userProfileId") Integer userProfileId);

    /**
     * @param userProfileId           - user's profile's id that user want to get
     *                                reaction of reply on newfeed's comment
     * @param replyOnNewFeedCommentId - id of reply on newfeed's comment that user
     *                                want to get reaction
     * @return reaction of reply on newfeed's comment
     */
    @Query(nativeQuery = true, value = GET_REPLY_ON_NEW_FEED_COMMENT_REACTION)
    List<Object> getReplyOnNewFeedCommentReaction(@RequestParam("userProfileId") Long userProfileId,
                                                  @RequestParam("replyOnNewFeedCommentId") Long replyOnNewFeedCommentId);
}
