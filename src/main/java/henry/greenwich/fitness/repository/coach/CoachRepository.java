package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    /**
     * @param id     - coach's id
     * @param status - coach's status
     * @return selected coach
     */
    Coach findCoachByIdAndStatus(Long id, int status);

    /**
     * @param status - coach's status
     * @return list of coaches
     */
    List<Coach> findCoachesByStatus(int status);

    /**
     * @param keyword    - keyword
     * @param startIndex - start index
     * @return list of coaches
     */
    @Query(nativeQuery = true, value = "select coach.id, coach.user_profile_id, coach.about, coach.status from coach inner join user_profile on coach.user_profile_id = user_profile.id where coach.status = :status and lower(user_profile.full_name) like :keyword limit :startIndex, 8")
    List<Object> findCoachesByPage(String keyword, int startIndex, int status);

    /**
     * @param keyword - keyword
     * @return number of coaches
     */
    @Query(nativeQuery = true, value = "select count(*) from coach inner join user_profile on coach.user_profile_id = user_profile.id where coach.status = :status and lower(user_profile.full_name) like :keyword")
    List<Object> countCoaches(String keyword, int status);

    /**
     * @param id - coach's id
     * @return selected coach
     */
    Coach findCoachById(Long id);

    /**
     * @param userProfile - user's profile
     * @return selected coach
     */
    Coach findCoachByUserProfileAndStatus(UserProfile userProfile, int status);
}
