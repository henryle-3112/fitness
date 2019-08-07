package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductOrderDetail;
import henry.greenwich.fitness.service.product.ProductOrderDetailService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductOrderDetailController {
    private ProductOrderDetailService productOrderDetailService;

    /**
     * @param productOrderDetailService - inject productOrderDetailService
     */
    public ProductOrderDetailController(ProductOrderDetailService productOrderDetailService) {
        this.productOrderDetailService = productOrderDetailService;
    }

    /**
     * @param productOrderDetails - list of product order details that user want to
     *                            add to the database
     * @return list of product order details
     */
    @PostMapping(value = "/order-details", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ProductOrderDetail> addProductOrderDetail(
            @RequestBody ArrayList<ProductOrderDetail> productOrderDetails) {
        return this.productOrderDetailService.addProductOrderDetail(productOrderDetails);
    }

    /**
     * @param productOrderId - product's order's id that user want to get list of
     *                       product's order's details
     * @return list of product's order's details
     */
    @GetMapping(value = "/orders/{productOrderId}/order-details", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ProductOrderDetail> getProductOrderDetailsByProductOrderId(@PathVariable Integer productOrderId) {
        return this.productOrderDetailService.getProductOrderDetailsByProductOrder(productOrderId);
    }
}
