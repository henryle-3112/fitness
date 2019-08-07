package henry.greenwich.fitness.repository.payment;

import henry.greenwich.fitness.model.payment.ProductPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPaymentRepository extends JpaRepository<ProductPayment, Long> {
}
