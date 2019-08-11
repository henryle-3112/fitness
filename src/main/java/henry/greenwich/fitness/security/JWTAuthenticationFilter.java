package henry.greenwich.fitness.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.UserAccount;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserAccount creds = new ObjectMapper().readValue(request.getInputStream(), UserAccount.class);

            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserName(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User userDetails = (User) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(Constants.SECRET.getBytes()));
        StringBuilder responseData = new StringBuilder("{\"userName\": \"" + userDetails.getUsername() + "\", \"token\": \"" + Constants.TOKEN_PREFIX + token + "\", \"roles\": [");
        // get user's authorities then add to string to convert to json format
        ArrayList<GrantedAuthority> listOfAuthorities = new ArrayList<>(userDetails.getAuthorities());
        for (int i = 0; i < listOfAuthorities.size(); i++) {
            if (i != listOfAuthorities.size() - 1) {
                responseData.append("\"").append(listOfAuthorities.get(i).getAuthority()).append("\"").append(", ");
            } else {
                responseData.append("\"").append(listOfAuthorities.get(i).getAuthority()).append("\"");
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
    }
}
