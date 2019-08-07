package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ProductFeedbackRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductFeedbackService {
    private ProductFeedbackRepository productFeedbackRepository;
    private ProductService productService;
    private UserProfileService userProfileService;

    /**
     * @param productFeedbackRepository - inject productFeedbackRepository
     * @param productService            - inject productService
     * @param userProfileService        - inject userProfileService
     */
    public ProductFeedbackService(ProductFeedbackRepository productFeedbackRepository, ProductService productService,
            UserProfileService userProfileService) {
        this.productFeedbackRepository = productFeedbackRepository;
        this.productService = productService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param productId             - product's id that user want to get list of
     *                              product's feedbacks
     * @param productFeedbackStatus - product's feedback's status that user want to
     *                              get list of product's feedbacks
     * @return list of product's feedbacks
     */
    public List<ProductFeedback> getProductFeedbacks(Integer productId, Integer productFeedbackStatus) {
        List<Object> productFeedbacksObjectList = this.productFeedbackRepository.getProductFeedbacks(productId,
                productFeedbackStatus);
        return this.getProductFeedbacksFromObjectList(productFeedbacksObjectList);
    }

    /**
     * @param productFeedback - that user want to add to the database
     * @return productFeedback - inserted product's feedback
     */
    public ProductFeedback addProductFeedback(ProductFeedback productFeedback) {
        return this.productFeedbackRepository.saveAndFlush(productFeedback);
    }

    /**
     * @param productFeedbacksObjectList - product feedbacks object list that user
     *                                   want to convert to product feedbacks list
     * @return list of product's feedbacks
     */
    private List<ProductFeedback> getProductFeedbacksFromObjectList(List<Object> productFeedbacksObjectList) {
        List<ProductFeedback> productFeedbacks = new ArrayList<>();
        for (Object o : productFeedbacksObjectList) {
            Object[] productFeedbackObjectArr = (Object[]) o;
            ProductFeedback productFeedback = this.createProductFeedbackFromObjectArray(productFeedbackObjectArr);
            productFeedbacks.add(productFeedback);
        }
        return productFeedbacks;
    }

    /**
     * @param productFeedbackObjectArr - product feedback object arr that user want
     *                                 to convert to product feedback
     * @return converted product's feedback
     */
    private ProductFeedback createProductFeedbackFromObjectArray(Object[] productFeedbackObjectArr) {
        int productFeedbackId = (int) productFeedbackObjectArr[0];
        String productFeedbackContent = (String) productFeedbackObjectArr[1];
        Date productFeedbackCreatedDate = (Date) productFeedbackObjectArr[2];
        int productFeedbackStatus = (int) productFeedbackObjectArr[3];
        int userProfileId = (int) productFeedbackObjectArr[4];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int productId = (int) productFeedbackObjectArr[5];
        Product product = this.getProduct((long) productId);
        int nProductFeedbackLikes = (int) productFeedbackObjectArr[6];
        int nProductFeedbackDislikes = (int) productFeedbackObjectArr[7];
        int nProductFeedbackReplies = (int) productFeedbackObjectArr[8];
        return new ProductFeedback((long) productFeedbackId, productFeedbackContent, productFeedbackCreatedDate,
                productFeedbackStatus, userProfile, product, nProductFeedbackLikes, nProductFeedbackDislikes,
                nProductFeedbackReplies);
    }

    /**
     * @param productId - product's id that user want to get selected product
     * @return selected product
     */
    private Product getProduct(Long productId) {
        return this.productService.getProduct(productId);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param productFeedbackId - product's feedback's id that user want to get
     *                          selected product's feedback
     * @return selected product's feedback
     */
    public ProductFeedback getProductFeedback(Long productFeedbackId) {
        return this.productFeedbackRepository.findProductFeedbackById(productFeedbackId);
    }
}
