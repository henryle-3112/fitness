package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewFeedCommentReactionRepository extends JpaRepository<NewFeedCommentReaction, Long> {

    /**
     * @param newFeedComment - new's feed's comment
     * @param reaction       - reaction
     * @return number of new's feed's comment's reactions
     */
    int countNewFeedCommentReactionsByNewFeedCommentAndReaction(NewFeedComment newFeedComment, int reaction);

    /**
     * @param userProfile    - user's profile
     * @param newFeedComment - new's feed's comment
     * @return selected newFeedCommentReaction
     */
    NewFeedCommentReaction findNewFeedCommentReactionByUserProfileAndNewFeedComment(UserProfile userProfile, NewFeedComment newFeedComment);

    /**
     * @param userProfile - user's profile
     * @return list of new's feed's comment's reactions
     */
    List<NewFeedCommentReaction> findNewFeedCommentReactionsByUserProfile(UserProfile userProfile);
}
