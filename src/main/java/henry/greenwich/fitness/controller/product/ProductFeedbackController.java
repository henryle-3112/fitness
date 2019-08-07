package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.service.product.ProductFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductFeedbackController {
    private ProductFeedbackService productFeedbackService;

    /**
     * @param productFeedbackService - inject productFeedbackService
     */
    public ProductFeedbackController(ProductFeedbackService productFeedbackService) {
        this.productFeedbackService = productFeedbackService;
    }

    /**
     * @param productId - product's id that user want to get product's feedbacks
     * @param status    - product's feedback's status that user want to get
     *                  product's feedbacks
     * @return list of product's feedbacks
     */
    @PostMapping(value = "/products/{productId}/feedbacks", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ProductFeedback> getProductFeedbacks(@PathVariable Integer productId,
            @RequestParam(required = false) Integer status) {
        return this.productFeedbackService.getProductFeedbacks(productId, status);
    }

    /**
     * @param productFeedback - that user want to add to the database
     * @return productFeedback - inserted product's feedback
     */
    @PostMapping(value = "/feedbacks", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ProductFeedback addProductFeedback(@RequestBody ProductFeedback productFeedback) {
        return this.productFeedbackService.addProductFeedback(productFeedback);
    }
}
