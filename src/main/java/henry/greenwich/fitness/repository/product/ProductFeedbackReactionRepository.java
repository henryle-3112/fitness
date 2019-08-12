package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductFeedbackReactionRepository extends JpaRepository<ProductFeedbackReaction, Long> {

    String GET_PRODUCT_FEEDBACK_REACTIONS = "select * from " + Constants.PRODUCT_FEEDBACK_REACTION_TABLE + ""
            + " where (:userProfileId is null or " + Constants.PRODUCT_FEEDBACK_REACTION_TABLE + "."
            + Constants.PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)";

    String GET_PRODUCT_FEEDBACK_REACTION = "select * from " + Constants.PRODUCT_FEEDBACK_REACTION_TABLE + ""
            + " where (:userProfileId is null or " + Constants.PRODUCT_FEEDBACK_REACTION_TABLE + "."
            + Constants.PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:productFeedbackId is null or " + Constants.PRODUCT_FEEDBACK_REACTION_TABLE + "."
            + Constants.PRODUCT_FEEDBACK_REACTION_PRODUCT_FEEDBACK_ID + " = :productFeedbackId)";

    /**
     * @param userProfileId - user's profile's id that user want to get product's
     *                      feedback's reactions
     * @return list of product's feedback's reactions
     */
    @Query(nativeQuery = true, value = GET_PRODUCT_FEEDBACK_REACTIONS)
    List<Object> getProductFeedbackReactions(@RequestParam("userProfileId") int userProfileId);

    /**
     * @param userProfileId     - user's profile's id that user want to get
     *                          product's feedback's reaction
     * @param productFeedbackId - product's feedbacks' id that user want to get
     *                          product's feedback's id
     * @return selected product's feedback reaction
     */
    @Query(nativeQuery = true, value = GET_PRODUCT_FEEDBACK_REACTION)
    List<Object> getProductFeedbackReaction(@RequestParam("userProfileId") Long userProfileId,
                                            @RequestParam("productFeedbackId") Long productFeedbackId);
}
