package henry.greenwich.fitness.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import henry.greenwich.fitness.constants.Constants;

import henry.greenwich.fitness.model.user.FacebookAccount;
import henry.greenwich.fitness.model.user.GoogleAccount;
import henry.greenwich.fitness.service.user.FacebookAccountService;
import henry.greenwich.fitness.service.user.GoogleAccountService;
import henry.greenwich.fitness.service.user.UserRoleService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private UserDetailsServiceImpl userDetailsService;
    private FacebookAccountService facebookAccountService;
    private UserRoleService userRoleService;
    private GoogleAccountService googleAccountService;

    /**
     * @param authManager            - inject authManager
     * @param userDetailsService     - inject userDetailsService
     * @param facebookAccountService - inject facebookAccountService
     * @param userRoleService        - inject userRoleService
     * @param googleAccountService   - inject googleAccountService
     */
    public JWTAuthorizationFilter(AuthenticationManager authManager, UserDetailsServiceImpl userDetailsService,
                                  FacebookAccountService facebookAccountService, UserRoleService userRoleService,
                                  GoogleAccountService googleAccountService) {
        super(authManager);
        this.userDetailsService = userDetailsService;
        this.facebookAccountService = facebookAccountService;
        this.userRoleService = userRoleService;
        this.googleAccountService = googleAccountService;
    }

    /**
     * @param request  - request
     * @param response - response
     * @param chain    - chain
     * @throws IOException      - throw IOException
     * @throws ServletException - throw ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(Constants.HEADER_STRING);

        if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Constants.HEADER_STRING);
        if (token != null) {
            // parse the token to get subject
            // subject could be:
            // 1. user' name if user login by normal account
            // 2. facebook's id if user login by facebook's account
            // 3. google's id if user login by google's account
            String subject = this.parseTokenToSubject(token);
            if (subject != null) {
                UserDetails user = this.userDetailsService.loadUserByUsername(subject);
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(subject, null, user.getAuthorities());
                } else {
                    // try to get test if it is facebook's account
                    FacebookAccount facebookAccount = this.getFacebookAccountByFacebookId(subject);
                    if (facebookAccount != null) {
                        return this.loadUserByFacebookAccount(facebookAccount, subject);
                    } else {
                        // try to get test if it is google's account
                        GoogleAccount googleAccount = this.getGoogleAccountByGoogleId(subject);
                        if (googleAccount != null) {
                            return this.loadUserByGoogleAccount(googleAccount, subject);
                        }
                        return null;
                    }
                }
            }
            return null;
        }
        return null;
    }

    /**
     * @param facebookId - facebook's id that user want to get facebook's account
     * @return facebook's account
     */
    private FacebookAccount getFacebookAccountByFacebookId(String facebookId) {
        return this.facebookAccountService.findFacebookAccountByFacebookId(facebookId);
    }

    /**
     * @param facebookAccount - facebook's account that will be used to load user's
     *                        information
     * @param subject         - facebook's id that will be used as response
     * @return UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken loadUserByFacebookAccount(FacebookAccount facebookAccount,
                                                                          String subject) {
        int selectedUserProfileId = Math.toIntExact(facebookAccount.getUserProfile().getId());
        List<Object> userRoles = this.userRoleService.findUserRoles(selectedUserProfileId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority((String) role)));
        return new UsernamePasswordAuthenticationToken(subject, null, authorities);
    }

    /**
     * @param googleId - google's id that user want to get selected google's account
     * @return selected google's account
     */
    private GoogleAccount getGoogleAccountByGoogleId(String googleId) {
        return this.googleAccountService.findGoogleAccountByGoogleId(googleId);
    }

    /**
     * @param googleAccount - google's account that will be used to load user's
     *                      information
     * @param subject       - google's id that will be used as response
     * @return UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken loadUserByGoogleAccount(GoogleAccount googleAccount, String subject) {
        int selectedUserProfileId = Math.toIntExact(googleAccount.getUserProfile().getId());
        List<Object> userRoles = this.userRoleService.findUserRoles(selectedUserProfileId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority((String) role)));
        return new UsernamePasswordAuthenticationToken(subject, null, authorities);
    }

    /**
     * @param token - token that user want to use to parse to subject
     * @return subject (user's email or facebook's id or google's id)
     */
    private String parseTokenToSubject(String token) {
        return JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes())).build()
                .verify(token.replace(Constants.TOKEN_PREFIX, "")).getSubject();
    }
}
