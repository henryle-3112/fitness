package henry.greenwich.fitness.service.user;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.repository.user.UserAccountRepository;

@Service
public class UserAccountService {
    private UserAccountRepository userAccountRepository;

    /**
     * @param userAccountRepository - inject userAccountRepository
     */
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * @param userAccount - userAccount that users want to add
     * @return userAccount - that was inserted to the database
     */
    public UserAccount addUserAccount(UserAccount userAccount) {
        return this.userAccountRepository.saveAndFlush(userAccount);
    }

    /**
     * @param userAccount - userAccount that users want to update
     * @return userAccount - that was updated to the database
     */
    public UserAccount updateUserAccount(UserAccount userAccount) {
        return this.userAccountRepository.saveAndFlush(userAccount);
    }

    /**
     * @param email - email of user's account that users want to find
     * @return userAccount
     */
    public UserAccount findUserAccountByUsername(String email) {
        return this.userAccountRepository.findUserAccountByUserName(email);
    }

    /**
     * @param token - password reminder token of user's account that users want to
     *              find
     * @return userAccount
     */
    public UserAccount findUserAccountByPasswordReminderToken(String token) {
        return this.userAccountRepository.findUserAccountByPasswordReminderToken(token);
    }

    /**
     * @param token - emailConfirmationToken of account that user want to get
     * @return selected userAccount
     */
    public UserAccount findUserAccountByEmailConfirmationToken(String token) {
        return this.userAccountRepository.findUserAccountsByEmailConfirmationToken(token);
    }

    /**
     * @param userName - user's name that user want to get user's account
     * @return selected user's account
     */
    public UserAccount getUserAccountByUsername(String userName) {
        return this.userAccountRepository.findUserAccountByUserName(userName);
    }
}
