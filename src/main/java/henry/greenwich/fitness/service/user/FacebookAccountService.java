package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.FacebookAccount;
import org.springframework.stereotype.Service;

import henry.greenwich.fitness.repository.user.FacebookAccountRepository;

@Service
public class FacebookAccountService {
    private FacebookAccountRepository facebookAccountRepository;

    /**
     *
     * @param facebookAccountRepository - inject facebookAccountRepository
     */
    public FacebookAccountService(FacebookAccountRepository facebookAccountRepository) {
        this.facebookAccountRepository = facebookAccountRepository;
    }

    /**
     *
     * @param facebookId - facebook's id that user want to get
     * @return facebookAccount
     */
    public FacebookAccount findFacebookAccountByFacebookId(String facebookId) {
        return this.facebookAccountRepository.findFacebookAccountByFacebookId(facebookId);
    }

    /**
     *
     * @param facebookAccount - facebookAccount that user want to add to the
     *                        database
     * @return selected facebookAccount
     */
    public FacebookAccount addFacebookAccount(FacebookAccount facebookAccount) {
        return this.facebookAccountRepository.saveAndFlush(facebookAccount);
    }

}
