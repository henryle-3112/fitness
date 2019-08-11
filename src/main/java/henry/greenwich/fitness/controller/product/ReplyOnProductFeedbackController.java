package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.product.ReplyOnProductFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReplyOnProductFeedbackController {
    /**
     * replyOnProductFeedbackService - interact with reply on product's feedback data
     */
    private ReplyOnProductFeedbackService replyOnProductFeedbackService;

    /**
     * @param replyOnProductFeedbackService - inject replyOnProductFeedbackService
     */
    public ReplyOnProductFeedbackController(ReplyOnProductFeedbackService replyOnProductFeedbackService) {
        this.replyOnProductFeedbackService = replyOnProductFeedbackService;
    }

    /**
     * @param productFeedback - product's feedbacks that user want to get replies
     * @return list of replies
     */
    @PostMapping(value = "/product/feedback/replies/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnProductFeedback> getReplyOnProductFeedbacks(@RequestBody ProductFeedback productFeedback, @PathVariable int status) {
        return this.replyOnProductFeedbackService.getReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(productFeedback, status);
    }

    /**
     * @param replyOnProductFeedback - that user want to add to the database
     * @return replyOnProductFeedback - that was inserted to the database
     */
    @PostMapping(value = "/product/feedback/replies/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnProductFeedback addReplyOnProductFeedback(@RequestBody ReplyOnProductFeedback replyOnProductFeedback) {
        return this.replyOnProductFeedbackService.addReplyOnProductFeedback(replyOnProductFeedback);
    }

    @PostMapping(value = "/product/feedback/replies/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfReplyOnProductFeedbacksByProductFeedback(@RequestBody ProductFeedback productFeedback, @PathVariable int status) {
        int nReplies = this.replyOnProductFeedbackService.countNumberOfReplyOnProductFeedbacksByProductFeedbackAndReplyOnProductFeedbackStatus(productFeedback, status);
        return new ResponseMessage(String.valueOf(nReplies));
    }


}
