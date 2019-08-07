package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, Long> {

    String GET_PRODUCT_FEEDBACKS = "select * from " + Constants.PRODUCT_FEEDBACK_TABLE + ""
            + " where (:productId is null and " + Constants.PRODUCT_FEEDBACK_TABLE + "."
            + Constants.PRODUCT_FEEDBACK_PRODUCT_ID + " = :productId)" + " and (:productFeedbackStatus is null or "
            + Constants.PRODUCT_FEEDBACK_TABLE + "." + Constants.PRODUCT_FEEDBACK_STATUS + " = :productFeedbackStatus)";

    /**
     * @param productId             - product's id that user want to get list of
     *                              product's feedbacks
     * @param productFeedbackStatus - product's feedback's status that user want to
     *                              get list of product's feedbacks
     * @return list of product's feedbacks
     */
    @Query(nativeQuery = true, value = GET_PRODUCT_FEEDBACKS)
    List<Object> getProductFeedbacks(Integer productId, Integer productFeedbackStatus);

    /**
     * @param id - product's feedback's id that user want to get selected product's
     *           feedback
     * @return selected products' feedback
     */
    ProductFeedback findProductFeedbackById(Long id);
}
