package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, Long> {

    /**
     * @param product - that user want to get feedback
     * @return list of feedbacks
     */
    List<ProductFeedback> findProductFeedbacksByProductAndFeedbackStatus(Product product, int status);
}
