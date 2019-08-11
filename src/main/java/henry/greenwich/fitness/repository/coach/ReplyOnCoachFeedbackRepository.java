package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnCoachFeedbackRepository extends JpaRepository<ReplyOnCoachFeedback, Long> {

    /**
     * @param coachFeedback - coach's feedback
     * @param status        - status
     * @return list of coach feedbacks
     */
    int countReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(CoachFeedback coachFeedback, int status);

    /**
     * @param coachFeedback - coach's feedback
     * @param status        - status
     * @return list of reply on coach feedbacks
     */
    List<ReplyOnCoachFeedback> findReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(
            CoachFeedback coachFeedback,
            int status);
}
