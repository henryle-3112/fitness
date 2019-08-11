package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.model.user.UserAccountStatus;
import henry.greenwich.fitness.service.user.UserAccountStatusService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.service.user.UserAccountService;

@Controller
public class ActiveAccountController {

    /**
     * userAccountService - interact with user's account's data
     * userAccountStatusService - interact with user's account's status's data
     */
    private UserAccountService userAccountService;
    private UserAccountStatusService userAccountStatusService;

    /**
     *
     * @param userAccountService - inject userAccountService
     * @param userAccountStatusService - inject userAccountStatusService
     */
    public ActiveAccountController(UserAccountService userAccountService, UserAccountStatusService userAccountStatusService) {
        this.userAccountService = userAccountService;
        this.userAccountStatusService = userAccountStatusService;
    }

    /**
     *
     * @param userAccount - userAccount that user want to active
     * @return responseMessage - to tell user that selected user's account was activated or not
     */
    @PostMapping(value = "/active/account", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage activeAccount(@RequestBody UserAccount userAccount) {
        // get emailConfirmationToken
        String emailConfirmationToken = userAccount.getEmailConfirmationToken();
        // get selected userAccount by emailConfirmationToken
        UserAccount selectedUserAccount = this.userAccountService.findUserAccountByEmailConfirmationToken(emailConfirmationToken);
        if (selectedUserAccount != null) {
            // get selected user account status
            UserAccountStatus confirmedUserAccountStatus = this.userAccountStatusService.getUserAccountStatusByName("EMAIL_CONFIRMED");
            selectedUserAccount.setUserAccountStatus(confirmedUserAccountStatus);
            this.userAccountService.updateUserAccount(selectedUserAccount);
            return new ResponseMessage("successfully");
        }
        return new ResponseMessage("failure");
    }
}
