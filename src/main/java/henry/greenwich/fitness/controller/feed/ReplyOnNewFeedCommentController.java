package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyOnNewFeedCommentController {
    /**
     * replyOnNewFeedCommentService - interact with reply new feed comment data
     */
    private ReplyOnNewFeedCommentService replyOnNewFeedCommentService;

    /**
     * @param replyOnNewFeedCommentService - inject replyOnNewFeedCommentService
     */
    public ReplyOnNewFeedCommentController(ReplyOnNewFeedCommentService replyOnNewFeedCommentService) {
        this.replyOnNewFeedCommentService = replyOnNewFeedCommentService;
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @param status         - status
     * @return list of replies
     */
    @PostMapping(value = "/feed/comment/replies/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ReplyOnNewFeedComment> findReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
            @RequestBody NewFeedComment newFeedComment, @PathVariable int status) {
        return this.replyOnNewFeedCommentService.findReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
                newFeedComment, status);
    }

    /**
     * @param replyOnNewFeedComment - replyOnNewFeedComment
     * @return inserted replyOnNewFeedComment
     */
    @PostMapping(value = "/feed/comment/replies/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ReplyOnNewFeedComment addReplyOnNewFeedComment(@RequestBody ReplyOnNewFeedComment replyOnNewFeedComment) {
        return this.replyOnNewFeedCommentService.addReplyOnNewFeedComment(replyOnNewFeedComment);
    }

    /**
     * @param newFeedComment - new's feed's commet
     * @param status         - status
     * @return number of replies
     */
    @PostMapping(value = "/feed/comment/replies/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
            @RequestBody NewFeedComment newFeedComment, @PathVariable int status) {
        int nReplies = this.replyOnNewFeedCommentService
                .countReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(newFeedComment, status);
        return new ResponseMessage(String.valueOf(nReplies));
    }
}
