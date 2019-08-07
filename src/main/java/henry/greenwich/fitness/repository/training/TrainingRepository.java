package henry.greenwich.fitness.repository.training;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.training.Training;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    String GET_TRAININGS_PAGING = "select " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE + " from " + Constants.TRAINING_TABLE + "" +
            " where (:coachId is null or " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_USER_PROFILE_ID + " = :userProfileId)" +
            " group by " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE + "" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_TRAININGS = "select " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE + " from " + Constants.TRAINING_TABLE + "" +
            " where (:coachId is null or " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_USER_PROFILE_ID + " = :userProfileId)" +
            " group by " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE;

    String GET_NUMBER_OF_TRAININGS = "select " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE + " from " + Constants.TRAINING_TABLE + "" +
            " where (:coachId is null or " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_USER_PROFILE_ID + " = :userProfileId)" +
            " group by " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE;

    String DELETE_ALL_BY_TRAINING_DATE = "delete from " + Constants.TRAINING_TABLE + "" +
            " where " + Constants.TRAINING_TABLE + "." + Constants.TRAINING_DATE + " = :trainingDate";

    /**
     * @param coachId       - coach's id that user want to get list of trainings (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get list of trainings (this parameter could be optional)
     * @param startIndex    - start index (for pagination) (this parameter could be optional)
     * @return list of trainings
     */
    @Query(nativeQuery = true, value = GET_TRAININGS_PAGING)
    List<Object> getTrainingsPaging(Integer coachId, Integer userProfileId, Integer startIndex);

    /**
     * @param coachId       - coach's id that user want to get list of trainings (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get list of trainings (this parameter could be optional)
     * @return list of trainings
     */
    @Query(nativeQuery = true, value = GET_TRAININGS)
    List<Object> getTrainings(Integer coachId, Integer userProfileId);

    /**
     * @param coachId       - coach's id that user want to get number of trainings (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get number of trainings (this parameter could be optional)
     * @return number of trainings
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_TRAININGS)
    List<Object> getNumberOfTrainings(Integer coachId, Integer userProfileId);


    /**
     * @param trainingDate - training's date that user want to get list of trainings
     * @param userProfile  - user's profile that user want to get list of trainings
     * @param coach        - coach that user want to get list of trainings
     * @return list of trainings
     */
    List<Training> findTrainingsByTrainingDateAndUserProfileAndCoach(String trainingDate, UserProfile userProfile, Coach coach);

    /**
     * @param trainingDate - training's date that user want to delete all trainings
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = DELETE_ALL_BY_TRAINING_DATE)
    void deleteAllTrainingsByTrainingDate(String trainingDate);

}
