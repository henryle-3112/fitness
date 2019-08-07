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

    /**
     * @param userAccountRepository - inject userAccountRepository
     * @param userRoleRepository    - inject userRoleRepository
     */
    public UserDetailsServiceImpl(UserAccountRepository userAccountRepository, UserRoleRepository userRoleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * @param username - user's name that will be used to load user's information
     * @return user details
     * @throws UsernameNotFoundException - throw exception if username not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // check Authentication by loading user's account by user's name;
        List<Object> userAccount = this.userAccountRepository.checkAuthentication(username);
        if (userAccount != null && !userAccount.isEmpty()) {
            return this.getUserDetails(userAccount);
        }
        return null;
    }

    /**
     * @param userAccount - user account that user want to convert to user details
     * @return converted user details
     */
    private User getUserDetails(List<Object> userAccount) {
        Object[] selectedUserAccount = (Object[]) userAccount.get(0);
        String selectedUserName = (String) selectedUserAccount[0];
        String selectedUserPassword = (String) selectedUserAccount[1];
        Integer selectedUserProfileId = (Integer) selectedUserAccount[2];
        List<Object> userRoles = this.userRoleRepository.findUserRoles(selectedUserProfileId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority((String) role)));
        return new User(selectedUserName, selectedUserPassword, authorities);
    }
}