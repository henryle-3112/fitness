package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedCommentReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFeedCommentReactionService {
    /**
     * newFeedCommentReactionRepository - interact with new's feed's comment's reaction's data
     */
    private NewFeedCommentReactionRepository newFeedCommentReactionRepository;

    /**
     * @param newFeedCommentReactionRepository - inject newFeedCommentReactionRepository
     */
    public NewFeedCommentReactionService(NewFeedCommentReactionRepository newFeedCommentReactionRepository) {
        this.newFeedCommentReactionRepository = newFeedCommentReactionRepository;
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @param reaction       - reaction
     * @return number of reactions
     */
    public int countNewFeedCommentReactionsByNewFeedCommentAndReaction(NewFeedComment newFeedComment, int reaction) {
        return this.newFeedCommentReactionRepository.countNewFeedCommentReactionsByNewFeedCommentAndReaction(newFeedComment, reaction);
    }

    /**
     * @param newFeedCommentReaction - new's feed's comment's reaction
     * @return inserted newFeedCommentReaction
     */
    public NewFeedCommentReaction addNewFeedCommentReaction(NewFeedCommentReaction newFeedCommentReaction) {
        // check new's feed's comment's reaction existed in the database or not
        // if not create new one, if yes, update new's feed's comment's reaction
        NewFeedCommentReaction selectedNewFeedCommentReaction = this.newFeedCommentReactionRepository.findNewFeedCommentReactionByUserProfileAndNewFeedComment(
                newFeedCommentReaction.userProfile,
                newFeedCommentReaction.newFeedComment
        );
        if (selectedNewFeedCommentReaction != null) {
            selectedNewFeedCommentReaction.setReaction(newFeedCommentReaction.getReaction());
            return this.newFeedCommentReactionRepository.saveAndFlush(selectedNewFeedCommentReaction);
        }
        return this.newFeedCommentReactionRepository.saveAndFlush(newFeedCommentReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of new's feed's comment's reactions
     */
    public List<NewFeedCommentReaction> findNewFeedCommentReactionsByUserProfile(UserProfile userProfile) {
        return this.newFeedCommentReactionRepository.findNewFeedCommentReactionsByUserProfile(userProfile);
    }
}
