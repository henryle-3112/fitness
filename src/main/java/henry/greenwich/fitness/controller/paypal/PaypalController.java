package henry.greenwich.fitness.controller.paypal;

import henry.greenwich.fitness.config.PaypalClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class PaypalController {
    /**
     * payPlalClient - payPalClient
     */
    private PaypalClient payPalClient;

    /**
     *
     * @param payPalClient - inject payPalClient
     */
    public PaypalController(PaypalClient payPalClient) {
        this.payPalClient = payPalClient;
    }

    /**
     *
     * @param sum - sum
     * @return created payment
     */
    @PostMapping(value = "/paypal/make/payment", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> makePayment(@RequestParam("sum") String sum) {
        return payPalClient.createPayment(sum);
    }

    /**
     *
     * @param paymentId - paymentId
     * @param payerId - payerId
     * @return completed payment
     */
    @PostMapping(value = "/paypal/complete/payment", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> completePayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerId") String payerId) {
        return payPalClient.completePayment(paymentId, payerId);
    }
}
