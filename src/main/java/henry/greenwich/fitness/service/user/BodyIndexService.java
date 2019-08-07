package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.BodyIndex;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.user.BodyIndexRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BodyIndexService {
    private BodyIndexRepository bodyIndexRepository;
    private UserProfileService userProfileService;

    /**
     * @param bodyIndexRepository - inject bodyIndexRepository
     * @param userProfileService  - inject userProfileService
     */
    public BodyIndexService(BodyIndexRepository bodyIndexRepository, UserProfileService userProfileService) {
        this.bodyIndexRepository = bodyIndexRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of body
     *                      indexes
     * @return list of body indexes
     */
    public List<BodyIndex> getBodyIndexes(Integer userProfileId) {
        List<Object> bodyIndexesObjectList = this.bodyIndexRepository.getBodyIndexes((long) userProfileId);
        return this.createBodyIndexesFromObjectList(bodyIndexesObjectList);
    }

    /**
     * @param bodyIndexesObjectList - body indexes object list that user want to
     *                              convert to list of body indexes
     * @return list of body indexes
     */
    private List<BodyIndex> createBodyIndexesFromObjectList(List<Object> bodyIndexesObjectList) {
        List<BodyIndex> bodyIndexes = new ArrayList<>();
        for (Object o : bodyIndexesObjectList) {
            Object[] bodyIndexObjectArr = (Object[]) o;
            BodyIndex bodyIndex = this.createBodyIndexFromObjectArray(bodyIndexObjectArr);
            bodyIndexes.add(bodyIndex);
        }
        return bodyIndexes;
    }

    /**
     * @param bodyIndexObjectArr - body index object array that user want to convert
     *                           to body index
     * @return converted body index
     */
    private BodyIndex createBodyIndexFromObjectArray(Object[] bodyIndexObjectArr) {
        float selectedWeight = (float) bodyIndexObjectArr[0];
        float selectedHeight = (float) bodyIndexObjectArr[1];
        String currentDate = (String) bodyIndexObjectArr[2];
        int userProfileId = (int) bodyIndexObjectArr[3];
        int bodyIndexId = (int) bodyIndexObjectArr[4];
        UserProfile selectedUserProfile = this.getUserProfile((long) userProfileId);
        return new BodyIndex((long) bodyIndexId, selectedWeight, selectedHeight, currentDate, selectedUserProfile);
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
     * @param bodyIndex - body's index that user want to add to the database
     * @return inserted bodyIndex
     */
    public BodyIndex addBodyIndex(BodyIndex bodyIndex) {
        return this.bodyIndexRepository.saveAndFlush(bodyIndex);
    }

    /**
     * @param bodyIndex - body index that user want to update to the database
     * @return updated bodyIndex
     */
    public BodyIndex updateBodyIndex(BodyIndex bodyIndex) {
        // check body index existed in the database or not
        BodyIndex selectedBodyIndex = this.getBodyIndexByCurrentDate(bodyIndex.getCurrentDate());
        if (selectedBodyIndex == null) {
            return this.addBodyIndex(bodyIndex);
        }
        selectedBodyIndex.setWeight(bodyIndex.getWeight());
        selectedBodyIndex.setHeight(bodyIndex.getHeight());
        selectedBodyIndex.setUserProfile(bodyIndex.getUserProfile());
        return this.bodyIndexRepository.save(selectedBodyIndex);
    }

    /**
     * @param currentDate - date that user want to get selected body index
     * @return selected body index
     */
    private BodyIndex getBodyIndexByCurrentDate(String currentDate) {
        return this.bodyIndexRepository.findBodyIndexByCurrentDate(currentDate);
    }
}
