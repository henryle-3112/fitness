package henry.greenwich.fitness.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.*;
import henry.greenwich.fitness.util.EmailServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private UserAccountService userAccountService;
    private UserProfileService userProfileService;
    private UserAccountStatusService userAccountStatusService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailServiceImpl emailService;
    private RoleService roleService;
    private UserRoleService userRoleService;

    /**
     * @param userAccountService       - inject userAccountService
     * @param userProfileService       - inject userProfileService
     * @param userAccountStatusService - inject userAccountStatusService
     * @param bCryptPasswordEncoder    - inject bCryptPasswordEncoder
     * @param emailService             - inject emailService
     */
    public RegisterService(UserAccountService userAccountService,
                           UserProfileService userProfileService,
                           UserAccountStatusService userAccountStatusService,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           EmailServiceImpl emailService, RoleService roleService,
                           UserRoleService userRoleService) {
        this.userAccountService = userAccountService;
        this.userProfileService = userProfileService;
        this.userAccountStatusService = userAccountStatusService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    /**
     * @param emailToReceiveConfirmEmail - email that would be received confirm
     *                                   email
     * @param emailConfirmationToken     - email confirmation token
     */
    private void sendConfirmEmalToUser(String emailToReceiveConfirmEmail, String emailConfirmationToken) {
        String emailContent = "Please click on the link to active your account <a href=\"http://localhost:4200/active?token="
                + emailConfirmationToken + "\">Active Account</a>";
        this.emailService.sendSimpleMessage(emailToReceiveConfirmEmail, Constants.EMAIL_SUBJECT_ACTIVE_ACCOUNT,
                emailContent);
    }

    /**
     * @param userAccount               - user's account that user want to add to
     *                                  the database
     * @param encryptedUserPassword     - user's password that would be encrypted
     * @param selectedUserAccountStatus - user's account's status that user want to
     *                                  add to user's account
     * @param insertedUserProfile       - user's profile that user want to add to
     *                                  user's account
     * @param userProfileKey            - user's profile's key
     * @param emailConfirmationToken    - email's confirmation's token that user
     *                                  want to add to user's account
     * @return inserted user's account
     */
    private UserAccount addUserAccount(UserAccount userAccount, String encryptedUserPassword,
                                       UserAccountStatus selectedUserAccountStatus, UserProfile insertedUserProfile, UserProfileKey userProfileKey,
                                       String emailConfirmationToken) {
        userAccount.setPassword(encryptedUserPassword);
        userAccount.setUserAccountStatus(selectedUserAccountStatus);
        userAccount.setUserProfile(insertedUserProfile);
        userAccount.setUserProfileId(userProfileKey);
        userAccount.setEmailConfirmationToken(emailConfirmationToken);
        return this.userAccountService.addUserAccount(userAccount);
    }

    /**
     * @param userAccount - user's account that user want to register or not
     * @return register successfully or not
     */
    public boolean register(UserAccount userAccount) {
        String emailToReceiveConfirmEmail = userAccount.getUserName();
        UserAccount existedUserAccount = this.getUserAccountByUserName(emailToReceiveConfirmEmail);
        if (existedUserAccount != null) {
            return false;
        }
        String emailConfirmationToken = this.generateEmailConfirmationToken(emailToReceiveConfirmEmail);
        UserProfile insertedUserProfile = this.addUserProfile(userAccount.getUserProfile());
        UserAccountStatus selectedUserAccountStatus = this
                .getUserAccountStatusByUserAccountStatusName(userAccount.getUserAccountStatus().getName());
        String encryptedUserPassword = this.bCryptPasswordEncoder.encode(userAccount.getPassword());
        UserProfileKey userProfileKey = new UserProfileKey(insertedUserProfile.getId());
        UserAccount insertedUserAccount = this.addUserAccount(userAccount, encryptedUserPassword,
                selectedUserAccountStatus, insertedUserProfile, userProfileKey, emailConfirmationToken);
        if (insertedUserAccount != null) {
            UserRole insertedUserRole = this.insertUserRoles(insertedUserProfile, userProfileKey);
            if (insertedUserRole != null) {
                this.sendConfirmEmalToUser(emailToReceiveConfirmEmail, emailConfirmationToken);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @param email - email that user want to get selected user's account
     * @return selected user's account
     */
    private UserAccount getUserAccountByUserName(String email) {
        return this.userAccountService.findUserAccountByUsername(email);
    }

    /**
     * @param userProfile - user's profile that user want to add to the database
     * @return inserted user's profile
     */
    private UserProfile addUserProfile(UserProfile userProfile) {
        return this.userProfileService.addUserProfile(userProfile);
    }

    /**
     * @param userAccountStatusName - user's account's status's name that user want
     *                              to get selected user's account's status
     * @return selected user's account's status
     */
    private UserAccountStatus getUserAccountStatusByUserAccountStatusName(String userAccountStatusName) {
        return this.userAccountStatusService.getUserAccountStatusByName(userAccountStatusName);
    }

    /**
     * @param insertedUserProfile - inserted user's profile that shouls be used to
     *                            add user's role
     * @param userProfileKey      - user's profile's key
     */
    private UserRole insertUserRoles(UserProfile insertedUserProfile, UserProfileKey userProfileKey) {
        Role role = this.roleService.getRoleByName("ROLE_USER");
        if (role != null) {
            UserRoleKey userRoleKey = new UserRoleKey(userProfileKey, role.getId());
            UserRole userRole = new UserRole(userRoleKey, insertedUserProfile, role);
            return this.userRoleService.addUserRole(userRole);
        }
        return null;
    }

    /**
     * @param email - use user's email to generate token
     * @return emailConfirmationToken
     */
    private String generateEmailConfirmationToken(String email) {
        return JWT.create().withSubject(email).sign(Algorithm.HMAC512(email.getBytes()));
    }
}
