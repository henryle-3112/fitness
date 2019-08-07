package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ProductFeedbackReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductFeedbackReactionService {
    private ProductFeedbackReactionRepository productFeedbackReactionRepository;
    private ProductFeedbackService productFeedbackService;
    private UserProfileService userProfileService;

    /**
     * @param productFeedbackReactionRepository - inject
     *                                          productFeedbackReactionRepository
     * @param productFeedbackService            - inject productFeedbackService
     * @param userProfileService                - inject userProfileService
     */
    public ProductFeedbackReactionService(ProductFeedbackReactionRepository productFeedbackReactionRepository,
            ProductFeedbackService productFeedbackService, UserProfileService userProfileService) {
        this.productFeedbackReactionRepository = productFeedbackReactionRepository;
        this.productFeedbackService = productFeedbackService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param productFeedbackReaction - product's feedback's reaction that user want
     *                                to add to the database
     * @return inserted product's feedback's reaction
     */
    public ProductFeedbackReaction addProductFeedbackReaction(ProductFeedbackReaction productFeedbackReaction) {
        // check product's feedback's reaction existed in the database or not
        // if not create new one, if yes, update product's feedback's reaction
        Long userProfileId = productFeedbackReaction.getUserProfile().getId();
        Long productFeedbackId = productFeedbackReaction.getProductFeedback().getId();
        ProductFeedbackReaction selectedProductFeedbackReaction = this.getProductFeedbackReaction(userProfileId,
                productFeedbackId);
        if (selectedProductFeedbackReaction != null) {
            selectedProductFeedbackReaction.setReaction(productFeedbackReaction.getReaction());
            return this.productFeedbackReactionRepository.saveAndFlush(selectedProductFeedbackReaction);
        }
        return this.productFeedbackReactionRepository.saveAndFlush(productFeedbackReaction);
    }

    /**
     * @param userProfileId     - user's profile's id that user want to get
     *                          product's feedback's reaction
     * @param productFeedbackId - product's feedback's id that user want to get
     *                          product's feedback's reaction
     * @return selected product's feedback's reaction
     */
    private ProductFeedbackReaction getProductFeedbackReaction(Long userProfileId, Long productFeedbackId) {
        List<Object> productFeedbackReactionsObjectList = this.productFeedbackReactionRepository
                .getProductFeedbackReaction(userProfileId, productFeedbackId);
        // because userProfileId and product's feedback's id are unique. Therefore, the
        // maximum length of the list is one.
        // That's why .get(0) will be use in this situation
        if (productFeedbackReactionsObjectList.size() > 0) {
            return this.getProductFeedbackReactionsFromObjectList(productFeedbackReactionsObjectList).get(0);
        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get product's
     *                      feedback's reactions
     * @return list of product's feedback's reactions
     */
    public List<ProductFeedbackReaction> getProductFeedbackReactions(Integer userProfileId) {
        List<Object> productFeedbackReactionsObjectList = this.productFeedbackReactionRepository
                .getProductFeedbackReactions(userProfileId);
        return this.getProductFeedbackReactionsFromObjectList(productFeedbackReactionsObjectList);
    }

    /**
     * @param productFeedbackReactionsObjectList - product's feedback's reactions
     *                                           object list that user want to
     *                                           convert to product's feedback's
     *                                           reactions list
     * @return list of reactions of product's feedback
     */
    private List<ProductFeedbackReaction> getProductFeedbackReactionsFromObjectList(
            List<Object> productFeedbackReactionsObjectList) {
        List<ProductFeedbackReaction> productFeedbackReactions = new ArrayList<>();
        for (Object o : productFeedbackReactionsObjectList) {
            Object[] productFeedbackReactionArr = (Object[]) o;
            ProductFeedbackReaction productFeedbackReaction = this
                    .createProductFeedbackReactionFromObjectArray(productFeedbackReactionArr);
            productFeedbackReactions.add(productFeedbackReaction);
        }
        return productFeedbackReactions;
    }

    /**
     * @param productFeedbackReactionArr - product's feedback's reaction object
     *                                   array that user want to convert to
     *                                   product's feedback's reaction
     * @return converted product's feedback's reaction
     */
    private ProductFeedbackReaction createProductFeedbackReactionFromObjectArray(Object[] productFeedbackReactionArr) {
        int productFeedbackReactionId = (int) productFeedbackReactionArr[0];
        int productFeedbackId = (int) productFeedbackReactionArr[1];
        ProductFeedback productFeedback = this.getProductFeedback((long) productFeedbackId);
        int userProfileId = (int) productFeedbackReactionArr[2];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int reaction = (int) productFeedbackReactionArr[3];
        return new ProductFeedbackReaction((long) productFeedbackReactionId, reaction, productFeedback, userProfile);
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
}
