package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.security.UserDetailsServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.service.user.UserAccountService;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class UserAccountController {
    /**
     * userAccountService to interact with user's account's data
     */
    private UserAccountService userAccountService;
    private UserDetailsServiceImpl userDetailsService;

    /**
     * @param userAccountService - inject userAccountService
     */
    public UserAccountController(UserAccountService userAccountService, UserDetailsServiceImpl userDetailsService) {
        this.userAccountService = userAccountService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * @return list of user's account
     */
    @GetMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<UserAccount> getUserAccountList() {
        return this.userAccountService.getUserAccountList();
    }

    /**
     * @param userName - user's name that user want to get user's account
     * @return selected user's account
     */
    @GetMapping(value = "/users/username", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccount getUserAccountByUsername(@RequestParam Optional<String> userName) {
        String selectedUserName = userName.orElse(null);
        if (selectedUserName != null) {
            return this.userAccountService.getUserAccountByUsername(selectedUserName);
        }
        return null;
    }

    /**
     * @param id - user's account's id that user want to get
     * @return selected user's account
     */
    @GetMapping(value = "/users/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccount getUserAccount(@PathVariable Long id) {
        return this.userAccountService.getUserAccount(id);
    }

    /**
     * @param userAccount - userAccount that user want to add to the database
     * @return userAccount that was inserted to the database
     */
    @PostMapping(value = "/users/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    /**
     * @param userAccount - userAccount that user want to update to the database
     * @return userAccount that was updated to the database
     */
    @PostMapping(value = "/users/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.updateUserAccount(userAccount);
    }

    /**
     * @param userAccount - userAccount that will be auhenticated
     * @return userAccount that was authenticated
     */
    @PostMapping(value = "/users/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserDetails authenticateUserAccount(@RequestBody UserAccount userAccount) {
        String userName = userAccount.getUserName();
        return this.userDetailsService.loadUserByUsername(userName);
    }

    /**
     * @param id - user's account's id that user want to delete
     */
    @PostMapping(value = "/users/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteUserAccount(@PathVariable Long id) {
        this.userAccountService.deleteUserAccount(id);
    }

}
