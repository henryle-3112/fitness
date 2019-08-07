package henry.greenwich.fitness.service.user;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.user.UserProfileRepository;

@Service
public class UserProfileService {
    private UserProfileRepository userProfileRepository;

    /**
     *
     * @param userProfileRepository - inject userProfileRepository
     */
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    /**
     *
     * @param id - user's profile's id that user want to get
     * @return selected user's profile
     */
    public UserProfile getUserProfile(Long id) {
        return this.userProfileRepository.findUserProfileById(id);
    }

    /**
     *
     * @param userProfile - that user want to add to the database
     * @return userProfile that was inserted to the database
     */
    public UserProfile addUserProfile(UserProfile userProfile) {
        return this.userProfileRepository.saveAndFlush(userProfile);
    }

    /**
     *
     * @param userProfile - that user want to update to the database
     * @return userProfile that was updated to the database
     */
    public UserProfile updateUserProfile(UserProfile userProfile) {
        return this.userProfileRepository.saveAndFlush(userProfile);
    }

}
