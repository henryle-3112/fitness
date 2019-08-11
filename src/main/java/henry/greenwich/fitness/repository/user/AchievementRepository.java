package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.model.user.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    /**
     *
     * @param userId - user's id that user want to get achievements
     * @return list of achievements
     */
    @Query(nativeQuery = true, value = "select * from achievement where user_profile_id = :userId")
    List<Object> getAchievementsById(Long userId);

    /**
     *
     * @param userProfileId - user's profile's id
     * @param startIndex - start's index
     * @return list of achievements
     */
    @Query(nativeQuery = true, value = "select * from achievement where user_profile_id = :userProfileId limit :startIndex, 8")
    List<Object> findAchievementsByUserProfileIdAndByPage(int userProfileId, int startIndex);

    /**
     *
     * @param userProfileId - user's profile's id
     * @return number of achievements
     */
    @Query(nativeQuery = true, value = "select count(*) from achievement where user_profile_id = :userProfileId")
    List<Object> countAchievementsByUserProfileId(int userProfileId);
}
