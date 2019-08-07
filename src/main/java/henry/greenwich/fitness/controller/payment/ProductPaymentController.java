package henry.greenwich.fitness.controller.payment;

import henry.greenwich.fitness.model.payment.ProductPayment;
import henry.greenwich.fitness.service.payment.ProductPaymentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("payment-management")
public class ProductPaymentController {
    private ProductPaymentService productPaymentService;

    /**
     * @param productPaymentService - inject productPaymentService
     */
    public ProductPaymentController(ProductPaymentService productPaymentService) {
        this.productPaymentService = productPaymentService;
    }

    /**
     * @param productPayment - product payment
     * @return inserted product payment
     */
    @PostMapping(value = "/products-payment", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ProductPayment addProductPayment(@RequestBody ProductPayment productPayment) {
        return this.productPaymentService.addProductPayment(productPayment);
    }
}
