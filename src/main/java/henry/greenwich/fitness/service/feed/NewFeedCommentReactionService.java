package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedCommentReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewFeedCommentReactionService {
    private NewFeedCommentReactionRepository newFeedCommentReactionRepository;
    private NewFeedCommentService newFeedCommentService;
    private UserProfileService userProfileService;

    /**
     * @param newFeedCommentReactionRepository - inject
     *                                         newFeedCommentReactionRepository
     * @param newFeedCommentService            - inject newFeedCommentService
     * @param userProfileService               - inject userProfileService
     */
    public NewFeedCommentReactionService(NewFeedCommentReactionRepository newFeedCommentReactionRepository,
                                         NewFeedCommentService newFeedCommentService,
                                         UserProfileService userProfileService) {
        this.newFeedCommentReactionRepository = newFeedCommentReactionRepository;
        this.newFeedCommentService = newFeedCommentService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param newFeedCommentReaction - new's feed's comment's reaction that user
     *                               want to add to the database
     * @return inserted newFeedCommentReaction
     */
    public NewFeedCommentReaction addNewFeedCommentReaction(NewFeedCommentReaction newFeedCommentReaction) {
        // check new's feed's comment's reaction existed in the database or not
        // if not create new one, if yes, update new's feed's comment's reaction
        Long userProfileId = newFeedCommentReaction.getUserProfile().getId();
        Long newFeedCommentId = newFeedCommentReaction.getNewFeedComment().getId();
        NewFeedCommentReaction selectedNewFeedCommentReaction = this.getNewFeedCommentReaction(userProfileId,
                newFeedCommentId);
        if (selectedNewFeedCommentReaction != null) {
            selectedNewFeedCommentReaction.setReaction(newFeedCommentReaction.getReaction());
            return this.newFeedCommentReactionRepository.saveAndFlush(selectedNewFeedCommentReaction);
        }
        return this.newFeedCommentReactionRepository.saveAndFlush(newFeedCommentReaction);
    }

    /**
     * @param userProfileId    - user's profile's id that user want to get newfeed's
     *                         comment's reaction
     * @param newFeedCommentId - newfeed's comment's id that user want to get
     *                         newfeed's comment's reaction
     * @return selected newfeed's comment's reaction
     */
    private NewFeedCommentReaction getNewFeedCommentReaction(Long userProfileId, Long newFeedCommentId) {
        List<Object> newFeedCommentReactionsObjectList = this.newFeedCommentReactionRepository
                .getNewFeedCommentReaction(userProfileId, newFeedCommentId);
        // because userProfileId and newFeedCommentId. Therefore, there is just one
        // result in the list.
        // That's why .get(0) will be used in this situation
        return this.getNewFeedCommentReactionsFromObjectList(newFeedCommentReactionsObjectList).get(0);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      newfeed's comment's reactions (1 is like and 0 is
     *                      dislike)
     * @return list of new's feed's comment's reactions
     */
    public List<NewFeedCommentReaction> getNewFeedCommentReactions(Integer userProfileId) {
        List<Object> newFeedCommentReactionsObjectList = this.newFeedCommentReactionRepository
                .getNewFeedCommentReactions(userProfileId);
        return this.getNewFeedCommentReactionsFromObjectList(newFeedCommentReactionsObjectList);
    }

    /**
     * @param newFeedCommentReactionsObjectList - convert newfeed's comment's
     *                                          reactions object list to newfeed's
     *                                          comment's reactions list
     * @return list of newfeed's comment's reactions
     */
    private List<NewFeedCommentReaction> getNewFeedCommentReactionsFromObjectList(
            List<Object> newFeedCommentReactionsObjectList) {
        List<NewFeedCommentReaction> newFeedCommentReactions = new ArrayList<>();
        for (Object o : newFeedCommentReactionsObjectList) {
            Object[] newFeedCommentReactionObjectArr = (Object[]) o;
            NewFeedCommentReaction newFeedCommentReaction = this
                    .createNewFeedCommentReactionFromObjectArray(newFeedCommentReactionObjectArr);
            newFeedCommentReactions.add(newFeedCommentReaction);
        }
        return newFeedCommentReactions;
    }

    /**
     * @param newFeedCommentReactionObjectArr - newfeed's comment's reaction object
     *                                        array that user want to convert to
     *                                        newfeed's comment's reactions object
     * @return converted newfeed's comment's reaction
     */
    private NewFeedCommentReaction createNewFeedCommentReactionFromObjectArray(
            Object[] newFeedCommentReactionObjectArr) {
        int newFeedCommentReactionId = (int) newFeedCommentReactionObjectArr[0];
        int newFeedCommentReactionValue = (int) newFeedCommentReactionObjectArr[1];
        int newFeedCommentId = (int) newFeedCommentReactionObjectArr[2];
        NewFeedComment newFeedComment = this.getNewFeedComment((long) newFeedCommentId);
        int userProfileId = (int) newFeedCommentReactionObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new NewFeedCommentReaction((long) newFeedCommentReactionId, newFeedCommentReactionValue, newFeedComment,
                userProfile);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param newFeedCommentId - newfeed's comment's id that user want to get
     *                         newfeed's comment
     * @return selected newfeed's comment
     */
    private NewFeedComment getNewFeedComment(Long newFeedCommentId) {
        return this.newFeedCommentService.getNewFeedComment(newFeedCommentId);
    }
}
