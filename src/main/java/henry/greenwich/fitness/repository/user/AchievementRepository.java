package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    String GET_ACHIEVEMENTS_PAGING = "select * from " + Constants.ACHIEVEMENT_TABLE + "" +
            " where (:userProfileId is null or " + Constants.ACHIEVEMENT_TABLE + "." + Constants.ACHIEVEMENT_USER_PROFILE_ID + " = :userProfileId)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_ACHIEVEMENTS = "select * from " + Constants.ACHIEVEMENT_TABLE + "" +
            " where (:userProfileId is null or " + Constants.ACHIEVEMENT_TABLE + "." + Constants.ACHIEVEMENT_USER_PROFILE_ID + " = :userProfileId)";

    String GET_NUMBER_OF_ACHIEVEMENTS = "select count(*) from " + Constants.ACHIEVEMENT_TABLE + "" +
            " where (:userProfileId is null or " + Constants.ACHIEVEMENT_TABLE + "." + Constants.ACHIEVEMENT_USER_PROFILE_ID + " = :userProfileId)";

    /**
     * @param userProfileId - user's profile's id that user want to get achievements (this parameter could be optional)
     * @param startIndex    - start's index (for pagination) (this parameter could be optional)
     * @return list of achievements
     */
    @Query(nativeQuery = true, value = GET_ACHIEVEMENTS_PAGING)
    List<Object> getAchievementsPaging(Integer userProfileId, Integer startIndex);

    /**
     * @param userProfileId - user's profile's id that user want to get achievements (this parameter could be optional)
     * @return list of achievements
     */
    @Query(nativeQuery = true, value = GET_ACHIEVEMENTS)
    List<Object> getAchievements(Integer userProfileId);

    /**
     * @param userProfileId - user's profile's id that user want to get number of achievements (this parameter could be optional)
     * @return number of achievements
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_ACHIEVEMENTS)
    List<Object> getNumberOfAchievements(Integer userProfileId);
}
