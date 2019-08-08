package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.constants.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.user.UserAccount;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    String CHECK_AUTHENTICATION = "select " + Constants.USER_ACCOUNT_TABLE + "." + Constants.USER_ACCOUNT_USER_NAME
            + "," + " " + Constants.USER_ACCOUNT_TABLE + "." + Constants.USER_ACCOUNT_PASSWORD + "," + " "
            + Constants.USER_ACCOUNT_TABLE + "." + Constants.USER_ACCOUNT_USER_PROFILE_ID + " from "
            + Constants.USER_ACCOUNT_TABLE + "" + " inner join " + Constants.USER_PROFILE_TABLE + " on "
            + Constants.USER_ACCOUNT_TABLE + "." + Constants.USER_ACCOUNT_USER_PROFILE_ID + " = "
            + Constants.USER_PROFILE_TABLE + "." + Constants.USER_PROFILE_ID + "" + " where "
            + Constants.USER_ACCOUNT_TABLE + "." + Constants.USER_ACCOUNT_USER_NAME + " = :userName" + " and "
            + Constants.USER_ACCOUNT_TABLE + "." + Constants.USER_ACCOUNT_USER_ACCOUNT_STATUS_ID + " = 1" + " and "
            + Constants.USER_PROFILE_TABLE + "." + Constants.USER_PROFILE_STATUS + " = 1";

    /**
     * @param userName - userName of account that would be authenticated
     * @return list of object (including user's name, password, user's profile's id)
     */
    @Query(nativeQuery = true, value = CHECK_AUTHENTICATION)
    List<Object> checkAuthentication(String userName);

    /**
     * @param userName - user's name of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountByUserName(String userName);

    /**
     * @param token - passwordReminderToken of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountByPasswordReminderToken(String token);

    /**
     * @param token - emailConfirmationToken of account that user want to get
     * @return selected userAccount
     */
    UserAccount findUserAccountsByEmailConfirmationToken(String token);
}
