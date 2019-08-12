package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.service.user.ResetPasswordService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserAccount;

import java.text.ParseException;

@Controller
@RequestMapping("user-management")
public class ResetPasswordController {
    private ResetPasswordService resetPasswordService;

    /**
     * @param resetPasswordService - inject resetPasswordService
     */
    public ResetPasswordController(ResetPasswordService resetPasswordService) {
        this.resetPasswordService = resetPasswordService;
    }

    /**
     * @param email - email of user
     * @return message - email was sent successfully or not
     */
    @PostMapping(value = "/send-email-reset-password", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage sendEmailResetPassword(@RequestBody String email) {
        boolean isEmailSentSuccessfully = this.resetPasswordService.sendEmailResetPassword(email);
        if (isEmailSentSuccessfully) {
            return new ResponseMessage("success");
        }
        return new ResponseMessage("failure");
    }

    /**
     * @param updatedUserAccount - updated user's account that contains new password
     * @return responseMessage - contains message successfully or not
     */
    @PostMapping(value = "/change-password", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage changePassword(@RequestBody UserAccount updatedUserAccount) throws ParseException {
        boolean isPasswordChangedSuccessfully = this.resetPasswordService.changePassword(updatedUserAccount);
        if (isPasswordChangedSuccessfully) {
            return new ResponseMessage("success");
        }
        return new ResponseMessage("failure");
    }

}
