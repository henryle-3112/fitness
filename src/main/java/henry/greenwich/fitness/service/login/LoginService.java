package henry.greenwich.fitness.service.login;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.FacebookAccount;
import henry.greenwich.fitness.model.user.GoogleAccount;
import henry.greenwich.fitness.model.user.Role;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.model.user.UserProfileKey;
import henry.greenwich.fitness.model.user.UserRole;
import henry.greenwich.fitness.model.user.UserRoleKey;
import henry.greenwich.fitness.service.user.FacebookAccountService;
import henry.greenwich.fitness.service.user.GoogleAccountService;
import henry.greenwich.fitness.service.user.RoleService;
import henry.greenwich.fitness.service.user.UserProfileService;
import henry.greenwich.fitness.service.user.UserRoleService;

@Service
public class LoginService {
    private FacebookAccountService facebookAccountService;
    private GoogleAccountService googleAccountService;
    private UserProfileService userProfileService;
    private UserRoleService userRoleService;
    private RoleService roleService;

    /**
     * @param facebookAccountService - inject facebookAccountService
     * @param userProfileService     - inject userProfileService
     * @param userRoleService        - inject userRoleService
     * @param googleAccountService   - inject googleAccountService
     * @param roleService            - inject roleService
     */
    public LoginService(FacebookAccountService facebookAccountService, UserProfileService userProfileService,
            UserRoleService userRoleService, GoogleAccountService googleAccountService, RoleService roleService) {
        this.facebookAccountService = facebookAccountService;
        this.userProfileService = userProfileService;
        this.userRoleService = userRoleService;
        this.googleAccountService = googleAccountService;
        this.roleService = roleService;
    }

