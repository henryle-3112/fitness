package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ReplyOnProductFeedbackRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyOnProductFeedbackService {
    private ReplyOnProductFeedbackRepository replyOnProductFeedbackRepository;
    private ProductFeedbackService productFeedbackService;
    private UserProfileService userProfileService;

    /**
     * @param replyOnProductFeedbackRepository - inject
     *                                         replyOnProductFeedbackRepository
     * @param productFeedbackService           - inject productFeedbackService
     * @param userProfileService               - inject userProfileService
     */
    public ReplyOnProductFeedbackService(ReplyOnProductFeedbackRepository replyOnProductFeedbackRepository,
                                         ProductFeedbackService productFeedbackService,
                                         UserProfileService userProfileService) {
        this.replyOnProductFeedbackRepository = replyOnProductFeedbackRepository;
        this.productFeedbackService = productFeedbackService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param productFeedbackId            - product's feedback's id that user want
     *                                     to get replies
     * @param replyOnProductFeedbackStatus - reply on product's feedback's status
     *                                     that user want to get replies
     * @return list of replies
     */
    public List<ReplyOnProductFeedback> getRepliesOnProductFeedback(Integer productFeedbackId,
                                                                    Integer replyOnProductFeedbackStatus) {
        List<Object> repliesOnProductFeedbackObjectList = this.replyOnProductFeedbackRepository
                .getRepliesOnProductFeedback(productFeedbackId, replyOnProductFeedbackStatus);
        return this.getRepliesOnProductFeedbackFromObjectList(repliesOnProductFeedbackObjectList);
    }

    /**
     * @param replyOnProductFeedback - reply on product's feedback that user want to
     *                               add to the database
     * @return inserted reply on product's feedback
     */
    public ReplyOnProductFeedback addReplyOnProductFeedback(ReplyOnProductFeedback replyOnProductFeedback) {
        return this.replyOnProductFeedbackRepository.saveAndFlush(replyOnProductFeedback);
    }

    /**
     * @param repliesOnProductFeedbackObjectList - replies on product's feedback
     *                                           object list that user want to
     *                                           convert to replies on product's
     *                                           feedback list
     * @return list of replies on product's feedback
     */
    private List<ReplyOnProductFeedback> getRepliesOnProductFeedbackFromObjectList(
            List<Object> repliesOnProductFeedbackObjectList) {
        List<ReplyOnProductFeedback> repliesOnProductFeedback = new ArrayList<>();
        for (Object o : repliesOnProductFeedbackObjectList) {
            Object[] replyOnProductFeedbackObjectArr = (Object[]) o;
            ReplyOnProductFeedback replyOnProductFeedback = this
                    .createReplyOnProductFeedbackFromObjectArr(replyOnProductFeedbackObjectArr);
            repliesOnProductFeedback.add(replyOnProductFeedback);
        }
        return repliesOnProductFeedback;
    }

    /**
     * @param replyOnProductFeedbackObjectArr - reply on product's feedback object
     *                                        array that user want to convert to
     *                                        reply on product's feedback object
     * @return converted reply on product's feedback
     */
    private ReplyOnProductFeedback createReplyOnProductFeedbackFromObjectArr(Object[] replyOnProductFeedbackObjectArr) {
        int replyOnProductFeedbackId = (int) replyOnProductFeedbackObjectArr[0];
        String replyOnProductFeedbackContent = (String) replyOnProductFeedbackObjectArr[1];
        int productFeedbackId = (int) replyOnProductFeedbackObjectArr[2];
        ProductFeedback productFeedback = this.getProductFeedback((long) productFeedbackId);
        int userProfileId = (int) replyOnProductFeedbackObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int replyOnProductFeedbackStatus = (int) replyOnProductFeedbackObjectArr[4];
        Date replyOnProductFeedbackCreatedDate = (Date) replyOnProductFeedbackObjectArr[5];
        int nReplyOnProductFeedbackLikes = (int) replyOnProductFeedbackObjectArr[6];
        int nReplyOnProductFeedbackDislikes = (int) replyOnProductFeedbackObjectArr[7];
        return new ReplyOnProductFeedback((long) replyOnProductFeedbackId, replyOnProductFeedbackContent,
                replyOnProductFeedbackStatus, replyOnProductFeedbackCreatedDate, productFeedback, userProfile,
                nReplyOnProductFeedbackLikes, nReplyOnProductFeedbackDislikes);
    }

    /**
     * @param productFeedbackId - product's feedback's id that user want to get
     *                          selected product's feedback
     * @return selected product's feedback
     */
    private ProductFeedback getProductFeedback(Long productFeedbackId) {
        return this.productFeedbackService.getProductFeedback(productFeedbackId);
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
     * @param replyOnProductFeedbackId - id of reply on product's feedback's id that
     *                                 user want to get selected reply on product's
     *                                 feedback
     * @return selected reply on product's feedback
     */
    public ReplyOnProductFeedback getReplyOnProductFeedback(Long replyOnProductFeedbackId) {
        return this.replyOnProductFeedbackRepository.findReplyOnProductFeedbackById(replyOnProductFeedbackId);
    }

    /**
     * @param replyOnProductFeedback - reply on product's feedback that user want to update
     * @return updated reply on product's feedback
     */
    public ReplyOnProductFeedback updateReplyOnProductFeedback(ReplyOnProductFeedback replyOnProductFeedback) {
        return this.replyOnProductFeedbackRepository.saveAndFlush(replyOnProductFeedback);
    }

}
