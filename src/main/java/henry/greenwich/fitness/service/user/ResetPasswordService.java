package henry.greenwich.fitness.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.util.EmailServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ResetPasswordService {

    private EmailServiceImpl emailService;
    private UserAccountService userAccountService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param emailService          - inject emailService
     * @param userAccountService    - inject userAccountService
     * @param bCryptPasswordEncoder - inject bCryptPasswordEncoder
     */
    public ResetPasswordService(EmailServiceImpl emailService,
                                UserAccountService userAccountService,
                                BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.emailService = emailService;
        this.userAccountService = userAccountService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * @param updatedUserAccount - updated user's account that contains new password
     * @return responseMessage - contains message successfully or not
     */
    public boolean changePassword(UserAccount updatedUserAccount) throws ParseException {
        // check token and its expiration
        UserAccount userAccount = this
                .getUserAccountByPasswordReminderToken(updatedUserAccount.getPasswordReminderToken());
        if (userAccount == null) {
            return false;
        }
        // check expiration
        Date userPasswordReminderExpired = userAccount.getPasswordReminderExpired();
        boolean isTokenExpired = this.checkTokenExpired(userPasswordReminderExpired);
        if (isTokenExpired) {
            return false;
        }
        this.changeUserPassword(userAccount, updatedUserAccount);
        return true;
    }

    /**
     * @param email - email of user
     * @return message - email was sent successfully or not
     */
    public boolean sendEmailResetPassword(String email) {
        UserAccount userAccount = this.getUserAccountByUserName(email);
        if (userAccount == null) {
            return false;
        }
        String passwordReminderToken = this.generatePasswordReminderToken(email);
        this.updatePasswordReminderTokenToUserAccount(userAccount, passwordReminderToken);
        this.sendResetPasswordEmailToUser(email, passwordReminderToken);
        return true;
    }

    /**
     * @param userAccount           - user's account that would be updated (update
     *                              password reminder token)
     * @param passwordReminderToken - password reminder token that would be updated
     *                              to user's account
     */
    private void updatePasswordReminderTokenToUserAccount(UserAccount userAccount, String passwordReminderToken) {
        userAccount.setPasswordReminderToken(passwordReminderToken);
        Date expiredDate = this.generatePasswordReminderExpired();
        userAccount.setPasswordReminderExpired(expiredDate);
        this.userAccountService.updateUserAccount(userAccount);
    }

    /**
     * @param email                 - user's email that would be received reset
     *                              password email
     * @param passwordReminderToken - passwod reminder token
     */
    private void sendResetPasswordEmailToUser(String email, String passwordReminderToken) {
        String emailContent = "Please click on the link to reset your password <a href=\"http://localhost:4200/change-password?token="
                + passwordReminderToken + "\">Reset Password</a>";
        this.emailService.sendSimpleMessage(email, Constants.EMAIL_SUBJECT_RESET_PASSWORD, emailContent);
    }

    /**
     * @param passwordReminderToken - password reminder token that user want to get
     *                              selected user's account
     * @return selected user's account
     */
    private UserAccount getUserAccountByPasswordReminderToken(String passwordReminderToken) {
        return this.userAccountService.findUserAccountByPasswordReminderToken(passwordReminderToken);
    }

    /**
     * @param userName - user's name that user want to get selected user's account
     * @return selected user's account
     */
    private UserAccount getUserAccountByUserName(String userName) {
        return this.userAccountService.findUserAccountByUsername(userName);
    }

    /**
     * @param userPasswordReminderExpired - user's password reminder expired date to
     *                                    token password reminder expired or not
     * @return token password reminder expired or not
     * @throws ParseException - parse date exception
     */
    private boolean checkTokenExpired(Date userPasswordReminderExpired) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String strPasswordReminderExpired = sdf.format(userPasswordReminderExpired);
        String strCurrentDate = sdf.format(currentDate);
        return sdf.parse(strCurrentDate).compareTo(sdf.parse(strPasswordReminderExpired)) > 0;
    }

    /**
     * @param userAccount        - current user's account
     * @param updatedUserAccount - updated user's account
     */
    private void changeUserPassword(UserAccount userAccount, UserAccount updatedUserAccount) {
        String updatedEncryptedPassword = this.bCryptPasswordEncoder.encode(updatedUserAccount.getPassword());
        userAccount.setPassword(updatedEncryptedPassword);
        this.userAccountService.updateUserAccount(userAccount);
    }

    /**
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
        return JWT.create().withSubject(email).sign(Algorithm.HMAC512(email.getBytes()));
    }
}
