package henry.greenwich.fitness.controller.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.service.user.UserAccountService;
import henry.greenwich.fitness.util.EmailServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ResetPasswordController {
    // emailService to send email reset password
    private EmailServiceImpl emailService;
    // userAccountRepository to interact with user's account's data
    private UserAccountService userAccountService;
    // BrcryptPasswordEncoder to encrypt new password
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 
     * @param emailService - inject emailService
     * @param userAccountService - inject userAccountService
     * @param bCryptPasswordEncoder - inject bCryptPasswordEncoder
     */
    public ResetPasswordController(EmailServiceImpl emailService, UserAccountService userAccountService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.emailService = emailService;
        this.userAccountService = userAccountService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * @param email - email of user
     * @return message - email was sent successfully or not
     */
    @PostMapping(value = "/send/email/reset/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage sendEmailResetPassword(@RequestBody String email) {
        // find user's account by email
        UserAccount userAccount = this.userAccountService.findUserAccountByUsername(email);
        if (userAccount == null) {
            return new ResponseMessage("failure");
        }
        // generate password reminder token
        String token = this.generatePasswordReminderToken(email);
        // update password reminder token to user's account
        userAccount.setPasswordReminderToken(token);
        // generate password reminder expired
        Date expiredDate = this.generatePasswordReminderExpired();
        // update password reminder expired to user's account
        userAccount.setPasswordReminderExpired(expiredDate);
        // update password reminder token and password reminder expired to the database before sending email to customers
        this.userAccountService.updateUserAccount(userAccount);
        // create email content (contains link that customer can click to change password)
        String emailContent = "Please click on the link to reset your password <a href=\"http://localhost:4200/change-password?token=" + token + "\">Reset Password</a>";
        this.emailService.sendSimpleMessage(email, Constants.EMAIL_SUBJECT_RESET_PASSWORD, emailContent);
        return new ResponseMessage("successfully");
    }

    /**
     *
     * @param updatedUserAccount - updated user's account that contains new password
     * @return responseMessage - contains message successfully or not
     */
    @PostMapping(value = "/change/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage changePassword(@RequestBody UserAccount updatedUserAccount) {
        // check token and its expiration
        UserAccount userAccount = this.userAccountService.findUserAccountByPasswordReminderToken(updatedUserAccount.getPasswordReminderToken());
        if (userAccount == null) {
            return new ResponseMessage("failure");
        }
        // check expiration
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date passwordReminderExpired = userAccount.getPasswordReminderExpired();
        Date currentDate = new Date();
        String strPasswordReminderExpired = sdf.format(passwordReminderExpired);
        String strCurrentDate = sdf.format(currentDate);
        try {
            if (sdf.parse(strCurrentDate).compareTo(sdf.parse(strPasswordReminderExpired)) > 0) {
                // expired
                return new ResponseMessage("failure");
            } else {
                // not expired, then changing password
                String updatedEncryptedPassword = this.bCryptPasswordEncoder.encode(updatedUserAccount.getPassword());
                userAccount.setPassword(updatedEncryptedPassword);
                this.userAccountService.updateUserAccount(userAccount);
                return new ResponseMessage("successfully");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // if token is not expired, then changing the password
        return new ResponseMessage("failure");
    }

    /**
     *
     * @return date - password reminder expired
     */
    private Date generatePasswordReminderExpired() {
        try {
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 3);
            currentDate = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(currentDate);
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param email - email of user
     * @return string - password reminder token
     */
    private String generatePasswordReminderToken(String email) {
        return JWT.create()
                .withSubject(email)
                .sign(Algorithm.HMAC512(email.getBytes()));
    }
}
