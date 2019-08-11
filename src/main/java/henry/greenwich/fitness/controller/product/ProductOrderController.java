package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.product.ProductOrderService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ProductOrderController {
    /**
     * productOrderService - interact with product's order's data
     * userProfileService - interact with user's profile's data
     */
    private ProductOrderService productOrderService;
    private UserProfileService userProfileService;

    /**
     * @param productOrderService - inject productOrderService
     * @param userProfileService  - inject userProfileService
     */
    public ProductOrderController(ProductOrderService productOrderService, UserProfileService userProfileService) {
        this.productOrderService = productOrderService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of product's order
     */
    @GetMapping(value = "/product/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductOrder> getProductOrders() {
        return this.productOrderService.getProductOrders();
    }

    /**
     * @param id - product's order's id that user want to get
     * @return selected product's order
     */
    @GetMapping(value = "/product/orders/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductOrder getProductOrder(@PathVariable Long id) {
        return this.productOrderService.getProductOrder(id);
    }

    /**
     * @param productOrder - that user want to add to the database
     * @return productOrder - that was inserted to the database
     */
    @PostMapping(value = "/product/orders/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductOrder addProductOrder(@RequestBody ProductOrder productOrder) {
        return this.productOrderService.addProductOrder(productOrder);
    }

    /**
     * @param productOrder - that user want to update to the database
     * @return productOrder - that was updated to the database
     */
    @PostMapping(value = "/product/orders/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductOrder updateProductOrder(@RequestBody ProductOrder productOrder) {
        return this.productOrderService.updateProductOrder(productOrder);
    }

    /**
     * @param id - product's order's id that user want to delete
     */
    @PostMapping(value = "/product/orders/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteProductOrder(@PathVariable Long id) {
        this.productOrderService.deleteProductOrder(id);
    }

    /**
     * @param userProfileId      - user's profile's id
     * @param productOrderStatus - product's order's status
     * @param page               - current page
     * @return list of product's orders
     */
    @GetMapping(value = "/product/orders/paging/{userProfileId}/{productOrderStatus}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductOrder> findProductOrdersByUserProfileIdAndProductOrderStatus(
            @PathVariable int userProfileId,
            @PathVariable int productOrderStatus,
            @PathVariable int page
    ) {
        int startIndex = ((page - 1) * 8) + 1;
        List<ProductOrder> productOrders = new ArrayList<>();
        List<Object> productOrdersObject = this.productOrderService
                .findProductOrdersByUserProfileIdAndProductOrderStatus(userProfileId, productOrderStatus, startIndex - 1);
        for (Object o : productOrdersObject) {
            Object[] eachProductOrderObject = (Object[]) o;
            int productOrderId = (int) eachProductOrderObject[0];
            Date productOrderDate = (Date) eachProductOrderObject[2];
            // get user profile
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            ProductOrder productOrder = new ProductOrder(
                    (long) productOrderId,
                    productOrderStatus,
                    productOrderDate,
                    userProfile
            );
            productOrders.add(productOrder);
        }
        return productOrders;
    }

    /**
     * @param userProfileId      - user's profile's id
     * @param productOrderStatus - product's order's status
     * @return number of product's orders
     */
    @GetMapping(value = "/product/orders/count/{userProfileId}/{productOrderStatus}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfProductOrdersByUserProfileIdAndProductOrderStatus(
            @PathVariable int userProfileId,
            @PathVariable int productOrderStatus
    ) {
        List<Object> countProductOrdersObject = this.productOrderService
                .countNumberOfProductOrdersByUserProfileIdAndProductOrderStatus(userProfileId, productOrderStatus);
        Object eachCountProductOrder = countProductOrdersObject.get(0);
        return new ResponseMessage(eachCountProductOrder.toString());
    }

}
