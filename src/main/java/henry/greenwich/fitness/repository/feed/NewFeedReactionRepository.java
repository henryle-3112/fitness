package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.NewFeedReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface NewFeedReactionRepository extends JpaRepository<NewFeedReaction, Long> {

    String GET_NEW_FEED_REACTIONS = "select * from " + Constants.NEW_FEED_REACTION_TABLE + ""
            + " where (:userProfileId is null or " + Constants.NEW_FEED_REACTION_TABLE + "."
            + Constants.NEW_FEED_REACTION_USER_PROFILE_ID + " = :userProfileId)";

    String GET_NEW_FEED_REACTION = "select * from " + Constants.NEW_FEED_REACTION_TABLE + ""
            + " where (:userProfileId is null or " + Constants.NEW_FEED_REACTION_TABLE + "."
            + Constants.NEW_FEED_REACTION_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:newFeedId is null or " + Constants.NEW_FEED_REACTION_TABLE + "."
            + Constants.NEW_FEED_REACTION_NEW_FEED_ID + " = :newFeedId)";

    /**
     * @param userProfileId - user's profile's id that user want to get newfeed's
     *                      comment's reactions
     * @return list of newfeed's comment's reactions
     */
    @Query(nativeQuery = true, value = GET_NEW_FEED_REACTIONS)
    List<Object> getNewFeedReactions(@RequestParam("userProfileId") Integer userProfileId);

    /**
     * @param userProfileId - user's profile is that user want to get newfeed's
     *                      reaction
     * @param newFeedId     - newfeed's id that user want to get newfeed's reaction
     * @return selected newfeed's reaction
     */
    @Query(nativeQuery = true, value = GET_NEW_FEED_REACTION)
    List<Object> getNewFeedReaction(@RequestParam("userProfileId") Long userProfileId,
                                    @RequestParam("newFeedId") Long newFeedId);
}
