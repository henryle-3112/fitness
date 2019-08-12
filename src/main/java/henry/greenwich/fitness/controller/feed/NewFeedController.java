package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.service.feed.NewFeedService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/feed-management")
public class NewFeedController {
    private NewFeedService newFeedService;

    /**
     * @param newFeedService - inject newFeedService
     */
    public NewFeedController(NewFeedService newFeedService) {
        this.newFeedService = newFeedService;
    }

    /**
     * @param response - response to add number of pages and number of newfeeds to
     *                 header
     * @param status   - newfeeds' status that user want to filter newfeeds (this
     *                 parameter could be optional)
     * @param search   - newfeed's content's keywords that user want to get newfeeds
     *                 (this parameter could be optional)
     * @param page     - start index to get newfeeds (for pagination) (this
     *                 parameter could be optional)
     * @return list of newfeeds
     */
    @GetMapping(value = "/feeds", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeed> getNewFeeds(HttpServletResponse response,
                                     @RequestParam(required = false) Integer status,
                                     @RequestParam(required = false) String search,
                                     @RequestParam(required = false) Integer page) {
        if (page != null) {
            return this.getNewFeedsPaging(response, status, search, page);
        }
        return this.newFeedService.getNewFeeds(status, search);
    }

    /**
     * @param response - response to add number of pages and number of newfeeds to
     *                 header
     * @param status   - newfeeds' status that user want to filter newfeeds (this
     *                 parameter could be optional)
     * @param search   - newfeed's content's keywords that user want to get newfeeds
     *                 (this parameter could be optional)
     * @param page     - start index to get newfeeds (for pagination) (this
     *                 parameter could be optional)
     * @return list of newfeeds
     */
    private List<NewFeed> getNewFeedsPaging(HttpServletResponse response, Integer status, String search, Integer page) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nNewFeeds = this.newFeedService.getNumberOfNewFeeds(status, search);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nNewFeeds));
        int nPages = nNewFeeds > 0 ? (nNewFeeds >= Constants.NUMBER_ITEMS_PER_PAGE ? nNewFeeds / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.newFeedService.getNewFeedsByPage(status, search, startIndex - 1);
    }

    /**
     * @param newFeed - newFeed
     * @return inserted newFeed
     */
    @PostMapping(value = "/feeds", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeed addNewFeed(@RequestBody NewFeed newFeed) {
        return this.newFeedService.addNewFeed(newFeed);
    }

    /**
     * @param newFeed - newfeed that user want to update
     * @return updated newfeed
     */
    @PutMapping(value = "/feeds", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeed updateNewFeed(@RequestBody NewFeed newFeed) {
        return this.newFeedService.updateNewFeed(newFeed);
    }

}
