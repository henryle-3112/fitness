package henry.greenwich.fitness.controller.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.user.UserAccountStatus;
import henry.greenwich.fitness.service.user.UserAccountStatusService;

import java.util.List;

@Controller
public class UserAccountStatusController {
    /**
     * userAccountStatusService - interact with userAccountStatus service
     */
    private UserAccountStatusService userAccountStatusService;

    /**
     *
     * @param userAccountStatusService
     */
    public UserAccountStatusController(UserAccountStatusService userAccountStatusService) {
        this.userAccountStatusService = userAccountStatusService;
    }

    /**
     *
     * @return list of user's account's status
     */
    @GetMapping(value = "/users/status", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<UserAccountStatus> getUserAccountStatusList() {
        return this.userAccountStatusService.getUserAccountStatusList();
    }

    /**
     *
     * @param id - user's account's id that user want to get
     * @return selected user's account's status's id
     */
    @GetMapping(value = "/users/status/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccountStatus getUserAccountStatus(@PathVariable Long id) {
        return this.userAccountStatusService.getUserAccountStatus(id);
    }

    /**
     *
     * @param userAccountStatus - user's account's status that user want to add to the database
     * @return userAccountStatus - that was inserted to the database
     */
    @PostMapping(value = "/users/status/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccountStatus addUserAccountStatus(@RequestBody UserAccountStatus userAccountStatus) {
        return this.userAccountStatusService.addUserAccountStatus(userAccountStatus);
    }

    /**
     *
     * @param userAccountStatus - userAccountStatus that user want to update to the database
     * @return userAccountStatus - that was updated to the database
     */
    @PostMapping(value = "/users/status/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccountStatus updateUserAccountStatus(@RequestBody UserAccountStatus userAccountStatus) {
        return this.userAccountStatusService.updateUserAccountStatus(userAccountStatus);
    }

    /**
     *
     * @param id - user's account's status's id that user want to delete
     */
    @PostMapping(value = "/users/status/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteUserAccountStatus(@PathVariable Long id) {
        this.userAccountStatusService.deleteUserAccountStatus(id);
    }

}
