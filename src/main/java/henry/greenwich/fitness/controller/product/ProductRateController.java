package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductRate;
import henry.greenwich.fitness.service.product.ProductRateService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product-management")
public class ProductRateController {
    private ProductRateService productRateService;

    /**
     * @param productRateService - inject product's rate service
     */
    public ProductRateController(ProductRateService productRateService) {
        this.productRateService = productRateService;
    }

    /**
     * @param productRate - that user want to add to the database
     * @return inserted product's rate
     */
    @PostMapping(value = "/rates", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ProductRate addProductRate(@RequestBody ProductRate productRate) {
        return this.productRateService.addProductRate(productRate);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get product's
     *                      rate
     * @param productId     - product's id that user want to get product's rate
     * @return selected product's rate
     */
    @GetMapping(value = "/users/{userProfileId}/products/{productId}/rates", produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ProductRate getProductRate(@PathVariable Long userProfileId, @PathVariable Long productId) {
        return this.productRateService.getProductRateByUserProfileIdAndProductId(userProfileId, productId);
    }

}
