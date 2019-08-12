package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.service.feed.NewFeedCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("feed-management")
public class NewFeedCommentController {
    private NewFeedCommentService newFeedCommentService;

    /**
     * @param newFeedCommentService - inject newFeedCommentService
     */
    public NewFeedCommentController(NewFeedCommentService newFeedCommentService) {
        this.newFeedCommentService = newFeedCommentService;
    }

    /**
     * @param newFeedId - newfeed's id that user want to get newfeed's comments
     * @param status    - newfeed's comment's status that user want to filter
     *                  newfeed's comments (this parameter could be optional)
     * @return list of newfeed's comments
     */
    @GetMapping(value = "/feeds/{newFeedId}/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeedComment> getNewFeedComments(@PathVariable Integer newFeedId,
                                                   @RequestParam(required = false) Integer status) {
        return this.newFeedCommentService.getNewFeedComments(newFeedId, status);
    }

    /**
     * @param newFeedComment - new's feed's comment that user want to add to the
     *                       database
     * @return inserted new's feed's comment
     */
    @PostMapping(value = "/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeedComment addNewFeedComment(@RequestBody NewFeedComment newFeedComment) {
        return this.newFeedCommentService.addNewFeedComment(newFeedComment);
    }

    /**
     *
     * @param newFeedComment - newfeed's comment that user want to update
     * @return updated newfeed's comment
     */
    @PutMapping(value = "/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeedComment updateNewFeedComment(@RequestBody NewFeedComment newFeedComment) {
        return this.newFeedCommentService.updateNewFeedComment(newFeedComment);
    }
}
