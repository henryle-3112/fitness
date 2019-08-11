package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.feed.NewFeedCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewFeedCommentController {
    /**
     * newFeedCommentService - interact with new's feed's comment's data
     */
    private NewFeedCommentService newFeedCommentService;

    /**
     * @param newFeedCommentService - inject newFeedCommentService
     */
    public NewFeedCommentController(NewFeedCommentService newFeedCommentService) {
        this.newFeedCommentService = newFeedCommentService;
    }

    /**
     * @param newFeed - new's feed
     * @param status  - status
     * @return list of new's feed's comment
     */
    @PostMapping(value = "/feed/comments/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeedComment> findNewFeedCommentsByNewFeedAndNewFeedCommentStatus(@RequestBody NewFeed newFeed, @PathVariable int status) {
        return this.newFeedCommentService.findNewFeedCommentsByNewFeedAndNewFeedCommentStatus(newFeed, status);
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @return inserted new's feed's comment
     */
    @PostMapping(value = "/feed/comments/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeedComment addNewFeedComment(@RequestBody NewFeedComment newFeedComment) {
        return this.newFeedCommentService.addNewFeedComment(newFeedComment);
    }

    /**
     * @param newFeed - new's feed
     * @param status  - status
     * @return number of new feed comments
     */
    @PostMapping(value = "/feed/comments/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNewFeedCommentsByNewFeedAndNewFeedCommentStatus(
            @RequestBody NewFeed newFeed, @PathVariable int status) {
        int nNewFeedComments = this.newFeedCommentService
                .countNewFeedCommentsByNewFeedAndNewFeedCommentStatus(newFeed, status);
        return new ResponseMessage(String.valueOf(nNewFeedComments));
    }
}
