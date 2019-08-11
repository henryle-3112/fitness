package henry.greenwich.fitness.controller.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.user.UserProfileService;

import java.util.List;

@Controller
public class UserProfileController {

    /**
     * userProfileService - interact with user's profile data
     */
    private UserProfileService userProfileService;

    /**
     *
     * @param userProfileService - inject user's profile's service
     */
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    /**
     *
     * @return list of user's profile
     */
    @GetMapping(value = "/users/profiles", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<UserProfile> getUserProfiles() {
        return this.userProfileService.getUserProfiles();
    }

    /**
     *
     * @param id - user's profile's id that user want to get
     * @return selected user's profile
     */
    @GetMapping(value = "/users/profiles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserProfile getUserProfile(@PathVariable Long id) {
        return this.userProfileService.getUserProfile(id);
    }

    /**
     *
     * @param userProfile - that user want to add to the database
     * @return userProfile - that was inserted to the database
     */
    @PostMapping(value = "/users/profiles/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserProfile addUserProfile(@RequestBody UserProfile userProfile) {
        return this.userProfileService.addUserProfile(userProfile);
    }

    /**
     *
     * @param userProfile - userProfile that user want to update to the database
     * @return userProfile - that was updated to the database
     */
    @PostMapping(value = "/users/profiles/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return this.userProfileService.updateUserProfile(userProfile);
    }

    /**
     *
     * @param id - user's profile's id that user want to delete
     */
    @PostMapping(value = "/users/profiles/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteUserProfile(@PathVariable Long id) {
        this.userProfileService.deleteUserProfile(id);
    }
}
