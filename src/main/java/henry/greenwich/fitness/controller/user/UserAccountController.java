package henry.greenwich.fitness.controller.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.service.user.UserAccountService;

@Controller
@RequestMapping("user-management")
public class UserAccountController {
    private UserAccountService userAccountService;

    /**
     * @param userAccountService - inject userAccountService
     */
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     * @param userName - user's name that user want to get user's account
     * @return selected user's account
     */
    @GetMapping(value = "/accounts", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserAccount getUserAccountByUsername(@RequestParam(required = false) String userName) {
        return this.userAccountService.getUserAccountByUsername(userName);
    }

    /**
     * @param userAccount - userAccount that user want to update to the database
     * @return updated userAccount
     */
    @PutMapping(value = "/accounts", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.updateUserAccount(userAccount);
    }

}
