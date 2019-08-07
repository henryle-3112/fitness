package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.UserRole;
import henry.greenwich.fitness.repository.user.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    /**
     * userRoleRepository - interact with user's role's data
     */
    private UserRoleRepository userRoleRepository;

    /**
     *
     * @param userRoleRepository - inject userRoleRepository
     */
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    /**
     *
     * @param userProfileId - if of user's profile that user want to get roles
     * @return list of roles
     */
    public List<Object> findUserRoles(Integer userProfileId) {
        return this.userRoleRepository.findUserRoles(userProfileId);
    }

    public UserRole addUserRole(UserRole userRole) {
        return this.userRoleRepository.saveAndFlush(userRole);
    }
}
