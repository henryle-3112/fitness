package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.service.post.ReplyOnPostCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("post-management")
public class ReplyOnPostCommentController {
    private ReplyOnPostCommentService replyOnPostCommentService;

    /**
     * @param replyOnPostCommentService - inject replyOnPostCommentService
     */
    public ReplyOnPostCommentController(ReplyOnPostCommentService replyOnPostCommentService) {
        this.replyOnPostCommentService = replyOnPostCommentService;
    }

    /**
     * @param postCommentId - post's comment's id that user want to get replies
     * @param status        - status of replies on post's comment's status that user
     *                      want to get replies
     * @return list of replies
     */
    @GetMapping(value = "/comments/{postCommentId}/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnPostComment> getReplyOnPostComments(@PathVariable Integer postCommentId,
                                                           @RequestParam(required = false) Integer status) {
        return this.replyOnPostCommentService.getRepliesOnPostComment(postCommentId, status);
    }

    /**
     * @param replyOnPostComment - reply on post's comment that user want to add to
     *                           the database
     * @return inserted reply on post's comment
     */
    @PostMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnPostComment addReplyOnPostComment(@RequestBody ReplyOnPostComment replyOnPostComment) {
        return this.replyOnPostCommentService.addReplyOnPostComment(replyOnPostComment);
    }

    /**
     * @param replyOnPostComment - reply on post's comment that user want to update
     * @return updated reply on post's comment
     */
    @PutMapping(value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnPostComment updateReplyOnPostComment(@RequestBody ReplyOnPostComment replyOnPostComment) {
        return this.replyOnPostCommentService.updateReplyOnPostComment(replyOnPostComment);
    }

}
