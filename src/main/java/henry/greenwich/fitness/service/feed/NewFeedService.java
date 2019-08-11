package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.repository.feed.NewFeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFeedService {
    /**
     * newFeedRepository - interact with new's feed's data
     */
    private NewFeedRepository newFeedRepository;

    /**
     * @param newFeedRepository - inject newFeedRepository
     */
    public NewFeedService(NewFeedRepository newFeedRepository) {
        this.newFeedRepository = newFeedRepository;
    }

    /**
     * @param status     - status
     * @param keyword    - keyword
     * @param startIndex - start's index
     * @return list of new's feeds
     */
    public List<Object> findNewFeedsByStatusAndUserProfileAndPage(int status, String keyword, int startIndex) {
        return this.newFeedRepository.findNewFeedsByStatusAndUserProfileAndPage(status, keyword, startIndex);
    }

    /**
     * @param status  - status
     * @param keyword - keyword
     * @return number of new's feeds
     */
    public List<Object> countNewFeedsByStatusAndUserProfileAndPage(int status, String keyword) {
        return this.newFeedRepository.countNewFeedsByStatusAndUserProfileAndPage(status, keyword);
    }

    /**
     *
     * @param newFeed - new's feed
     * @return inserted newFeed
     */
    public NewFeed addNewFeed(NewFeed newFeed) {
        return this.newFeedRepository.saveAndFlush(newFeed);
    }
}
