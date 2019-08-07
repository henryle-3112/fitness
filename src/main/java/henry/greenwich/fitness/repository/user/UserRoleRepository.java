package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.constants.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import henry.greenwich.fitness.model.user.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    String FIND_USER_ROLES = "select " + Constants.ROLE_TABLE + "." + Constants.ROLE_NAME + " from " + Constants.USER_ROLE_TABLE + "" +
            " inner join " + Constants.ROLE_TABLE + " on " + Constants.USER_ROLE_TABLE + "." + Constants.USER_ROLE_ROLE_ID + " = " + Constants.ROLE_TABLE + "." + Constants.ROLE_ID + "" +
            " where " + Constants.USER_ROLE_TABLE + "." + Constants.USER_ROLE_USER_PROFILE_ID + " = :id";

    /**
     * @param id - user's profile's id
     * @return roles of selected user
     */
    @Query(nativeQuery = true, value = FIND_USER_ROLES)
    List<Object> findUserRoles(Integer id);
}
