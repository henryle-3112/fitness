package henry.greenwich.fitness.service.user;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.Role;
import henry.greenwich.fitness.repository.user.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    /**
     * @param roleRepository - inject roleRepository
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param name - role's name that user want to get
     * @return selected role
     */
    public Role getRoleByName(String name) {
        return this.roleRepository.findRoleByName(name);
    }
}
