package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductRate;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.product.ProductRateService;
import henry.greenwich.fitness.service.product.ProductService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductRateController {
    /**
     * productRateService - interact with product's rate's data
     * productService - interact with product's data
     * userProfileService - interact with user's profile's data
     */
    private ProductRateService productRateService;
    private ProductService productService;
    private UserProfileService userProfileService;

    /**
     * @param productRateService - inject product's rate service
     */
    public ProductRateController(ProductRateService productRateService,
                                 ProductService productService,
                                 UserProfileService userProfileService) {
        this.productRateService = productRateService;
        this.productService = productService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of product's rate
     */
    @GetMapping(value = "/product/rates", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductRate> getUserProfiles() {
        return this.productRateService.getProductRates();
    }

    /**
     * @param id - product's rate's id that user want to get
     * @return selected product's rate
     */
    @GetMapping(value = "/product/rates/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductRate getProductRate(@PathVariable Long id) {
        return this.productRateService.getProductRate(id);
    }

    /**
     * @param productRate - that user want to add to the database
     * @return productRate - that was inserted to the database
     */
    @PostMapping(value = "/product/rates/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductRate addProductRate(@RequestBody ProductRate productRate) {
        return this.productRateService.addProductRate(productRate);
    }

    /**
     * @param productRate - that user want to update to the database
     * @return productRate - that was updated to the database
     */
    @PostMapping(value = "/product/rates/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductRate updateProductRate(@RequestBody ProductRate productRate) {
        return this.productRateService.updateProductRate(productRate);
    }

    /**
     * @param id - product's rate's id that user want to delete
     */
    @PostMapping(value = "/product/rates/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteProductRate(@PathVariable Long id) {
        this.productRateService.deleteProductRate(id);
    }


    @GetMapping(value = "/product/rates/{userId}/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductRate getProductRate(@PathVariable Long userId, @PathVariable Long productId) {
        ProductRate productRate = new ProductRate();
        List<Object> productRateObjectList = this.productRateService.getProductRateByUserIdAndProductId(userId, productId);
        if (!productRateObjectList.isEmpty()) {
            Object[] productRateObjectArray = (Object[]) productRateObjectList.get(0);
            int productRateId = (int) productRateObjectArray[0];
            int productRateValue = (int) productRateObjectArray[1];
            Product product = this.productService.getProduct(productId);
            UserProfile userProfile = this.userProfileService.getUserProfile(userId);
            productRate = new ProductRate(
                    (long) productRateId,
                    productRateValue,
                    product,
                    userProfile
            );
            return productRate;
        }
        return productRate;
    }

}
