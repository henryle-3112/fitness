package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.service.product.ProductFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductFeedbackReactionController {
    private ProductFeedbackReactionService productFeedbackReactionService;

    /**
     * @param productFeedbackReactionService - inject productFeedbackReactionService
     */
    public ProductFeedbackReactionController(ProductFeedbackReactionService productFeedbackReactionService) {
        this.productFeedbackReactionService = productFeedbackReactionService;
    }

    /**
     * @param productFeedbackReaction - that user want to add to the database
     * @return inserted product's feedback
     */
    @PostMapping(value = "/feedback-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ProductFeedbackReaction addProductFeedbackReaction(
            @RequestBody ProductFeedbackReaction productFeedbackReaction) {
        return this.productFeedbackReactionService.addProductFeedbackReaction(productFeedbackReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      product's feedback's reactions
     * @return list of product's feedback's reactions
     */
    @GetMapping(value = "/users/{userProfileId}/feedback-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ProductFeedbackReaction> getProductFeedbackReactionsByUserProfile(@PathVariable Integer userProfileId) {
        return this.productFeedbackReactionService.getProductFeedbackReactions(userProfileId);
    }
}
