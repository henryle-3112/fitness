package henry.greenwich.fitness.controller.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.feed.NewFeedService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class NewFeedController {
    /**
     * newFeedService - interact new's feed's data
     * userProfileService - interact user's profile's data
     */
    private NewFeedService newFeedService;
    private UserProfileService userProfileService;

    /**
     * @param newFeedService - inject newFeedService
     * @param userProfileService - inject userProfileService
     */
    public NewFeedController(NewFeedService newFeedService, UserProfileService userProfileService) {
        this.newFeedService = newFeedService;
        this.userProfileService = userProfileService;
    }

    /**
     *
     * @param status - status
     * @param page - page
     * @param keyword - keyword
     * @return list of new feeds
     */
    @GetMapping(value = "/feeds/paging/{status}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<NewFeed> findNewFeedsByStatusAndUserProfileAndPage(
            @PathVariable int status, @PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<NewFeed> newFeeds = new ArrayList<>();
        List<Object> newFeedsObject = this.newFeedService.findNewFeedsByStatusAndUserProfileAndPage(
                status, selectedKeyWord, startIndex - 1);
        for (int i = 0; i < newFeedsObject.size(); i++) {
            Object[] eachNewFeedObject = (Object[]) newFeedsObject.get(i);
            int id = (int) eachNewFeedObject[0];
            String image = (String) eachNewFeedObject[1];
            String achievement = (String) eachNewFeedObject[2];
            String achievementTime = (String) eachNewFeedObject[3];
            String content = (String) eachNewFeedObject[4];
            int userProfileId = (int) eachNewFeedObject[5];
            // get selected user's profile
            UserProfile selectedUserProfile = this.userProfileService.getUserProfile((long) userProfileId);
            Date createdDate = (Date) eachNewFeedObject[6];
            // create new's feed object
            NewFeed newFeed = new NewFeed(
                    (long) id,
                    image,
                    achievement,
                    achievementTime,
                    content,
                    status,
                    createdDate,
                    selectedUserProfile
            );
            newFeeds.add(newFeed);
        }
        return newFeeds;
    }

    /**
     *
     * @param status - status
     * @param keyword - keyword
     * @return number of new feeds
     */
    @GetMapping(value = "/feeds/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNewFeedsByStatusAndUserProfileAndPage(
            @PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countNewFeedsObject = this.newFeedService.countNewFeedsByStatusAndUserProfileAndPage(
                status, selectedKeyWord);
        Object eachCountNewFeed = countNewFeedsObject.get(0);
        return new ResponseMessage(eachCountNewFeed.toString());
    }

    /**
     *
     * @param newFeed - newFeed
     * @return inserted newFeed
     */
    @PostMapping(value = "/feeds/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public NewFeed addNewFeed(@RequestBody NewFeed newFeed) {
        return this.newFeedService.addNewFeed(newFeed);
    }

}
