package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.CoachRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRateRepository extends JpaRepository<CoachRate, Long> {

    /**
     * @param coachId - coach's id
     * @return find rating average
     */
    @Query(nativeQuery = true, value = "select avg(rate) as rate_average from coach_rate where coach_id = :coachId")
    List<Object> findRateAverage(int coachId);

    /**
     * @param userId  - user' id
     * @param coachId - coach's id
     * @return selected coach's rate
     */
    @Query(nativeQuery = true, value = "select * from coach_rate where user_profile_id = :userId and coach_id = :coachId")
    List<Object> getCoachRateByUserIdAndCoachId(Long userId, Long coachId);
}
