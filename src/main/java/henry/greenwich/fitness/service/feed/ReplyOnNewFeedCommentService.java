package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.ReplyOnNewFeedCommentRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyOnNewFeedCommentService {
    private ReplyOnNewFeedCommentRepository replyOnNewFeedCommentRepository;
    private NewFeedCommentService newFeedCommentService;
    private UserProfileService userProfileService;

    /**
     * @param replyOnNewFeedCommentRepository      - inject
     *                                             replyOnNewFeedCommentRepository
     * @param newFeedCommentService                - inject newFeedCommentService
     * @param userProfileService                   - inject userProfileService
     */
    public ReplyOnNewFeedCommentService(ReplyOnNewFeedCommentRepository replyOnNewFeedCommentRepository,
            @Lazy NewFeedCommentService newFeedCommentService, UserProfileService userProfileService,
            @Lazy ReplyOnNewFeedCommentReactionService replyOnNewFeedCommentReactionService) {
        this.replyOnNewFeedCommentRepository = replyOnNewFeedCommentRepository;
        this.newFeedCommentService = newFeedCommentService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param newFeedCommentId            - newfeed's comment's id that user want to
     *                                    get replies
     * @param replyOnNewFeedCommentStatus - status of reply on newfeed's comment
     *                                    that user want to get replies (this
     *                                    parameter could be optional)
     * @return list of replies
     */
    public List<ReplyOnNewFeedComment> getRepliesOnNewFeedComment(Integer newFeedCommentId,
            Integer replyOnNewFeedCommentStatus) {
        List<Object> repliesOnNewFeedCommentObjectList = this.replyOnNewFeedCommentRepository
                .getRepliesOnNewFeedComment(newFeedCommentId, replyOnNewFeedCommentStatus);
        return this.getRepliesOnNewFeedCommentFromObjectList(repliesOnNewFeedCommentObjectList);
    }

    /**
     * @param replyOnNewFeedComment - reply on newfeed's comment that user want to
     *                              add to the database
     * @return inserted reply on newfeed's comment
     */
    public ReplyOnNewFeedComment addReplyOnNewFeedComment(ReplyOnNewFeedComment replyOnNewFeedComment) {
        return this.replyOnNewFeedCommentRepository.saveAndFlush(replyOnNewFeedComment);
    }

    /**
     * @param repliesOnNewFeedCommentObjectList - replies on newfeed's comment
     *                                          object list that user want to
     *                                          convert to replies on newfeed's
     *                                          comment list
     * @return list of replies on newfeed's comment
     */
    private List<ReplyOnNewFeedComment> getRepliesOnNewFeedCommentFromObjectList(
            List<Object> repliesOnNewFeedCommentObjectList) {
        List<ReplyOnNewFeedComment> repliesOnNewFeedComment = new ArrayList<>();
        for (Object o : repliesOnNewFeedCommentObjectList) {
            Object[] replyOnNewFeedCommentObjectArr = (Object[]) o;
            ReplyOnNewFeedComment replyOnNewFeedComment = this
                    .createReplyOnNewFeedCommentFromObjectArray(replyOnNewFeedCommentObjectArr);
            repliesOnNewFeedComment.add(replyOnNewFeedComment);
        }
        return repliesOnNewFeedComment;
    }

    /**
     * @param replyOnNewFeedCommentObjectArr - reply on newfeed's comment object
     *                                       array that user want to convert to
     *                                       reply on newfeed's comment
     * @return converted reply on newfeed's comment
     */
    private ReplyOnNewFeedComment createReplyOnNewFeedCommentFromObjectArray(Object[] replyOnNewFeedCommentObjectArr) {
        int replyOnNewFeedCommentId = (int) replyOnNewFeedCommentObjectArr[0];
        String replyOnNewFeedCommentContent = (String) replyOnNewFeedCommentObjectArr[1];
        Date replyOnNewFeedCommentCreatedDate = (Date) replyOnNewFeedCommentObjectArr[2];
        int replyOnNewFeedCommentStatus = (int) replyOnNewFeedCommentObjectArr[3];
        int newFeedCommentId = (int) replyOnNewFeedCommentObjectArr[4];
        NewFeedComment newFeedComment = this.getNewFeedComment((long) newFeedCommentId);
        int userProfileId = (int) replyOnNewFeedCommentObjectArr[5];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int nReplyOnNewFeedCommentLikes = (int) replyOnNewFeedCommentObjectArr[6];
        int nReplyOnNewFeedCommentDislikes = (int) replyOnNewFeedCommentObjectArr[7];
        return new ReplyOnNewFeedComment((long) replyOnNewFeedCommentId, replyOnNewFeedCommentContent, newFeedComment,
                userProfile, replyOnNewFeedCommentStatus, replyOnNewFeedCommentCreatedDate, nReplyOnNewFeedCommentLikes,
                nReplyOnNewFeedCommentDislikes);
    }

    /**
     * @param newFeedCommentId - newfeed's comment's id that user want to get
     *                         selected newfeed's comment
     * @return selected newfeed's comment
     */
    private NewFeedComment getNewFeedComment(Long newFeedCommentId) {
        return this.newFeedCommentService.getNewFeedComment(newFeedCommentId);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get user's
     *                      profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param id - id of reply on newfeed's comment that user want to get
     * @return selected reply on newfeed's comment
     */
    public ReplyOnNewFeedComment getReplyOnNewFeedComment(Long id) {
        return this.replyOnNewFeedCommentRepository.findReplyOnNewFeedCommentById(id);
    }
}
