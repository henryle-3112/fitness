package henry.greenwich.fitness.repository.user;


import henry.greenwich.fitness.model.user.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     *
     * @param id - role's id that user want to get
     * @return selected role
     */
    Role findRoleById(Long id);

    /**
     *
     * @param name - role's name that user want to get
     * @return selected role
     */
    Role findRoleByName(String name);
}
