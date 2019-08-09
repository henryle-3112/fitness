package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReplyOnProductFeedbackRepository extends JpaRepository<ReplyOnProductFeedback, Long> {
        String GET_REPLIES_ON_PRODUCT_FEEDBACK = "select * from " + Constants.REPLY_ON_PRODUCT_FEEDBACK_TABLE + ""
                        + " where (:productFeedbackId is null or " + Constants.REPLY_ON_PRODUCT_FEEDBACK_TABLE + "."
                        + Constants.REPLY_ON_PRODUCT_FEEDBACK_PRODUCT_FEEDBACK_ID + " = :productFeedbackId)"
                        + " and (:replyOnProductFeedbackStatus is null or " + Constants.REPLY_ON_PRODUCT_FEEDBACK_TABLE
                        + "." + Constants.REPLY_ON_PRODUCT_FEEDBACK_STATUS + " = :replyOnProductFeedbackStatus)";

        /**
         * @param productFeedbackId            - product's feedback's id that user want
         *                                     to get replies
         * @param replyOnProductFeedbackStatus - reply on product's feedback's status
         *                                     that user want to get replies
         * @return list of replies
         */
        @Query(nativeQuery = true, value = GET_REPLIES_ON_PRODUCT_FEEDBACK)
        List<Object> getRepliesOnProductFeedback(@RequestParam("productFeedbackId") Integer productFeedbackId,
                        @RequestParam("replyOnProductFeedbackStatus") Integer replyOnProductFeedbackStatus);

        /**
         * @param id - reply on product's feedback's id that user want to get selected
         *           reply on product's feedback
         * @return selected reply on product's feedback
         */
        ReplyOnProductFeedback findReplyOnProductFeedbackById(Long id);
}
