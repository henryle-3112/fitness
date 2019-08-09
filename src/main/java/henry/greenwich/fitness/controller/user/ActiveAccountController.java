package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.service.user.ActiveAccountService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserAccount;

@Controller
@RequestMapping("user-management")
public class ActiveAccountController {

    private ActiveAccountService activeAccountService;

    /**
     * @param activeAccountService - inject activeAccountService
     */
    public ActiveAccountController(ActiveAccountService activeAccountService) {
        this.activeAccountService = activeAccountService;
    }

    /**
     *
     * @param userAccount - userAccount that user want to active
     * @return responseMessage - to tell user that selected user's account was
     *         activated or not
     */
    @PostMapping(value = "/account/active", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseMessage activeAccount(@RequestBody UserAccount userAccount) {
        boolean isAccountActivatedSuccessfully = this.activeAccountService.activeUserAccount(userAccount);
        if (isAccountActivatedSuccessfully) {
            return new ResponseMessage("success");
        }
        return new ResponseMessage("failure");
    }
}
