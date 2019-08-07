package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoachFeedbackRepository extends JpaRepository<CoachFeedback, Long> {

    String GET_COACH_FEEDBACKS = "select * from " + Constants.COACH_FEEDBACK_TABLE + ""
            + " where (:coachFeedbackStatus is null or " + Constants.COACH_FEEDBACK_TABLE + "."
            + Constants.COACH_FEEDBACK_STATUS + " = :coachFeedbackStatus)";

    /**
     * @param coachFeedbackStatus - coach's feedback's status that user want to get
     *                            (this parameter could be optional)
     * @return list of coach's feedbacks
     */
    @Query(nativeQuery = true, value = GET_COACH_FEEDBACKS)
    List<Object> getCoachFeedbacks(@Param("coachFeedbackStatus") Integer coachFeedbackStatus);

    /**
     * @param id - coach's feedback's id that user want to get
     * @return selected coach's feedback
     */
    CoachFeedback findCoachFeedbackById(Long id);
}
