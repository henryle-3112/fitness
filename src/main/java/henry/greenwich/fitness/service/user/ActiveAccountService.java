package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.BodyIndex;
import henry.greenwich.fitness.model.user.UserAccount;
import henry.greenwich.fitness.model.user.UserAccountStatus;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ActiveAccountService {
    private UserAccountService userAccountService;
    private UserAccountStatusService userAccountStatusService;
    private BodyIndexService bodyIndexService;

    /**
     * @param userAccountService       - inject userAccountService
     * @param userAccountStatusService - inject userAccountStatusService
     * @param bodyIndexService - inject bodyIndexService
     */
    public ActiveAccountService(UserAccountService userAccountService,
                                UserAccountStatusService userAccountStatusService,
                                BodyIndexService bodyIndexService) {
        this.userAccountService = userAccountService;
        this.userAccountStatusService = userAccountStatusService;
        this.bodyIndexService = bodyIndexService;
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
            // init body index for user first time when user register new account
            this.addBodyIndex(selectedUserAccount.getUserProfile());
            return true;
        }
        return false;
    }

    /**
     *
     * @param selectedUserProfile - selected user's profile that will be added body index
     */
    private void addBodyIndex(UserProfile selectedUserProfile) {
        BodyIndex bodyIndex = new BodyIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        String strCurrentDate = sdf.format(currentDate);
        bodyIndex.setCurrentDate(strCurrentDate);
        bodyIndex.setWeight(70f);
        bodyIndex.setHeight(170f);
        bodyIndex.setUserProfile(selectedUserProfile);
        this.bodyIndexService.addBodyIndex(bodyIndex);
    }
}
