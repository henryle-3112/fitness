package henry.greenwich.fitness.repository.training;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.training.Training;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * @param startIndex    - start's index
     * @param userProfileId - user's profile's id
     * @param coachId       - coach's id
     * @return list of trainings
     */
    @Query(nativeQuery = true, value = "select training.training_date from training where coach_id = :coachId and user_profile_id = :userProfileId group by training.training_date limit :startIndex, 8")
    List<Object> findTrainingsByUserProfileIdAndCoachIdAndPage(int coachId, int userProfileId, int startIndex);

    /**
     * @param userProfileId - user's profile's id
     * @param coachId       - coach's id
     * @return number of trainings
     */
    @Query(nativeQuery = true, value = "select count(*) from training where coach_id = :coachId and user_profile_id = :userProfileId group by training.training_date")
    List<Object> countTrainingsByUserProfileIdAndCoachId(int coachId, int userProfileId);

    /**
     *
     * @param trainingDate - training's date
     * @return list of trainings
     */
    List<Training> findTrainingsByTrainingDateAndUserProfileAndCoach(String trainingDate, UserProfile userProfile, Coach coach);

    /**
     *
     * @param trainingDate - training's date
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from training where training_date = :trainingDate")
    void deleteAllTrainingsByTrainingDate(String trainingDate);

}
