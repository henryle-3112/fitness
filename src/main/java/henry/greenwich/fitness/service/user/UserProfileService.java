package henry.greenwich.fitness.service.user;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.user.UserProfileRepository;

import java.util.List;

@Service
public class UserProfileService {
    /**
     * userProfileRepository - interact with user's profile
     */
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
     * @return list of user's profiles
     */
    public List<UserProfile> getUserProfiles() {
        return this.userProfileRepository.findAll();
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

    /**
     *
     * @param id - user's profile's id that user want to delete
     */
    public void deleteUserProfile(Long id) {
        this.userProfileRepository.deleteById(id);
    }

}
