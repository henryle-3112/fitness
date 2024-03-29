package henry.greenwich.fitness.config;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import henry.greenwich.fitness.constants.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaypalClient {

    public PaypalClient() {

    }

    /**
     * @param sum - total payment
     * @return status and redirect url
     */
    public Map<String, Object> createPayment(String sum) {
        Map<String, Object> response = new HashMap<>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/client/payment");
        redirectUrls.setReturnUrl("http://localhost:4200/client/payment");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(Constants.PAYPAL_CLIENT_ID, Constants.PAYPAL_SECRET, "sandbox");
            createdPayment = payment.create(context);
            if (createdPayment != null) {
                List<Links> links = createdPayment.getLinks();
                for (Links link : links) {
                    if (link.getRel().equals("approval_url")) {
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }

    /**
     * @return status of payment
     */
    public Map<String, Object> completePayment(String paymentId, String payerId) {
        Map<String, Object> response = new HashMap<>();
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        try {
            APIContext context = new APIContext(Constants.PAYPAL_CLIENT_ID, Constants.PAYPAL_SECRET, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if (createdPayment != null) {
                response.put("status", "success");
                // response.put("user-payment", createdPayment);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }

}
