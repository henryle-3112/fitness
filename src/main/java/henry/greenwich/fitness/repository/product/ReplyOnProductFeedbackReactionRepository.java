package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedbackReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReplyOnProductFeedbackReactionRepository extends JpaRepository<ReplyOnProductFeedbackReaction, Long> {

    String GET_REPLY_ON_PRODUCT_FEEDBACK_REACTIONS = "select * from "
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_TABLE + "" + " where (:userProfileId is null or "
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_TABLE + "."
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)";

    String GET_REPLY_ON_PRODUCT_FEEDBACK_REACTION = "select * from "
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_TABLE + "" + " where (:userProfileId is null or "
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_TABLE + "."
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:replyOnProductFeedbackId is null or "
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_TABLE + "."
            + Constants.REPLY_ON_PRODUCT_FEEDBACK_REACTION_REPLY_ON_PRODUCT_FEEDBACK_ID
            + " = :replyOnProductFeedbackId)";

    /**
     * @param userProfileId - user's profile's id that user want to get reactions of
     *                      reply on product's feedback
     * @return list of reactions of reply on product's feedback
     */
    @Query(nativeQuery = true, value = GET_REPLY_ON_PRODUCT_FEEDBACK_REACTIONS)
    List<Object> getReplyOnProductFeedbackReactions(@RequestParam("userProfileId") Integer userProfileId);

    /**
     * @param userProfileId            - user's profile's id that user want to get
     *                                 reaction of reply on product's feedback
     * @param replyOnProductFeedbackId - id of reply on product's feedback that user
     *                                 want to get reaction
     * @return reaction of reply on product's feedback
     */
    @Query(nativeQuery = true, value = GET_REPLY_ON_PRODUCT_FEEDBACK_REACTION)
    List<Object> getReplyOnProductFeedbackReaction(@RequestParam("userProfileId") Long userProfileId,
                                                   @RequestParam("replyOnProductFeedbackId") Long replyOnProductFeedbackId);
}
