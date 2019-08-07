package henry.greenwich.fitness.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.user.FacebookAccount;

@Repository
public interface FacebookAccountRepository extends JpaRepository<FacebookAccount, Long> {
    /**
     *
     * @param facebookId - facebook's id of account that user want to get
     * @return selected facebookAccount
     */
    FacebookAccount findFacebookAccountByFacebookId(String facebookId);
}
