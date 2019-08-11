package henry.greenwich.fitness.service.user;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.UserAccountStatus;
import henry.greenwich.fitness.repository.user.UserAccountStatusRepository;

import java.util.List;

@Service
public class UserAccountStatusService {
	/**
	 * userAccountStatusRepository - interact with user's account's status's id
	 */
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
     * @return list of user's account's status
     */
    public List<UserAccountStatus> getUserAccountStatusList() {
        return this.userAccountStatusRepository.findAll();
    }
    
    /**
     * 
     * @param id - id of user's account's status
     * @return selected user's account's status
     */
    public UserAccountStatus getUserAccountStatus(Long id) {
        return this.userAccountStatusRepository.findUserAccountStatusById(id);
    }
    
    /**
     * 
     * @param userAccountStatus - that user want to add
     * @return userAccountStatus
     */
    public UserAccountStatus addUserAccountStatus(UserAccountStatus userAccountStatus) {
        return this.userAccountStatusRepository.saveAndFlush(userAccountStatus);
    }
    
    /**
     * 
     * @param userAccountStatus - that user want to update
     * @return userAccountStatus
     */
    public UserAccountStatus updateUserAccountStatus(UserAccountStatus userAccountStatus) {
        return this.userAccountStatusRepository.saveAndFlush(userAccountStatus);
    }
    
    /**
     * 
     * @param id - id of user's account that user want to delete
     */
    public void deleteUserAccountStatus(Long id) {
        this.userAccountStatusRepository.deleteById(id);
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
