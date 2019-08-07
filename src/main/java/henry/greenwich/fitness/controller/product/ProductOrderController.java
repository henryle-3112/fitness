package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.service.product.ProductOrderService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductOrderController {
    private ProductOrderService productOrderService;

    /**
     * @param productOrderService - inject productOrderService
     */
    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    /**
     * @param productOrder - that user want to add to the database
     * @return inserted product's order
     */
    @PostMapping(value = "/orders", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ProductOrder addProductOrder(@RequestBody ProductOrder productOrder) {
        return this.productOrderService.addProductOrder(productOrder);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      product's orders (this parameter could be optional)
     * @param status        - product's order's status that user want to get list of
     *                      product's orders (this parameter could be optional)
     * @param page          - current page (for pagination) (this parameter could be
     *                      optional)
     * @return list of product's orders
     */
    @GetMapping(value = "/orders", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ProductOrder> findProductOrdersByUserProfileIdAndProductOrderStatus(HttpServletResponse response,
            @RequestParam(required = false) Integer userProfileId, @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer page) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nProductOrders = this.productOrderService.getNumberOfProductOrders(userProfileId, status);
            response.addHeader("X-Total-Count", String.valueOf(nProductOrders));
            response.addHeader("X-Total-Page", String.valueOf(nProductOrders / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.productOrderService.getProductOrdersPaging(userProfileId, status, startIndex);
        }
        return this.productOrderService.getProductOrders(userProfileId, status);
    }

}
