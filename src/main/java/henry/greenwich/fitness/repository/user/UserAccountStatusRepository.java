package henry.greenwich.fitness.repository.user;


import henry.greenwich.fitness.model.user.UserAccountStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountStatusRepository extends JpaRepository<UserAccountStatus, Long> {
    /**
     * @param name - name of user's account's status
     * @return userAccountStatus - contained name
     */
    UserAccountStatus findUserAccountStatusByName(String name);
}
