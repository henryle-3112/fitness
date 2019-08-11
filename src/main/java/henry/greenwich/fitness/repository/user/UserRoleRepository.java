package henry.greenwich.fitness.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import henry.greenwich.fitness.model.user.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    /**
     *
     * @param id - user's profile's id
     * @return roles of selected user
     */
    @Query(nativeQuery = true, value = "select role.name from user_role inner join role on user_role.role_id = role.id where user_role.user_profile_id = :id")
    List<Object> findUserRoles(Integer id);
}
