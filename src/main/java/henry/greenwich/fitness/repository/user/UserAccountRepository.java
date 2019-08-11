package henry.greenwich.fitness.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.model.user.UserProfileKey;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    /**
     *
     * @param userProfileId - userProfileId of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountByUserProfileId(UserProfileKey userProfileId);

    /**
     *
     * @param userName - userName of account that would be authenticated
     * @return list of object (including user's name, password, user's profile's id)
     */
    @Query(nativeQuery = true, value = "select user_account.user_name, user_account.password, user_account.user_profile_id from user_account inner join user_profile where user_account.user_name = :userName and user_account.user_account_status_id = 1 and user_profile.status = 1")
    List<Object> checkAuthentication(String userName);

    /**
     *
     * @param userName - user's name of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountByUserName(String userName);

    /**
     *
     * @param token - passwordReminderToken of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountByPasswordReminderToken(String token);

    /**
     *
     * @param token - emailConfirmationToken of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountsByEmailConfirmationToken(String token);
}
