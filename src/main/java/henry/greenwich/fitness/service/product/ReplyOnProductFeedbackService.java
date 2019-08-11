package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.repository.product.ReplyOnProductFeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnProductFeedbackService {
    /**
     * replyOnProductFeedbackRepository - interact with reply on product feedback data
     */
    private ReplyOnProductFeedbackRepository replyOnProductFeedbackRepository;

    /**
     * @param replyOnProductFeedbackRepository - inject replyOnProductFeedbackRepository
     */
    public ReplyOnProductFeedbackService(ReplyOnProductFeedbackRepository replyOnProductFeedbackRepository) {
        this.replyOnProductFeedbackRepository = replyOnProductFeedbackRepository;
    }

    /**
     * @param productFeedback - product's feedback's that user want to count number of reply
     */
    public int countNumberOfReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(ProductFeedback productFeedback, int status) {
        return this.replyOnProductFeedbackRepository.countReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(productFeedback, status);
    }

    /**
     * @param productFeedback - product's feedbacks that user want to view replies
     * @return list of replies
     */
    public List<ReplyOnProductFeedback> getReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(ProductFeedback productFeedback, int status) {
        return this.replyOnProductFeedbackRepository.findReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(productFeedback, status);
    }

    /**
     * @param replyOnProductFeedback - reply that user want to add to the database
     * @return reply that was inserted to the database
     */
    public ReplyOnProductFeedback addReplyOnProductFeedback(ReplyOnProductFeedback replyOnProductFeedback) {
        return this.replyOnProductFeedbackRepository.saveAndFlush(replyOnProductFeedback);
    }

}
