package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.model.user.BodyIndex;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.user.BodyIndexService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BodyIndexController {
    /**
     * bodyIndexService - interact with body index' data
     * userProfileService - interact with user's profile data
     */
    private BodyIndexService bodyIndexService;
    private UserProfileService userProfileService;

    /**
     *
     * @param bodyIndexService - inject bodyIndexService
     */
    public BodyIndexController(BodyIndexService bodyIndexService, UserProfileService userProfileService) {
        this.bodyIndexService = bodyIndexService;
        this.userProfileService = userProfileService;
    }

    /**
     *
     * @param userId - userId that user want body's index
     * @return list of body index
     */
    @GetMapping(value = "/index/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<BodyIndex> getBodyIndexById(@PathVariable Long userId) {
        // get list body index as object
        ArrayList<BodyIndex> listOfBodyIndex = new ArrayList<>();
        List<Object> bodyIndexObjects = this.bodyIndexService.getBodyIndexById(userId);
        for (Object eachBodyIndexObject : bodyIndexObjects) {
            Object[] selectedBodyIndexObject = (Object[]) eachBodyIndexObject;
            float selectedWeight = (float) selectedBodyIndexObject[0];
            float selectedHeight = (float) selectedBodyIndexObject[1];
            String currentDate = (String) selectedBodyIndexObject[2];
            int userProfileId = (int) selectedBodyIndexObject[3];
            int bodyIndexId = (int) selectedBodyIndexObject[4];
            UserProfile selectedUserProfile = this.userProfileService.getUserProfile((long) userProfileId);
            BodyIndex currentBodyIndex = new BodyIndex(
                    (long) bodyIndexId,
                    selectedWeight,
                    selectedHeight,
                    currentDate,
                    selectedUserProfile
            );
            listOfBodyIndex.add(currentBodyIndex);
        }
        return listOfBodyIndex;
    }

    /**
     *
     * @param bodyIndex - that user want to add to the database
     * @return bodyIndex that was inserted to the database
     */
    @PostMapping(value = "/index/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BodyIndex addBodyIndex(@RequestBody BodyIndex bodyIndex) {
        return this.bodyIndexService.addBodyIndex(bodyIndex);
    }

    /**
     *
     * @param bodyIndex - that user want to update to the database
     * @return bodyIndex that was updated to the database
     */
    @PostMapping(value = "/index/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BodyIndex updateBodyIndex(@RequestBody BodyIndex bodyIndex) {
        // check body index existed in the database or not
        BodyIndex selectedBodyIndex = this.bodyIndexService.getBodyIndexByCurrentDate(bodyIndex.getCurrentDate());
        if (selectedBodyIndex == null) {
            return this.bodyIndexService.addBodyIndex(bodyIndex);
        }
        selectedBodyIndex.setWeight(bodyIndex.getWeight());
        selectedBodyIndex.setHeight(bodyIndex.getHeight());
        selectedBodyIndex.setUserProfile(bodyIndex.getUserProfile());
        return this.bodyIndexService.updateBodyIndex(selectedBodyIndex);
    }
}
