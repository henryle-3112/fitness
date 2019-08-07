package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.PostCommentRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostCommentService {
    private PostCommentRepository postCommentRepository;
    private PostService postService;
    private UserProfileService userProfileService;

    /**
     * @param postCommentRepository      - inject postCommentRepository
     * @param postService                - inject postService
     * @param userProfileService         - inject userProfileService
     */
    public PostCommentService(PostCommentRepository postCommentRepository,
                              PostService postService,
                              UserProfileService userProfileService) {
        this.postCommentRepository = postCommentRepository;
        this.postService = postService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param postId            - post's id that user want to get post's comments
     * @param postCommentStatus - post's comment's status that user want to get post's comments
     * @return list of post's comments
     */
    public List<PostComment> getPostComments(Integer postId, Integer postCommentStatus) {
        List<Object> postCommentsObjectList = this.postCommentRepository.getPostComments(postId, postCommentStatus);
        return this.getPostCommentsFromObjectList(postCommentsObjectList);
    }

    /**
     * @param postCommentsObjectList - post comments object list that user want to convert to post comments list
     * @return list of post's comments
     */
    private List<PostComment> getPostCommentsFromObjectList(List<Object> postCommentsObjectList) {
        List<PostComment> postComments = new ArrayList<>();
        for (Object o : postCommentsObjectList) {
            Object[] postCommentObjectArr = (Object[]) o;
            PostComment postComment = this.createPostCommentFromObjectArray(postCommentObjectArr);
            postComments.add(postComment);
        }
        return postComments;
    }

    /**
     * @param postCommentObjectArr - post comment object arr that user want to convert to post comment
     * @return converted post's comment
     */
    private PostComment createPostCommentFromObjectArray(Object[] postCommentObjectArr) {
        String postCommentContent = (String) postCommentObjectArr[0];
        int postCommentId = (int) postCommentObjectArr[1];
        int postId = (int) postCommentObjectArr[2];
        Post post = this.getPost((long) postId);
        int userProfileId = (int) postCommentObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int postCommentStatus = (int) postCommentObjectArr[4];
        Date postCommentCreatedDate = (Date) postCommentObjectArr[5];
        int nPostCommentLikes = (int) postCommentObjectArr[6];
        int nPostCommentDislikes = (int) postCommentObjectArr[7];
        int nPostCommentReplies = (int) postCommentObjectArr[8];
        return new PostComment(
                (long) postCommentId,
                postCommentContent,
                post,
                userProfile,
                postCommentStatus,
                postCommentCreatedDate,
                nPostCommentLikes,
                nPostCommentDislikes,
                nPostCommentReplies
        );
    }


    /**
     * @param postId - post's id that user want to get selected post
     * @return selected post
     */
    private Post getPost(Long postId) {
        return this.postService.getPost(postId);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected user
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param postComment - that user wants to add to the database
     * @return inserted post's comment
     */
    public PostComment addPostComment(PostComment postComment) {
        return this.postCommentRepository.saveAndFlush(postComment);
    }

    /**
     * @param postCommentId - post's comment's id that user want to get selected post's comment
     * @return selected post's comment
     */
    public PostComment getPostComment(Long postCommentId) {
        return this.postCommentRepository.findPostCommentById(postCommentId);
    }
}
