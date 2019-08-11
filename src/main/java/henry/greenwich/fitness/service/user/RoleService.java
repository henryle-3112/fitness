package henry.greenwich.fitness.service.user;


import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.Role;
import henry.greenwich.fitness.repository.user.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    /**
     * roleRepository - interact with role's data
     */
    private RoleRepository roleRepository;

    /**
     *
     * @param roleRepository - inject roleRepository
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     *
     * @return list of roles
     */
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    /**
     *
     * @param id - role's id that user want to get
     * @return selected role
     */
    public Role getRole(Long id) {
        return this.roleRepository.findRoleById(id);
    }

    /**
     *
     * @param role - that user want to add to the database
     * @return role that was inserted to the database
     */
    public Role addRole(Role role) {
        return this.roleRepository.saveAndFlush(role);
    }

    /**
     *
     * @param role - that user want to update to the database
     * @return role that was updated to the database
     */
    public Role updateRole(Role role) {
        return this.roleRepository.saveAndFlush(role);
    }

    /**
     *
     * @param id - role's id that user want to delete
     */
    public void deleteRole(Long id) {
        this.roleRepository.deleteById(id);
    }

    /**
     *
     * @param name - role's name that user want to get
     * @return selected role
     */
    public Role getRoleByName(String name) {
        return this.roleRepository.findRoleByName(name);
    }
}
