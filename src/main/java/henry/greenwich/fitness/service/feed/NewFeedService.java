package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewFeedService {
    private NewFeedRepository newFeedRepository;
    private UserProfileService userProfileService;

    /**
     * @param newFeedRepository  - inject newFeedRepository
     * @param userProfileService - inject userProfileService
     */
    public NewFeedService(NewFeedRepository newFeedRepository, UserProfileService userProfileService) {
        this.newFeedRepository = newFeedRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param newFeed - newfeed that user want to add to the database
     * @return inserted newfeed
     */
    public NewFeed addNewFeed(NewFeed newFeed) {
        return this.newFeedRepository.saveAndFlush(newFeed);
    }

    /**
     * @param newFeedStatus          - newfeeds' status that user want to filter
     *                               newfeeds (this parameter could be optional)
     * @param newFeedContentKeywords - newfeed's content's keywords that user want
     *                               to get newfeeds (this parameter could be
     *                               optional)
     * @param startIndex             - start index to get newfeeds (for pagination)
     * @return list of newfeeds
     */
    public List<NewFeed> getNewFeedsByPage(Integer newFeedStatus, String newFeedContentKeywords, Integer startIndex) {
        List<Object> newFeedsObjectList = this.newFeedRepository.getNewFeedsByPage(newFeedStatus,
                newFeedContentKeywords, startIndex);
        return this.getNewFeedsFromObjectList(newFeedsObjectList);
    }

    /**
     * @param newFeedStatus          - newfeeds' status that user want to filter
     *                               newfeeds (this parameter could be optional)
     * @param newFeedContentKeywords - newfeed's content's keywords that user want
     *                               to get newfeeds (this parameter could be
     *                               optional)
     * @return list of newfeeds
     */
    public List<NewFeed> getNewFeeds(Integer newFeedStatus, String newFeedContentKeywords) {
        List<Object> newFeedsObjectList = this.newFeedRepository.getNewFeeds(newFeedStatus, newFeedContentKeywords);
        return this.getNewFeedsFromObjectList(newFeedsObjectList);
    }

    /**
     * @param newFeedStatus          - newfeeds' status that user want to filter
     *                               newfeeds (this parameter could be optional)
     * @param newFeedContentKeywords - newfeed's content's keywords that user want
     *                               to get newfeeds (this parameter could be
     *                               optional)
     * @return number of newfeeds by newfeed's
     */
    public int getNumberOfNewFeeds(Integer newFeedStatus, String newFeedContentKeywords) {
        List<Object> nNewFeedsObjectList = this.newFeedRepository.getNumberOfNewFeeds(newFeedStatus,
                newFeedContentKeywords);
        return Integer.valueOf(nNewFeedsObjectList.get(0).toString());
    }

    /**
     * @param newFeedsObjectList - newfeed's object list that user want to convert
     *                           to newfeed's list
     * @return list of newfeeds
     */
    private List<NewFeed> getNewFeedsFromObjectList(List<Object> newFeedsObjectList) {
        List<NewFeed> newFeeds = new ArrayList<>();
        for (Object o : newFeedsObjectList) {
            Object[] newFeedObjectArr = (Object[]) o;
            NewFeed newFeed = this.createNewFeedFromObjectArr(newFeedObjectArr);
            newFeeds.add(newFeed);
        }
        return newFeeds;
    }

    /**
     * @param newFeedObjectArr - newfeed's object array that user want to convert to
     *                         newfeed object
     * @return converted newfeed object
     */
    private NewFeed createNewFeedFromObjectArr(Object[] newFeedObjectArr) {
        int newFeedId = (int) newFeedObjectArr[0];
        String newFeedImage = (String) newFeedObjectArr[1];
        String newFeedAchievement = (String) newFeedObjectArr[2];
        String newFeedAchievementTime = (String) newFeedObjectArr[3];
        String newFeedContent = (String) newFeedObjectArr[4];
        int userProfileId = (int) newFeedObjectArr[5];
        UserProfile selectedUserProfile = this.getUserProfile((long) userProfileId);
        Date createdDate = (Date) newFeedObjectArr[6];
        int newFeedStatus = (int) newFeedObjectArr[7];
        int nNewFeedLikes = (int) newFeedObjectArr[8];
        int nNewFeedDislikes = (int) newFeedObjectArr[9];
        int nNewFeedComments = (int) newFeedObjectArr[10];
        return new NewFeed((long) newFeedId, newFeedImage, newFeedAchievement, newFeedAchievementTime, newFeedContent,
                newFeedStatus, createdDate, selectedUserProfile, nNewFeedLikes, nNewFeedDislikes, nNewFeedComments);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param id - newfeed's id that user want to get
     * @return selected newfeed
     */
    public NewFeed getNewFeedById(Long id) {
        return this.newFeedRepository.findNewFeedById(id);
    }
}
