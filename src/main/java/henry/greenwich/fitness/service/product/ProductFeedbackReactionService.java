package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ProductFeedbackReactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFeedbackReactionService {
    /**
     * productFeedbackReactionRepository - interact with product' feedback's reaction data
     */
    private ProductFeedbackReactionRepository productFeedbackReactionRepository;

    /**
     * @param productFeedbackReactionRepository - inject productFeedbackReactionRepository
     */
    public ProductFeedbackReactionService(ProductFeedbackReactionRepository productFeedbackReactionRepository) {
        this.productFeedbackReactionRepository = productFeedbackReactionRepository;
    }

    /**
     * @param productFeedback - product's feedback that user's want to count number of reactions
     * @param reaction        - reaction that user want to count
     * @return number of reactions
     */
    public int countNumberOfProductFeedbackReactionsByProductFeedbackAndReaction(ProductFeedback productFeedback, int reaction) {
        return this.productFeedbackReactionRepository.countProductFeedbackReactionsByProductFeedbackAndReaction(productFeedback, reaction);
    }

    /**
     * @param productFeedbackReaction - that user want to add to the database
     * @return productFeedbackReaction - that was inserted to the database
     */
    public ProductFeedbackReaction addProductFeedbackReaction(ProductFeedbackReaction productFeedbackReaction) {
        // check product's feedback reaction existed in the database or not
        // if not create new one, if yes, update product's feedback
        ProductFeedbackReaction selectedProductFeedbackReaction = this.productFeedbackReactionRepository.findProductFeedbackReactionByUserProfileAndProductFeedback(
                productFeedbackReaction.userProfile,
                productFeedbackReaction.productFeedback
        );
        if (selectedProductFeedbackReaction != null) {
            selectedProductFeedbackReaction.setReaction(productFeedbackReaction.getReaction());
            return this.productFeedbackReactionRepository.saveAndFlush(selectedProductFeedbackReaction);
        }
        return this.productFeedbackReactionRepository.saveAndFlush(productFeedbackReaction);
    }

    /**
     * @param userProfile - user's profile
     * @return list of product's feedback reactions
     */
    public List<ProductFeedbackReaction> getProductFeedbackReactionsByUserProfile(UserProfile userProfile) {
        return this.productFeedbackReactionRepository.findProductFeedbackReactionsByUserProfile(userProfile);
    }
}
