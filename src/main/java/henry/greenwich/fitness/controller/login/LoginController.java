package henry.greenwich.fitness.controller.login;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.*;
import henry.greenwich.fitness.service.user.*;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
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
    public LoginController(FacebookAccountService facebookAccountService, UserProfileService userProfileService,
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
    @PostMapping(value = "/login/facebook", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void loginByFacebook(HttpServletResponse response, @RequestBody FacebookAccount facebookAccount) {
        try {
            FacebookAccount selectedFacebookAccount = this.facebookAccountService
                    .findFacebookAccountByFacebookId(facebookAccount.getFacebookId());
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
            UserProfile insertedUserProfile = this.userProfileService.addUserProfile(facebookAccount.getUserProfile());
            facebookAccount.setUserProfile(insertedUserProfile);
            UserProfileKey userProfileKey = new UserProfileKey(insertedUserProfile.getId());
            facebookAccount.setUserProfileId(userProfileKey);
            FacebookAccount insertedFacebookAccount = this.facebookAccountService.addFacebookAccount(facebookAccount);
            if (insertedFacebookAccount != null) {
                // add to user's role table (default ROLE_USER)
                // get selected role from the database
                Role role = this.roleService.getRoleByName("ROLE_USER");
                if (role != null) {
                    // insert user's role
                    this.insertUserRole(role, insertedUserProfile, userProfileKey);
                    // return generated token
                    this.sendJWT(response, insertedFacebookAccount.getUserProfile().getFullName(),
                            insertedFacebookAccount.getUserProfile().getId(), insertedFacebookAccount.getFacebookId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param response      - is used to pass to generate JWT function to generate
     *                      JWT
     * @param googleAccount - googleAccount would be authenticated
     */
    @PostMapping(value = "/login/google", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void loginByGoogle(HttpServletResponse response, @RequestBody GoogleAccount googleAccount) {
        try {
            // check google's account was inserted to the database or not
            GoogleAccount selectedGoogleAccount = this.googleAccountService
                    .findGoogleAccountByGoogleId(googleAccount.getGoogleId());
            if (selectedGoogleAccount != null) {
                // check account is actived or not
                if (selectedGoogleAccount.getUserProfile().getStatus() != 1) {
                    this.sendErrorResponse(response);
                } else {
                    // return generated token
                    this.sendJWT(response, selectedGoogleAccount.getUserProfile().getFullName(),
                            selectedGoogleAccount.getUserProfile().getId(), selectedGoogleAccount.getGoogleId());
                }
                return;
            }
            // add user's profile to the database
            UserProfile insertedUserProfile = this.userProfileService.addUserProfile(googleAccount.getUserProfile());
            // set user's profile
            googleAccount.setUserProfile(insertedUserProfile);
            // user's profile id
            UserProfileKey userProfileKey = new UserProfileKey(insertedUserProfile.getId());
            googleAccount.setUserProfileId(userProfileKey);
            // add google's account to the database
            GoogleAccount insertedGoogleAccount = this.googleAccountService.addGoogleAccount(googleAccount);
            if (insertedGoogleAccount != null) {
                // add to user's role table (default ROLE_USER)
                // get selected role from the database
                Role role = this.roleService.getRoleByName("ROLE_USER");
                if (role != null) {
                    // insert user's role
                    this.insertUserRole(role, insertedUserProfile, userProfileKey);
                    // return generated token
                    this.sendJWT(response, insertedGoogleAccount.getUserProfile().getFullName(),
                            insertedGoogleAccount.getUserProfile().getId(), insertedGoogleAccount.getGoogleId());
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
