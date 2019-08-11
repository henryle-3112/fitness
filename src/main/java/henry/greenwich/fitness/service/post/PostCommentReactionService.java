package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.PostCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.PostCommentReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentReactionService {
    /**
     * postCommentReactionRepository - interact with post's comment's reaction
     */
    private PostCommentReactionRepository postCommentReactionRepository;

    /**
     * @param postCommentReactionRepository - inject postCommentReactionRepository
     */
    public PostCommentReactionService(PostCommentReactionRepository postCommentReactionRepository) {
        this.postCommentReactionRepository = postCommentReactionRepository;
    }

    /**
     * @param postComment - post's comment that user's want to count number of reactions
     * @param reaction    - reaction that user want to count
     * @return number of reactions
     */
    public int countNumberOfPostCommentReactionsByPostCommentAndReaction(PostComment postComment, int reaction) {
        return this.postCommentReactionRepository.countPostCommentReactionsByPostCommentAndReaction(postComment, reaction);
    }

    /**
     * @param postCommentReaction - that user want to add to the database
     * @return postCommentReaction - that was inserted to the database
     */
    public PostCommentReaction addPostCommentReaction(PostCommentReaction postCommentReaction) {
        // check post's comment reaction existed in the database or not
        // if not create new one, if yes, update post's comment reaction
        PostCommentReaction selectedPostCommentReaction = this.postCommentReactionRepository.findPostCommentReactionByUserProfileAndPostComment(
                postCommentReaction.userProfile,
                postCommentReaction.postComment
        );
        if (selectedPostCommentReaction != null) {
            selectedPostCommentReaction.setReaction(postCommentReaction.getReaction());
            return this.postCommentReactionRepository.saveAndFlush(selectedPostCommentReaction);
        }
        return this.postCommentReactionRepository.saveAndFlush(postCommentReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of post's comment's reaction
     */
    public List<PostCommentReaction> getPostCommentReactionsByUserProfile(UserProfile userProfile) {
        return this.postCommentReactionRepository.findPostCommentReactionsByUserProfile(userProfile);
    }
}
