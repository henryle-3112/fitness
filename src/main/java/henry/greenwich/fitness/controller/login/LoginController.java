package henry.greenwich.fitness.controller.login;

import henry.greenwich.fitness.model.user.*;
import henry.greenwich.fitness.service.login.LoginService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private LoginService loginService;

    /**
     * @param loginService - inject loginService
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * @param response        - is used to pass to generate JWT function to generate
     *                        JWT
     * @param facebookAccount - facebookAccount would be authenticated
     */
    @PostMapping(value = "/login/facebook", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void loginByFacebook(HttpServletResponse response, @RequestBody FacebookAccount facebookAccount) {
        this.loginService.loginByFacebookAccount(response, facebookAccount);
    }

    /**
     * @param response      - is used to pass to generate JWT function to generate
     *                      JWT
     * @param googleAccount - googleAccount would be authenticated
     */
    @PostMapping(value = "/login/google", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void loginByGoogle(HttpServletResponse response, @RequestBody GoogleAccount googleAccount) {
        this.loginService.loginByGoogleAccount(response, googleAccount);
    }
}
