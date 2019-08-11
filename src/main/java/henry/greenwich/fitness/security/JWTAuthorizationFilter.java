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

    public JWTAuthorizationFilter(AuthenticationManager authManager,
                                  UserDetailsServiceImpl userDetailsService,
                                  FacebookAccountService facebookAccountService,
                                  UserRoleService userRoleService,
                                  GoogleAccountService googleAccountService) {
        super(authManager);
        this.userDetailsService = userDetailsService;
        this.facebookAccountService = facebookAccountService;
        this.userRoleService = userRoleService;
        this.googleAccountService = googleAccountService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
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
            // parse the token.
            String subject = JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(Constants.TOKEN_PREFIX, ""))
                    .getSubject();

            if (subject != null) {
                UserDetails user = this.userDetailsService.loadUserByUsername(subject);
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(subject, null, user.getAuthorities());
                } else {
                    // try to get test if it is facebook's account
                    FacebookAccount facebookAccount = this.facebookAccountService.findFacebookAccountByFacebookId(subject);
                    if (facebookAccount != null) {
                        int selectedUserProfileId = Math.toIntExact(facebookAccount.getUserProfile().getId());
                        List<Object> userRoles = this.userRoleService.findUserRoles(selectedUserProfileId);
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority((String) role)));
                        return new UsernamePasswordAuthenticationToken(subject, null, authorities);
                    } else {
                        // try to get test if it is google's account
                        GoogleAccount googleAccount = this.googleAccountService.findGoogleAccountByGoogleId(subject);
                        if (googleAccount != null) {
                            int selectedUserProfileId = Math.toIntExact(googleAccount.getUserProfile().getId());
                            List<Object> userRoles = this.userRoleService.findUserRoles(selectedUserProfileId);
                            List<GrantedAuthority> authorities = new ArrayList<>();
                            userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority((String) role)));
                            return new UsernamePasswordAuthenticationToken(subject, null, authorities);
                        }
                        return null;
                    }
                }
            }
            return null;
        }
        return null;
    }
}
