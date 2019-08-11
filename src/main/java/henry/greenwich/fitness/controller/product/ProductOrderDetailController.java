package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.product.ProductOrderDetail;
import henry.greenwich.fitness.service.product.ProductOrderDetailService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductOrderDetailController {
    /**
     * productOrderDetailService - interact with product's order's detail's data
     */
    private ProductOrderDetailService productOrderDetailService;

    /**
     * @param productOrderDetailService - inject productOrderDetailService
     */
    public ProductOrderDetailController(ProductOrderDetailService productOrderDetailService) {
        this.productOrderDetailService = productOrderDetailService;
    }

    /**
     * @return list of product's order's detail
     */
    @GetMapping(value = "/product/order/details", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductOrderDetail> getProductOrderDetails() {
        return this.productOrderDetailService.getProductOrderDetails();
    }

    /**
     * @param id - product's order's detail's id that user want to get
     * @return selected product's order's detail
     */
    @GetMapping(value = "/product/order/details/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductOrderDetail getProductOrderDetail(@PathVariable Long id) {
        return this.productOrderDetailService.getProductOrderDetail(id);
    }

    /**
     * @param productOrderDetails - list of product order details that user want to add to the database
     * @return list of product order details
     */
    @PostMapping(value = "/product/order/details/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductOrderDetail> addProductOrderDetail(@RequestBody ArrayList<ProductOrderDetail> productOrderDetails) {
        return this.productOrderDetailService.addProductOrderDetail(productOrderDetails);
    }

    /**
     * @param productOrderDetail - that user want to update to the database
     * @return productOrderDetail - that was updated to the database
     */
    @PostMapping(value = "/product/order/details/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductOrderDetail updateProductOrderDetail(@RequestBody ProductOrderDetail productOrderDetail) {
        return this.productOrderDetailService.updateProductOrderDetail(productOrderDetail);
    }

    /**
     * @param id - product's order's detail's id that user want to delete
     */
    @PostMapping(value = "/product/order/details/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteProductOrderDetail(@PathVariable Long id) {
        this.productOrderDetailService.deleteProductOrderDetail(id);
    }

    /**
     * @param productOrder - product's order
     * @return list of product's order's detail
     */
    @PostMapping(value = "/product/order/details", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductOrderDetail> findProductOrderDetailsByProductOrder(@RequestBody ProductOrder productOrder) {
        return this.productOrderDetailService.findProductOrderDetailsByProductOrder(productOrder);
    }
}
