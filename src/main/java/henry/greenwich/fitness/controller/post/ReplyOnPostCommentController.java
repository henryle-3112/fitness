package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.post.ReplyOnPostCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnPostCommentController {
    /**
     * replyOnPostCommentService - interact with reply on post comment data
     */
    private ReplyOnPostCommentService replyOnPostCommentService;

    /**
     * @param replyOnPostCommentService - inject replyOnPostCommentService
     */
    public ReplyOnPostCommentController(ReplyOnPostCommentService replyOnPostCommentService) {
        this.replyOnPostCommentService = replyOnPostCommentService;
    }

    /**
     * @param postComment - post's comment that user want to get replies
     * @return list of replies
     */
    @PostMapping(value = "/post/comment/replies/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnPostComment> getReplyOnPostComments(@RequestBody PostComment postComment, @PathVariable int status) {
        return this.replyOnPostCommentService.getReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(postComment, status);
    }

    /**
     * @param replyOnPostComment - reply on post's comment
     * @return inserted replyOnPostComment
     */
    @PostMapping(value = "/post/comment/replies/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnPostComment addReplyOnPostComment(@RequestBody ReplyOnPostComment replyOnPostComment) {
        return this.replyOnPostCommentService.addReplyOnPostComment(replyOnPostComment);
    }

    /**
     * @param postComment - post's comment
     * @param status      - status
     * @return numeber of reply on post comments
     */
    @PostMapping(value = "/post/comment/replies/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(@RequestBody PostComment postComment, @PathVariable int status) {
        int nReplies = this.replyOnPostCommentService.countNumberOfReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(postComment, status);
        return new ResponseMessage(String.valueOf(nReplies));
    }

}
