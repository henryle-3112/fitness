package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFeedReactionService {
    /**
     * newFeedReactionRepository - interact new's feed's reaction's data
     */
    private NewFeedReactionRepository newFeedReactionRepository;

    /**
     * @param newFeedRepository - interact with new's feed's repository
     */
    public NewFeedReactionService(NewFeedReactionRepository newFeedRepository) {
        this.newFeedReactionRepository = newFeedRepository;
    }

    /**
     *
     * @param newFeed - new's feed
     * @param reaction - reaction
     * @return number of new's feed's reactions
     */
    public int countNewFeedReactionsByNewFeedAndReaction(NewFeed newFeed, int reaction) {
        return this.newFeedReactionRepository.countNewFeedReactionsByNewFeedAndReaction(newFeed, reaction);
    }

    /**
     *
     * @param newFeedReaction - new's feed's reaction
     * @return selected new's feed's reaction
     */
    public NewFeedReaction addNewFeedReaction(NewFeedReaction newFeedReaction) {
        // check new's feed's reaction existed in the database or not
        // if not create new one, if yes, update new's feed's reaction
        NewFeedReaction selectedNewFeedReaction = this.newFeedReactionRepository.findNewFeedReactionByUserProfileAndNewFeed(
                newFeedReaction.userProfile,
                newFeedReaction.newFeed
        );
        if (selectedNewFeedReaction != null) {
            selectedNewFeedReaction.setReaction(newFeedReaction.getReaction());
            return this.newFeedReactionRepository.saveAndFlush(selectedNewFeedReaction);
        }
        return this.newFeedReactionRepository.saveAndFlush(newFeedReaction);
    }

    /**
     *
     * @param userProfile - user's profile
     * @return list of new' feed's reactions
     */
    public List<NewFeedReaction> findNewFeedReactionsByUserProfile (UserProfile userProfile) {
        return this.newFeedReactionRepository.findNewFeedReactionsByUserProfile(userProfile);
    }



}
