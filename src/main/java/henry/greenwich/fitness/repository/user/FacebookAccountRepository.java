package henry.greenwich.fitness.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.user.FacebookAccount;

import java.util.List;

@Repository
public interface FacebookAccountRepository extends JpaRepository<FacebookAccount, Long> {
    /**
     *
     * @param facebookId - facebook's id of account that user want to get
     * @return selected facebookAccount
     */
    FacebookAccount findFacebookAccountByFacebookId(String facebookId);
}
