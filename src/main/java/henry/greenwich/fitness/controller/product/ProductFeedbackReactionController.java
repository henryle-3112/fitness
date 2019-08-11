package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.product.ProductFeedbackReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductFeedbackReactionController {
    /**
     * productFeedbackReactionService - interact with product's feedback's reaction data
     */
    private ProductFeedbackReactionService productFeedbackReactionService;

    /**
     * @param productFeedbackReactionService - inject productFeedbackReactionService
     */
    public ProductFeedbackReactionController(ProductFeedbackReactionService productFeedbackReactionService) {
        this.productFeedbackReactionService = productFeedbackReactionService;
    }

    /**
     * @param productFeedbackReaction - that user want to add to the database
     * @return productFeedbackReaction - that was inserted to the database
     */
    @PostMapping(value = "/product/feedback/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductFeedbackReaction addProductFeedbackReaction(@RequestBody ProductFeedbackReaction productFeedbackReaction) {
        return this.productFeedbackReactionService.addProductFeedbackReaction(productFeedbackReaction);
    }

    @PostMapping(value = "/product/feedback/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfProductFeedbackReactionsByProductFeedbackAndReaction(
            @RequestBody ProductFeedback productFeedback,
            @PathVariable int reaction) {
        int nReactions = this.productFeedbackReactionService.countNumberOfProductFeedbackReactionsByProductFeedbackAndReaction(productFeedback, reaction);
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    @PostMapping(value = "/product/feedback/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductFeedbackReaction> getProductFeedbackReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.productFeedbackReactionService.getProductFeedbackReactionsByUserProfile(userProfile);
    }
}
