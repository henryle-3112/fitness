package henry.greenwich.fitness.controller.register;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.*;
import henry.greenwich.fitness.service.user.*;

import henry.greenwich.fitness.util.EmailServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

	/**
	 * userAccountService - interact with user's account data
	 * userProfileService - interact with user's profile data
	 * userAccountStatusService - interact with user's account's status data
	 * bCryptPasswordEncoder - encrypt user's password
	 * emailService - send email active account
	 * roleService - interact with role's data
	 * userRoleService - interact with user's role data
	 */
	private UserAccountService userAccountService;
	private UserProfileService userProfileService;
	private UserAccountStatusService userAccountStatusService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private EmailServiceImpl emailService;
	private RoleService roleService;
	private UserRoleService userRoleService;

	/**
	 *
	 * @param userAccountService - inject userAccountService
	 * @param userProfileService - inject userProfileService
	 * @param userAccountStatusService - inject userAccountStatusService
	 * @param bCryptPasswordEncoder - inject bCryptPasswordEncoder
	 * @param emailService - inject emailService
	 */
	public RegisterController(UserAccountService userAccountService,
							  UserProfileService userProfileService,
							  UserAccountStatusService userAccountStatusService,
							  BCryptPasswordEncoder bCryptPasswordEncoder,
							  EmailServiceImpl emailService,
							  RoleService roleService,
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
	 *
	 * @param userAccount - userAccount that could be inserted to the database
	 * @return
	 */
    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage register(@RequestBody UserAccount userAccount) {
        try {
			// get user's account email to check account existed or not 
			String email = userAccount.getUserName();
			UserAccount existedUserAccount = this.userAccountService.findUserAccountByUsername(email);
			if (existedUserAccount != null) {
				// user's account existed. Therefore, user's account cannot be inserted to the database
				return new ResponseMessage("failure");
			}
        	// add user's profile to the database
        	UserProfile insertedUserProfile = this.userProfileService.addUserProfile(userAccount.getUserProfile());
        	// find user's account's status 
        	UserAccountStatus selectedUserAccountStatus = this.userAccountStatusService.getUserAccountStatusByName(userAccount.getUserAccountStatus().getName());
        	// encrypt user's password
			String encryptedUserPassword = this.bCryptPasswordEncoder.encode(userAccount.getPassword());
			userAccount.setPassword(encryptedUserPassword);
        	// set user's profile and user's account's status to user's account
        	userAccount.setUserAccountStatus(selectedUserAccountStatus);
        	userAccount.setUserProfile(insertedUserProfile);
        	// user's profile id
			UserProfileKey userProfileKey = new UserProfileKey(insertedUserProfile.getId());
        	userAccount.setUserProfileId(userProfileKey);
        	// generate email confirmation token
			String token = this.generateEmailConfirmationToken(email);
			userAccount.setEmailConfirmationToken(token);
        	// add user's account to the database
        	this.userAccountService.addUserAccount(userAccount);
        	// add user's role
			this.insertUserRoles(insertedUserProfile, userProfileKey);
        	// if user's account and user's profile were inserted successfully
			// create email content (contains link that customer can click to change password)
			String emailContent = "Please click on the link to active your account <a href=\"http://localhost:4200/active?token=" + token + "\">Active Account</a>";
			this.emailService.sendSimpleMessage(email, Constants.EMAIL_SUBJECT_ACTIVE_ACCOUNT, emailContent);
			// return response message to tell user that the account was inserted successfully or not
            return new ResponseMessage("successfully");
        } catch (Exception e) {
        	// return response message to tell user that the account cannot be inserted
            return new ResponseMessage("failure");
        }
    }

	private void insertUserRoles(UserProfile insertedUserProfile, UserProfileKey userProfileKey) {
		Role role = this.roleService.getRoleByName("ROLE_USER");
		if (role != null) {
			UserRoleKey userRoleKey = new UserRoleKey(userProfileKey, role.getId());
			UserRole userRole = new UserRole(userRoleKey, insertedUserProfile, role);
			this.userRoleService.addUserRole(userRole);
		}
    }

	/**
	 *
	 * @param email - use user's email to generate token
	 * @return emailConfirmationToken
	 */
	private String generateEmailConfirmationToken(String email) {
		return JWT.create()
				.withSubject(email)
				.sign(Algorithm.HMAC512(email.getBytes()));
	}
}
