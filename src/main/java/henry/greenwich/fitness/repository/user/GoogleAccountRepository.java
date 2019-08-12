package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.model.user.GoogleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, Long> {
    /**
     * @param googleId - google's id that user want to get account
     * @return selected googleAccount
     */
    GoogleAccount findGoogleAccountByGoogleId(String googleId);
}
