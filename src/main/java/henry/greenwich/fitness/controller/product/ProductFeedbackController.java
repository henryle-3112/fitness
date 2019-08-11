package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.product.ProductFeedbackService;
import henry.greenwich.fitness.service.product.ProductService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ProductFeedbackController {
    /**
     * interact with product feedback's data
     * interact with product's data
     * interact with user's profile's data
     */
    private ProductFeedbackService productFeedbackService;
    private ProductService productService;
    private UserProfileService userProfileService;

    /**
     * @param productFeedbackService - inject productFeedbackService
     */
    public ProductFeedbackController(ProductFeedbackService productFeedbackService,
                                     ProductService productService,
                                     UserProfileService userProfileService) {
        this.productFeedbackService = productFeedbackService;
        this.productService = productService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of product's feedbacks
     */
    @PostMapping(value = "/product/feedbacks/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductFeedback> getProductFeedbacks(@RequestBody Product product, @PathVariable int status) {
        return this.productFeedbackService.getProductFeedbacksByProduct(product, status);
    }

    /**
     * @param productFeedback - that user want to add to the database
     * @return productFeedback - that was inserted to the database
     */
    @PostMapping(value = "/product/feedbacks/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductFeedback addProductFeedback(@RequestBody ProductFeedback productFeedback) {
        return this.productFeedbackService.addProductFeedback(productFeedback);
    }
}
