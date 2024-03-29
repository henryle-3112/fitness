package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.model.user.FacebookAccount;
import henry.greenwich.fitness.service.user.FacebookAccountService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user-management")
public class FacebookAccountController {
    private FacebookAccountService facebookAccountService;

    /**
     * @param facebookAccountService - inject facebookAccountService
     */
    public FacebookAccountController(FacebookAccountService facebookAccountService) {
        this.facebookAccountService = facebookAccountService;
    }

    @GetMapping(value = "/facebook/{facebookId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public FacebookAccount getFacebookAccountByFacebookId(@PathVariable String facebookId) {
        return this.facebookAccountService.findFacebookAccountByFacebookId(facebookId);
    }
}
