package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.CoachRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRateRepository extends JpaRepository<CoachRate, Long> {

    String GET_COACH_RATE = "select * from " + Constants.COACH_RATE_TABLE + ""
            + " where (:userProfileId is null or " + Constants.COACH_RATE_USER_PROFILE_ID
            + " = :userProfileId)" + " and (:coachId is null or " + Constants.COACH_RATE_COACH_ID
            + " = :coachId)";

    /**
     * @param userProfileId - user's profile' id that user want to get coach's rate
     * @param coachId       - coach's id that user want to get coach's rate
     * @return selected coach's rate
     */
    @Query(nativeQuery = true, value = GET_COACH_RATE)
    List<Object> getCoachRate(Long userProfileId, Long coachId);
}
