package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.PostCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.PostCommentReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostCommentReactionService {
    private PostCommentReactionRepository postCommentReactionRepository;
    private UserProfileService userProfileService;
    private PostCommentService postCommentService;

    /**
     * @param postCommentReactionRepository - inject postCommentReactionRepository
     * @param userProfileService            - inject userProfileService
     * @param postCommentService            - inject postCommentService
     */
    public PostCommentReactionService(PostCommentReactionRepository postCommentReactionRepository,
            UserProfileService userProfileService, PostCommentService postCommentService) {
        this.postCommentReactionRepository = postCommentReactionRepository;
        this.userProfileService = userProfileService;
        this.postCommentService = postCommentService;
    }

    /**
     * @param postCommentReaction - post's comment's reaction that user want to add
     *                            to the database
     * @return inserted post's comment' reaction
     */
    public PostCommentReaction addPostCommentReaction(PostCommentReaction postCommentReaction) {
        // check post's comment's reaction existed in the database or not
        // if not create new one, if yes, update post's comment's reaction
        Long userProfileId = postCommentReaction.getUserProfile().getId();
        Long postCommentId = postCommentReaction.getPostComment().getId();
        PostCommentReaction selectedPostCommentReaction = this.getPostCommentReaction(userProfileId, postCommentId);
        if (selectedPostCommentReaction != null) {
            selectedPostCommentReaction.setReaction(postCommentReaction.getReaction());
            return this.postCommentReactionRepository.saveAndFlush(selectedPostCommentReaction);
        }
        return this.postCommentReactionRepository.saveAndFlush(postCommentReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get post's
     *                      comment's reaction
     * @param postCommentId - post's comment's id that user want to get post's
     *                      comment's reaction
     * @return selected post's comment's reaction
     */
    private PostCommentReaction getPostCommentReaction(Long userProfileId, Long postCommentId) {
        List<Object> postCommentReactionsObjectList = this.postCommentReactionRepository
                .getPostCommentReaction(userProfileId, postCommentId);
        // because userProfileId and post's comment's id are unique. Therefore, the
        // maximum length of the list is one.
        // That's why .get(0) will be use in this situation
        if (postCommentReactionsObjectList.size() > 0) {
            return this.getPostCommentReactionsFromObjectList(postCommentReactionsObjectList).get(0);
        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get post's
     *                      comment's reactions
     * @return list of post's comment's reactions
     */
    public List<PostCommentReaction> getPostCommentReactions(Integer userProfileId) {
        List<Object> postCommentReactionsObjectList = this.postCommentReactionRepository
                .getPostCommentReactions(userProfileId);
        return this.getPostCommentReactionsFromObjectList(postCommentReactionsObjectList);
    }

    /**
     * @param postCommentReactionsObjectList - post's comment's reactions object
     *                                       list that user want to convert to
     *                                       post's comment's reactions list
     * @return list of reactions of post's comment
     */
    private List<PostCommentReaction> getPostCommentReactionsFromObjectList(
            List<Object> postCommentReactionsObjectList) {
        List<PostCommentReaction> postCommentReactions = new ArrayList<>();
        for (Object o : postCommentReactionsObjectList) {
            Object[] postCommentReactionArr = (Object[]) o;
            PostCommentReaction postCommentReaction = this
                    .createPostCommentReactionFromObjectArray(postCommentReactionArr);
            postCommentReactions.add(postCommentReaction);
        }
        return postCommentReactions;
    }

    /**
     * @param postCommentReactionArr - post's comment's reaction object array that
     *                               user want to convert to post's comment's
     *                               reaction
     * @return converted post's comment's reaction
     */
    private PostCommentReaction createPostCommentReactionFromObjectArray(Object[] postCommentReactionArr) {
        int eachPostCommentReactionId = (int) postCommentReactionArr[0];
        int eachPostCommentReactionValue = (int) postCommentReactionArr[1];
        int postCommentId = (int) postCommentReactionArr[2];
        PostComment postComment = this.getPostComment((long) postCommentId);
        int userProfileId = (int) postCommentReactionArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new PostCommentReaction((long) eachPostCommentReactionId, eachPostCommentReactionValue, postComment,
                userProfile);
    }

    /**
     * @param postCommentId - post's comment's id that user want to get selected
     *                      post's comment
     * @return selected post's comment
     */
    private PostComment getPostComment(Long postCommentId) {
        return this.postCommentService.getPostComment(postCommentId);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }
}
