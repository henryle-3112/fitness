package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ReplyOnProductFeedback;
import henry.greenwich.fitness.service.product.ReplyOnProductFeedbackService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product-management")
public class ReplyOnProductFeedbackController {
    private ReplyOnProductFeedbackService replyOnProductFeedbackService;

    /**
     * @param replyOnProductFeedbackService - inject replyOnProductFeedbackService
     */
    public ReplyOnProductFeedbackController(ReplyOnProductFeedbackService replyOnProductFeedbackService) {
        this.replyOnProductFeedbackService = replyOnProductFeedbackService;
    }

    /**
     * @param productFeedbackId - product's feedback's id that user want to get
     *                          replies
     * @param status            - status of replies on product's feedback's status
     *                          that user want to get replies
     * @return list of replies
     */
    @GetMapping(value = "/feedbacks/{productFeedbackId}/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnProductFeedback> getReplyOnProductFeedbacks(@PathVariable Integer productFeedbackId,
                                                                   @RequestParam(required = false) Integer status) {
        return this.replyOnProductFeedbackService.getRepliesOnProductFeedback(productFeedbackId, status);
    }

    /**
     * @param replyOnProductFeedback - reply on product's feedback that user want to
     *                               add to the database
     * @return inserted reply on product's feedback
     */
    @PostMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnProductFeedback addReplyOnProductFeedback(
            @RequestBody ReplyOnProductFeedback replyOnProductFeedback) {
        return this.replyOnProductFeedbackService.addReplyOnProductFeedback(replyOnProductFeedback);
    }

    /**
     * @param replyOnProductFeedback - reply on product's feedback that user want to update
     * @return updated reply on product's feedback
     */
    @PutMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnProductFeedback updateReplyOnProductFeedback(
            @RequestBody ReplyOnProductFeedback replyOnProductFeedback) {
        return this.replyOnProductFeedbackService.updateReplyOnProductFeedback(replyOnProductFeedback);
    }

}
