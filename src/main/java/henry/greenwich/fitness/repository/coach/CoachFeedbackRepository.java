package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachFeedbackRepository extends JpaRepository<CoachFeedback, Long> {

    /**
     * @param coach  - coach
     * @param status - status
     * @return list of coach's feedbacks
     */
    List<CoachFeedback> findCoachFeedbacksByCoachAndCoachFeedbackStatus(Coach coach, int status);

}
