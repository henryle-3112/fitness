package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewFeedReactionRepository extends JpaRepository<NewFeedReaction, Long> {

    /**
     *
     * @param newFeed - new's feed
     * @param reaction - reaction
     * @return number of reactions
     */
    int countNewFeedReactionsByNewFeedAndReaction(NewFeed newFeed, int reaction);

    /**
     *
     * @param userProfile - user's profile
     * @param newFeed - new's feeds
     * @return selected new's feed's reaction
     */
    NewFeedReaction findNewFeedReactionByUserProfileAndNewFeed(UserProfile userProfile, NewFeed newFeed);

    /**
     *
     * @param userProfile - user's profile
     * @return list of new's feed's reactions
     */
    List<NewFeedReaction> findNewFeedReactionsByUserProfile(UserProfile userProfile);
}
