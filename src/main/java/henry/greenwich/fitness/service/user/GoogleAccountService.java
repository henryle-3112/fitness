package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.GoogleAccount;
import henry.greenwich.fitness.repository.user.GoogleAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class GoogleAccountService {
    private GoogleAccountRepository googleAccountRepository;

    /**
     *
     * @param googleAccountRepository - inject googleAccountRepository
     */
    public GoogleAccountService(GoogleAccountRepository googleAccountRepository) {
        this.googleAccountRepository = googleAccountRepository;
    }

    /**
     *
     * @param googleId - google's id that user want to get
     * @return googleAccount
     */
    public GoogleAccount findGoogleAccountByGoogleId(String googleId) {
        return this.googleAccountRepository.findGoogleAccountByGoogleId(googleId);
    }

    /**
     *
     * @param googleAccount - google's account that user want to add to the database
     * @return selected googleAccount
     */
    public GoogleAccount addGoogleAccount(GoogleAccount googleAccount) {
        return this.googleAccountRepository.saveAndFlush(googleAccount);
    }

}
