package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("feed-management")
public class ReplyOnNewFeedCommentController {
    private ReplyOnNewFeedCommentService replyOnNewFeedCommentService;

    /**
     * @param replyOnNewFeedCommentService - inject replyOnNewFeedCommentService
     */
    public ReplyOnNewFeedCommentController(ReplyOnNewFeedCommentService replyOnNewFeedCommentService) {
        this.replyOnNewFeedCommentService = replyOnNewFeedCommentService;
    }

    /**
     * @param newFeedCommentId - newfeed's comment's id that user want to get
     *                         replies
     * @param status           - status of reply on newfeed's comment that user want
     *                         to filter replies (this parameter could be optional)
     * @return list of replies
     */
    @GetMapping(value = "/comments/{newFeedCommentId}/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnNewFeedComment> getRepliesOnNewFeedComment(@PathVariable Integer newFeedCommentId,
                                                                  @RequestParam(required = false) Integer status) {
        return this.replyOnNewFeedCommentService.getRepliesOnNewFeedComment(newFeedCommentId, status);
    }

    /**
     * @param replyOnNewFeedComment - reply on newfeed's comment that user want to
     *                              add to the database
     * @return inserted reply on newfeed's comment
     */
    @PostMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnNewFeedComment addReplyOnNewFeedComment(@RequestBody ReplyOnNewFeedComment replyOnNewFeedComment) {
        return this.replyOnNewFeedCommentService.addReplyOnNewFeedComment(replyOnNewFeedComment);
    }

    /**
     * @param replyOnNewFeedComment - reply on newfeed's comment that user want to update
     * @return updated reply on newfeed's comment
     */
    @PutMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnNewFeedComment updateReplyOnNewFeedComment(@RequestBody ReplyOnNewFeedComment replyOnNewFeedComment) {
        return this.replyOnNewFeedCommentService.updateReplyOnNewFeedComment(replyOnNewFeedComment);
    }

}
