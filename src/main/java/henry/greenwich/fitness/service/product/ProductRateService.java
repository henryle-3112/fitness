package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductRate;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ProductRateRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProductRateService {
    private ProductRateRepository productRateRepository;
    private UserProfileService userProfileService;
    private ProductService productService;

    /**
     * @param productRateRepository - inject productRateRepository
     * @param userProfileService    - inject userProfileService
     * @param productService        - inject productService
     */
    public ProductRateService(ProductRateRepository productRateRepository, UserProfileService userProfileService,
            ProductService productService) {
        this.productRateRepository = productRateRepository;
        this.userProfileService = userProfileService;
        this.productService = productService;
    }

    /**
     * @param productRate - that user want to add to the database
     * @return productRate - that was inserted to the database
     */
    public ProductRate addProductRate(ProductRate productRate) {
        // check productRate existed in the database or not
        // if not, create new one, if yes, just update
        ProductRate selectedProductRate = this.getProductRateByUserProfileIdAndProductId(
                productRate.getUserProfile().getId(), productRate.getProduct().getId());
        if (selectedProductRate == null) {
            return this.productRateRepository.saveAndFlush(productRate);
        }
        selectedProductRate.setRate(productRate.getRate());
        return this.productRateRepository.saveAndFlush(productRate);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      product's rate
     * @param productId     - product's id that user want to get selected product's
     *                      rate
     * @return selected product's rate
     */
    public ProductRate getProductRateByUserProfileIdAndProductId(Long userProfileId, Long productId) {
        UserProfile userProfile = this.getUserProfile(userProfileId);
        Product product = this.getProduct(productId);
        return this.productRateRepository.findProductRateByUserProfileAndProduct(userProfile, product);
    }

    /**
     * @param userProfileId - user's profile's service that user want to get
     *                      selected user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param productId - product's id that user want to get selected product
     * @return selected product
     */
    private Product getProduct(Long productId) {
        return this.productService.getProduct(productId);
    }
}
