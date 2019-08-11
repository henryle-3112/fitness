package henry.greenwich.fitness.service.payment;

import henry.greenwich.fitness.model.payment.ProductPayment;
import henry.greenwich.fitness.repository.payment.ProductPaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductPaymentService {
    /**
     * productPaymentRepository - interact with product payment repository
     */
    private ProductPaymentRepository productPaymentRepository;

    /**
     * @param productPaymentRepository - inject productPaymentRepository
     */
    public ProductPaymentService(ProductPaymentRepository productPaymentRepository) {
        this.productPaymentRepository = productPaymentRepository;
    }

    /**
     *
     * @param productPayment - product's payment
     * @return inserted product payment
     */
    public ProductPayment addProductPayment(ProductPayment productPayment) {
        return this.productPaymentRepository.saveAndFlush(productPayment);
    }
}