    /**
     * @param response        - is used to pass to generate JWT function to generate
     *                        JWT
     * @param facebookAccount - facebookAccount would be authenticated
     */
    public void loginByFacebookAccount(HttpServletResponse response, FacebookAccount facebookAccount) {
        try {
            FacebookAccount selectedFacebookAccount = this
                    .getFacebookAccountByFacebookId(facebookAccount.getFacebookId());
            if (selectedFacebookAccount != null) {
                boolean isFacebookAccountActivatedd = selectedFacebookAccount.getUserProfile().getStatus() != 1;
                if (isFacebookAccountActivatedd) {
                    this.sendErrorResponse(response);
                } else {
                    this.sendJWT(response, selectedFacebookAccount.getUserProfile().getFullName(),
                            selectedFacebookAccount.getUserProfile().getId(), selectedFacebookAccount.getFacebookId());
                }
                return;
            }
            this.registerNewFacebookAccount(response, facebookAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param response        - response will be used to send response as json
     *                        format
     * @param facebookAccount - facebook's account that would be registered to the
     *                        system
     */
    private void registerNewFacebookAccount(HttpServletResponse response, FacebookAccount facebookAccount) {
        UserProfile insertedUserProfile = this.addUserProfile(facebookAccount.getUserProfile());
        facebookAccount.setUserProfile(insertedUserProfile);
        UserProfileKey userProfileKey = new UserProfileKey(insertedUserProfile.getId());
        facebookAccount.setUserProfileId(userProfileKey);
        FacebookAccount insertedFacebookAccount = this.addFacebookAccount(facebookAccount);
        if (insertedFacebookAccount != null) {
            // add to user's role table (default ROLE_USER)
            // get selected role from the database
            Role role = this.getRoleByName("ROLE_USER");
            if (role != null) {
                this.insertUserRole(role, insertedUserProfile, userProfileKey);
                this.sendJWT(response, insertedFacebookAccount.getUserProfile().getFullName(),
                        insertedFacebookAccount.getUserProfile().getId(), insertedFacebookAccount.getFacebookId());
            }
        }
    }

    /**
     * 
     * @param userProfile - user's profile that user want to add to the database
     * @return inserted users profile
     */
    private UserProfile addUserProfile(UserProfile userProfile) {
        return this.userProfileService.addUserProfile(userProfile);
    }

    /**
     * 
     * @param facebookId - facebookId that user want to get selected facebook's
     *                   account
     * @return selected facebook's account
     */
    private FacebookAccount getFacebookAccountByFacebookId(String facebookId) {
        return this.facebookAccountService.findFacebookAccountByFacebookId(facebookId);
    }

    /**
     * 
     * @param facebookAccount - facebook's account that user want to add to the
     *                        database
     * @return inserted facebook's account
     */
    private FacebookAccount addFacebookAccount(FacebookAccount facebookAccount) {
        return this.facebookAccountService.addFacebookAccount(facebookAccount);
    }

    /**
     * @param roleName - role's name that user want to get role
     */
    private Role getRoleByName(String roleName) {
        return this.roleService.getRoleByName(roleName);
    }

    public void loginByGoogleAccount(HttpServletResponse response, GoogleAccount googleAccount) {
        try {
            GoogleAccount selectedGoogleAccount = this.getGoogleAccountByGoogleId(googleAccount.getGoogleId());
            if (selectedGoogleAccount != null) {
                boolean isGoogleAccountActivated = selectedGoogleAccount.getUserProfile().getStatus() != 1;
                if (isGoogleAccountActivated) {
                    this.sendErrorResponse(response);
                } else {
                    this.sendJWT(response, selectedGoogleAccount.getUserProfile().getFullName(),
                            selectedGoogleAccount.getUserProfile().getId(), selectedGoogleAccount.getGoogleId());
                }
                return;
            }
            this.registerGoogleAccount(response, googleAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param googleId - google's id that user want to get selected google's account
     * @return selected google's account
     */
    private GoogleAccount getGoogleAccountByGoogleId(String googleId) {
        return this.googleAccountService.findGoogleAccountByGoogleId(googleId);
    }

    /**
     * 
     * @param response      - response will be used to send response as json format
     * @param googleAccount - google's account that would be register to the system
     */
    private void registerGoogleAccount(HttpServletResponse response, GoogleAccount googleAccount) {
        UserProfile insertedUserProfile = this.addUserProfile(googleAccount.getUserProfile());
        googleAccount.setUserProfile(insertedUserProfile);
        UserProfileKey userProfileKey = new UserProfileKey(insertedUserProfile.getId());
        googleAccount.setUserProfileId(userProfileKey);
        GoogleAccount insertedGoogleAccount = this.addGoogleAccount(googleAccount);
        if (insertedGoogleAccount != null) {
            Role role = this.getRoleByName("ROLE_USER");
            if (role != null) {
                this.insertUserRole(role, insertedUserProfile, userProfileKey);
                this.sendJWT(response, insertedGoogleAccount.getUserProfile().getFullName(),
                        insertedGoogleAccount.getUserProfile().getId(), insertedGoogleAccount.getGoogleId());
            }

        }
    }

    /**
     * 
     * @param googleAccount - google's account that user want to add to the database
     * @return inserted google's account
     */
    private GoogleAccount addGoogleAccount(GoogleAccount googleAccount) {
        return this.googleAccountService.addGoogleAccount(googleAccount);
    }

    /**
     * 
     * @param response - response will be used to send error message as json format
     *                 to user when something goes wrong
     */
    private void sendErrorResponse(HttpServletResponse response) {
        try {
            String responseData = "{\"message\": \"failure\"}";
            // create gson object to return response data as json format
            Gson gson = new Gson();
            // configure response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(gson.toJson(responseData.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param role                - role of new account (default is USER_ROLE)
     * @param insertedUserProfile - insertedUserProfile
     * @param userProfileKey      - userProfileKey
     */
    private void insertUserRole(Role role, UserProfile insertedUserProfile, UserProfileKey userProfileKey) {
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUserProfile(insertedUserProfile);
        UserRoleKey userRoleKey = new UserRoleKey(userProfileKey, role.getId());
        userRole.setUserRoleKey(userRoleKey);
        this.userRoleService.addUserRole(userRole);
    }

    /**
     * @param response      - HttpServletResponse respose to send response to user
     * @param userName      - userName of user (is used to generated JWT and send to
     *                      client)
     * @param userProfileId - user's profile's id - to get roles of user
     */
    private void sendJWT(HttpServletResponse response, String userName, Long userProfileId, String socialId) {
        try {
            String token = this.generateJWT(socialId);
            StringBuilder responseData = new StringBuilder("{\"userName\": \"" + userName + "\", \"token\": \""
                    + Constants.TOKEN_PREFIX + token + "\", \"roles\": [");
            // get user's authorities then add to string to convert to json format
            int selectedUserProfileId = Math.toIntExact(userProfileId);
            List<Object> userRoles = this.userRoleService.findUserRoles(selectedUserProfileId);
            for (int i = 0; i < userRoles.size(); i++) {
                if (i != userRoles.size() - 1) {
                    responseData.append("\"").append(userRoles.get(i)).append("\"").append(", ");
                } else {
                    responseData.append("\"").append(userRoles.get(i)).append("\"");
                }
            }
            responseData.append("]}");
            // return jwt toke and return user's information
            // create gson object to return response data as json format
            Gson gson = new Gson();
            // configure response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + token);
            response.getWriter().print(gson.toJson(responseData.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param socialId - use socialId (facebook's id or google's id to generate
     *                 token)
     * @return emailConfirmationToken
     */
    private String generateJWT(String socialId) {
        return JWT.create().withSubject(socialId)
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(Constants.SECRET.getBytes()));
    }

}
