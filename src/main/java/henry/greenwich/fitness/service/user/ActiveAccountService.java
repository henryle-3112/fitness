package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.model.user.UserAccountStatus;
import org.springframework.stereotype.Service;

@Service
public class ActiveAccountService {
    private UserAccountService userAccountService;
    private UserAccountStatusService userAccountStatusService;

    /**
     * @param userAccountService       - inject userAccountService
     * @param userAccountStatusService - inject userAccountStatusService
     */
    public ActiveAccountService(UserAccountService userAccountService,
                                UserAccountStatusService userAccountStatusService) {
        this.userAccountService = userAccountService;
        this.userAccountStatusService = userAccountStatusService;
    }

    /**
     * @param userAccount - userAccount that user want to active
     * @return selected user's account was activated or not
     */
    public boolean activeUserAccount(UserAccount userAccount) {
        String emailConfirmationToken = userAccount.getEmailConfirmationToken();
        UserAccount selectedUserAccount = this.userAccountService
                .findUserAccountByEmailConfirmationToken(emailConfirmationToken);
        if (selectedUserAccount != null) {
            UserAccountStatus confirmedUserAccountStatus = this.userAccountStatusService
                    .getUserAccountStatusByName("EMAIL_CONFIRMED");
            selectedUserAccount.setUserAccountStatus(confirmedUserAccountStatus);
            this.userAccountService.updateUserAccount(selectedUserAccount);
            return true;
        }
        return false;
    }
}
