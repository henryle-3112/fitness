package henry.greenwich.fitness.security;

import henry.greenwich.fitness.service.user.FacebookAccountService;
import henry.greenwich.fitness.service.user.GoogleAccountService;
import henry.greenwich.fitness.service.user.UserRoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import henry.greenwich.fitness.constants.Constants;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private FacebookAccountService facebookAccountService;
    private UserRoleService userRoleService;
    private GoogleAccountService googleAccountService;

    /**
     * @param userDetailsService     - inject userDetailsService
     * @param bCryptPasswordEncoder  - inject bCryptPasswordEncoder
     * @param facebookAccountService - inject facebookAccountService
     * @param userRoleService        - inject userRoleService
     * @param googleAccountService   - inject googleAccountService
     */
    public WebSecurity(UserDetailsServiceImpl userDetailsService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       FacebookAccountService facebookAccountService,
                       UserRoleService userRoleService,
                       GoogleAccountService googleAccountService) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.facebookAccountService = facebookAccountService;
        this.userRoleService = userRoleService;
        this.googleAccountService = googleAccountService;
    }

    /**
     * @param http - http
     * @throws Exception - throw Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, Constants.LOGIN_URL)
                .permitAll().antMatchers(HttpMethod.POST).permitAll().antMatchers(HttpMethod.PUT).permitAll()
                .antMatchers(HttpMethod.GET, "/resources/**").permitAll().anyRequest().authenticated()
                .antMatchers("/users/**").hasRole("ADMIN").and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), this.userDetailsService,
                        facebookAccountService, userRoleService, googleAccountService))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * @param auth -auth
     * @throws Exception - throw Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * @return CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setExposedHeaders(Arrays.asList(Constants.HEADER_X_TOTAL_COUNT, Constants.HEADER_X_TOTAL_PAGE, Constants.HEADER_X_TOTAL_PAYMENT));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * @return HttpFirewall
     */
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}