package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnProductFeedbackRepository extends JpaRepository<ReplyOnProductFeedback, Long> {

    /**
     *
     * @param productFeedback - product's feedback
     * @param status - reply on product's feedback' status
     * @return number of reply on product's feedback's status
     */
    int countReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(ProductFeedback productFeedback, int status);

    /**
     * @param productFeedback - product's feedbacks that user want to view replies
     * @return list of replies
     */
    List<ReplyOnProductFeedback> findReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(
            ProductFeedback productFeedback,
            int status);
}
