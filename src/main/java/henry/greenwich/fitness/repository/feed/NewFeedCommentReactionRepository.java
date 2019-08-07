package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface NewFeedCommentReactionRepository extends JpaRepository<NewFeedCommentReaction, Long> {

        String GET_NEW_FEED_COMMENT_REACTIONS = "select * from " + Constants.NEW_FEED_COMMENT_REACTION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.NEW_FEED_COMMENT_REACTION_TABLE + "."
                        + Constants.NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)";

        String GET_NEW_FEED_COMMENT_REACTION = "select * from " + Constants.NEW_FEED_COMMENT_REACTION_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.NEW_FEED_COMMENT_REACTION_TABLE + "."
                        + Constants.NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:newFeedCommentId is null or " + Constants.NEW_FEED_COMMENT_REACTION_TABLE + "."
                        + Constants.NEW_FEED_COMMENT_REACTION_NEW_FEED_COMMENT_ID + " = :newFeedCommentId)";

        /**
         * @param userProfileId - user's profile's id that user want to get newfeed's
         *                      comment's reactions
         * @return list of newfeed's comment's reactions
         */
        @Query(nativeQuery = true, value = GET_NEW_FEED_COMMENT_REACTIONS)
        List<Object> getNewFeedCommentReactions(@RequestParam("userProfileId") Integer userProfileId);

        /**
         * @param userProfileId    - user's profile's id that user want to get newfeed's
         *                         comment's reaction
         * @param newFeedCommentId - newfeed's comment's id that user want to get
         *                         newfeed's comment's reaction
         * @return selected newfeed's comment's reaction
         */
        @Query(nativeQuery = true, value = GET_NEW_FEED_COMMENT_REACTION)
        List<Object> getNewFeedCommentReaction(@RequestParam("userProfileId") Long userProfileId,
                        @RequestParam("newFeedCommentId") Long newFeedCommentId);
}
