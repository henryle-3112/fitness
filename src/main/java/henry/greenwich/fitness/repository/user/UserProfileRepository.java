package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.model.user.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    /**
     * @param id - user's profile's is that user want to get
     * @return selected userProfile
     */
    UserProfile findUserProfileById(Long id);

}
