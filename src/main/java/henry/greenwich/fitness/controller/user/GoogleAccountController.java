package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.model.user.GoogleAccount;
import henry.greenwich.fitness.service.user.GoogleAccountService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user-management")
public class GoogleAccountController {
    private GoogleAccountService googleAccountService;

    /**
     * @param googleAccountService - inject googleAccountService
     */
    public GoogleAccountController(GoogleAccountService googleAccountService) {
        this.googleAccountService = googleAccountService;
    }

    @GetMapping(value = "/google/{googleId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public GoogleAccount getGoogleAccountByGoogleId(@PathVariable String googleId) {
        return this.googleAccountService.findGoogleAccountByGoogleId(googleId);
    }
}
