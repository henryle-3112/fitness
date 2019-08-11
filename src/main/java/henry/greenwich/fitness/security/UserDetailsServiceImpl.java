package henry.greenwich.fitness.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import henry.greenwich.fitness.repository.user.UserAccountRepository;
import henry.greenwich.fitness.repository.user.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserAccountRepository userAccountRepository;
    private UserRoleRepository userRoleRepository;

    public UserDetailsServiceImpl(UserAccountRepository userAccountRepository, UserRoleRepository userRoleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // check Authentication by loading user's account by user's name;
        List<Object> userAccount = this.userAccountRepository.checkAuthentication(username);
        if (userAccount != null && !userAccount.isEmpty()) {
            // convert selected result to object
            Object[] selectedUserAccount = (Object[]) userAccount.get(0);
            // get user's information (including user's name, user's password, user's profile id)
            String selectedUserName = (String) selectedUserAccount[0];
            String selectedUserPassword = (String) selectedUserAccount[1];
            Integer selectedUserProfileId = (Integer) selectedUserAccount[2];
            // get user's roles
            List<Object> userRoles = this.userRoleRepository.findUserRoles(selectedUserProfileId);
            List<GrantedAuthority> authorities = new ArrayList<>();
            userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority((String) role)));
            return new User(selectedUserName, selectedUserPassword, authorities);
        }
        return null;
    }
}