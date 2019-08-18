package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewFeedReactionService {
    private NewFeedReactionRepository newFeedReactionRepository;
    private NewFeedService newFeedService;
    private UserProfileService userProfileService;

    /**
     * @param newFeedRepository  - inject newFeedRepository
     * @param newFeedService     - inject newFeedService
     * @param userProfileService - inject userProfileService
     */
    public NewFeedReactionService(NewFeedReactionRepository newFeedRepository,
                                  NewFeedService newFeedService,
                                  UserProfileService userProfileService) {
        this.newFeedReactionRepository = newFeedRepository;
        this.newFeedService = newFeedService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param newFeedReaction - newfeed's reactions that user want to add to the
     *                        database
     * @return inserted newfeed's reaction
     */
    public NewFeedReaction addNewFeedReaction(NewFeedReaction newFeedReaction) {
        // check new's feed's reaction existed in the database or not
        // if not create new one, if yes, update new's feed's reaction
        long userProfileId = newFeedReaction.getUserProfile().getId();
        long newFeedId = newFeedReaction.getNewFeed().getId();
        NewFeedReaction selectedNewFeedReaction = this.getNewFeedReaction(userProfileId, newFeedId);
        if (selectedNewFeedReaction != null) {
            selectedNewFeedReaction.setReaction(newFeedReaction.getReaction());
            return this.newFeedReactionRepository.saveAndFlush(selectedNewFeedReaction);
        }
        return this.newFeedReactionRepository.saveAndFlush(newFeedReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get newfeed's
     *                      reaction
     * @param newFeedId     - newfeed's id that user want to get newfeed's reaction
     * @return selected newfeed's reaction
     */
    private NewFeedReaction getNewFeedReaction(Long userProfileId, Long newFeedId) {
        List<Object> newFeedReactionObjectList = this.newFeedReactionRepository.getNewFeedReaction(userProfileId,
                newFeedId);
        // because userProfileId, newFeedId are unique. Therefore, the maximum length in
        // the list is one
        // That's why .get(0) will be used in this situation
        return newFeedReactionObjectList.size() > 0 ? this.getNewFeedReactionsFromObjectList(newFeedReactionObjectList).get(0) : null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get newfeed's
     *                      reactions
     * @return list of newfeed's reactions
     */
    public List<NewFeedReaction> getNewFeedReactions(Integer userProfileId) {
        List<Object> newFeedReactionsObjectList = this.newFeedReactionRepository.getNewFeedReactions(userProfileId);
        return this.getNewFeedReactionsFromObjectList(newFeedReactionsObjectList);
    }

    /**
     * @param newFeedReactionsObjectList - convert newfeed's reactions object list
     *                                   to newfeed's reactions list
     * @return list of newfeed's reactions
     */
    private List<NewFeedReaction> getNewFeedReactionsFromObjectList(List<Object> newFeedReactionsObjectList) {
        List<NewFeedReaction> newFeedReactions = new ArrayList<>();
        for (Object o : newFeedReactionsObjectList) {
            Object[] newFeedReactionObjectArr = (Object[]) o;
            NewFeedReaction newFeedReaction = this.createNewFeedReactionFromObjectArray(newFeedReactionObjectArr);
            newFeedReactions.add(newFeedReaction);
        }
        return newFeedReactions;
    }

    /**
     * @param newFeedReactionObjectArr - newfeed's reaction object array that user
     *                                 want to convert to newfeed's reaction object
     * @return converted newfeed's reaction
     */
    private NewFeedReaction createNewFeedReactionFromObjectArray(Object[] newFeedReactionObjectArr) {
        int newFeedReactionId = (int) newFeedReactionObjectArr[0];
        int newFeedReactionValue = (int) newFeedReactionObjectArr[1];
        int newFeedId = (int) newFeedReactionObjectArr[2];
        NewFeed newFeed = this.getNewFeed((long) newFeedId);
        int userProfileId = (int) newFeedReactionObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new NewFeedReaction((long) newFeedReactionId, newFeedReactionValue, newFeed, userProfile);
    }

    /**
     * @param newFeedId - newfeed's id that user want to get selected newfeed
     * @return selected newfeed
     */
    private NewFeed getNewFeed(Long newFeedId) {
        return this.newFeedService.getNewFeedById(newFeedId);
    }

    /**
     * @param userProfileId - user's profile id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

}
