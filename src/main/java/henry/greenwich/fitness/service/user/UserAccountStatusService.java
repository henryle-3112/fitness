package henry.greenwich.fitness.service.user;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.UserAccountStatus;
import henry.greenwich.fitness.repository.user.UserAccountStatusRepository;

@Service
public class UserAccountStatusService {
    private UserAccountStatusRepository userAccountStatusRepository;

    /**
     * 
     * @param userAccountStatusRepository - inject user's account's repository
     */
    public UserAccountStatusService(UserAccountStatusRepository userAccountStatusRepository) {
        this.userAccountStatusRepository = userAccountStatusRepository;
    }

    /**
     * 
     * @param name - name of user's account's status that user want to get
     * @return userAccountStatus
     */
    public UserAccountStatus getUserAccountStatusByName(String name) {
        return this.userAccountStatusRepository.findUserAccountStatusByName(name);
    }
}
