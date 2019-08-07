package henry.greenwich.fitness.controller.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.user.UserProfileService;

@Controller
@RequestMapping("user-management")
public class UserProfileController {

    private UserProfileService userProfileService;

    /**
     * @param userProfileService - inject user's profile's service
     */
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    /**
     * @param userProfile - userProfile that user want to update to the database
     * @return userProfile - that was updated to the database
     */
    @PutMapping(value = "/profiles", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return this.userProfileService.updateUserProfile(userProfile);
    }
}
