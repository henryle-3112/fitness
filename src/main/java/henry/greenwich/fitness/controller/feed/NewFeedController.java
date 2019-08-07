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
     * @param status - newfeeds' status that user want to filter newfeeds (this
     *               parameter could be optional)
     * @param search - newfeed's content's keywords that user want to get newfeeds
     *               (this parameter could be optional)
     * @param page   - start index to get newfeeds (for pagination) (this parameter
     *               could be optional)
     * @return list of newfeeds
     */
    @GetMapping(value = "/feeds", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<NewFeed> getNewFeeds(HttpServletResponse response, @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String search, @RequestParam(required = false) Integer page) {
        if (page != null) {
            int nNewFeeds = this.newFeedService.getNumberOfNewFeeds(status, search);
            response.addHeader("X-Total-Count", String.valueOf(nNewFeeds));
            response.addHeader("X-Total-Page", String.valueOf(nNewFeeds / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.newFeedService.getNewFeedsByPage(status, search, page);
        } else {
            return this.newFeedService.getNewFeeds(status, search);
        }
    }

    /**
     * @param newFeed - newFeed
     * @return inserted newFeed
     */
    @PostMapping(value = "/feeds", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public NewFeed addNewFeed(@RequestBody NewFeed newFeed) {
        return this.newFeedService.addNewFeed(newFeed);
    }

}
