package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.ReplyOnPostCommentRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyOnPostCommentService {
    private ReplyOnPostCommentRepository replyOnPostCommentRepository;
    private PostCommentService postCommentService;
    private UserProfileService userProfileService;

    /**
     * @param replyOnPostCommentRepository - inject
     *                                     replyOnPostCommentRepository
     * @param postCommentService           - inject postCommentService
     * @param userProfileService           - inject userProfileService
     */
    public ReplyOnPostCommentService(ReplyOnPostCommentRepository replyOnPostCommentRepository,
                                     PostCommentService postCommentService,
                                     UserProfileService userProfileService) {
        this.replyOnPostCommentRepository = replyOnPostCommentRepository;
        this.postCommentService = postCommentService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param postCommentId            - post's comment's id that user want to get
     *                                 replies
     * @param replyOnPostCommentStatus - reply on post's comment's status that user
     *                                 want to get replies
     * @return list of replies
     */
    public List<ReplyOnPostComment> getRepliesOnPostComment(Integer postCommentId, Integer replyOnPostCommentStatus) {
        List<Object> repliesOnPostCommentObjectList = this.replyOnPostCommentRepository
                .getRepliesOnPostComment(postCommentId, replyOnPostCommentStatus);
        return this.getRepliesOnPostCommentFromObjectList(repliesOnPostCommentObjectList);
    }

    /**
     * @param replyOnPostComment - reply on post's comment that user want to add to
     *                           the database
     * @return inserted reply on post's comment
     */
    public ReplyOnPostComment addReplyOnPostComment(ReplyOnPostComment replyOnPostComment) {
        return this.replyOnPostCommentRepository.saveAndFlush(replyOnPostComment);
    }

    /**
     * @param repliesOnPostCommentObjectList - replies on post's comment object list
     *                                       that user want to convert to replies on
     *                                       post's comment list
     * @return list of replies on post's comment
     */
    private List<ReplyOnPostComment> getRepliesOnPostCommentFromObjectList(
            List<Object> repliesOnPostCommentObjectList) {
        List<ReplyOnPostComment> repliesOnPostComment = new ArrayList<>();
        for (Object o : repliesOnPostCommentObjectList) {
            Object[] replyOnPostCommentObjectArr = (Object[]) o;
            ReplyOnPostComment replyOnPostComment = this
                    .createReplyOnPostCommentFromObjectArr(replyOnPostCommentObjectArr);
            repliesOnPostComment.add(replyOnPostComment);
        }
        return repliesOnPostComment;
    }

    /**
     * @param replyOnPostCommentObjectArr - reply on post's comment object array
     *                                    that user want to convert to reply on
     *                                    post's comment object
     * @return converted reply on post's comment
     */
    private ReplyOnPostComment createReplyOnPostCommentFromObjectArr(Object[] replyOnPostCommentObjectArr) {
        String replyOnPostCommentContent = (String) replyOnPostCommentObjectArr[0];
        int replyOnPostCommentId = (int) replyOnPostCommentObjectArr[1];
        int postCommentId = (int) replyOnPostCommentObjectArr[2];
        PostComment postComment = this.getPostComment((long) postCommentId);
        int userProfileId = (int) replyOnPostCommentObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int replyOnPostCommentStatus = (int) replyOnPostCommentObjectArr[4];
        Date replyOnPostCommentCreatedDate = (Date) replyOnPostCommentObjectArr[5];
        int nReplyOnPostCommentLikes = (int) replyOnPostCommentObjectArr[6];
        int nReplyOnPostCommentDislikes = (int) replyOnPostCommentObjectArr[7];
        return new ReplyOnPostComment((long) replyOnPostCommentId, replyOnPostCommentContent, postComment, userProfile,
                replyOnPostCommentStatus, replyOnPostCommentCreatedDate, nReplyOnPostCommentLikes,
                nReplyOnPostCommentDislikes);
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

    /**
     * @param replyOnPostCommentId - id of reply on post's comment that user want to
     *                             get selected reply on post's comment
     * @return selected reply on post's comment
     */
    public ReplyOnPostComment getReplyOnPostComment(Long replyOnPostCommentId) {
        return this.replyOnPostCommentRepository.findReplyOnPostCommentsById(replyOnPostCommentId);
    }

    /**
     * @param replyOnPostComment - reply on post's comment that user want to update
     * @return updated reply on post's comment
     */
    public ReplyOnPostComment updateReplyOnPostComment(ReplyOnPostComment replyOnPostComment) {
        return this.replyOnPostCommentRepository.saveAndFlush((replyOnPostComment));
    }


}
