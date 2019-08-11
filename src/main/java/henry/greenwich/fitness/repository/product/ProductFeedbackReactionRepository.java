package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeedbackReactionRepository extends JpaRepository<ProductFeedbackReaction, Long> {

    /**
     * @param productFeedback - product's feedback that user want to count
     * @param reaction        - reaction that user want to count
     * @return number of reactions
     */
    int countProductFeedbackReactionsByProductFeedbackAndReaction(ProductFeedback productFeedback, int reaction);

    /**
     * @param userProfile     - user's profile
     * @param productFeedback - product's feedback
     * @return selected product's feedback's reaction
     */
    ProductFeedbackReaction findProductFeedbackReactionByUserProfileAndProductFeedback(UserProfile userProfile, ProductFeedback productFeedback);

    /**
     *
     * @param userProfile - user's profile
     * @return list of reactions
     */
    List<ProductFeedbackReaction> findProductFeedbackReactionsByUserProfile(UserProfile userProfile);
}
