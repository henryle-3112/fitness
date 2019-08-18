package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnCoachFeedbackRepository extends JpaRepository<ReplyOnCoachFeedback, Long> {

    String GET_REPLIES_ON_COACH_FEEDBACK = "select * from " + Constants.REPLY_ON_COACH_FEEDBACK_TABLE + ""
            + " where (:coachFeedbackId is null or " + Constants.REPLY_ON_COACH_FEEDBACK_TABLE + "."
            + Constants.REPLY_ON_COACH_FEEDBACK_COACH_FEED_BACK_ID + " = :coachFeedbackId)"
            + " and (:replyOnCoachFeedbackStatus is null or " + Constants.REPLY_ON_COACH_FEEDBACK_TABLE
            + "." + Constants.REPLY_ON_COACH_FEEDBACK_STATUS + " = :replyOnCoachFeedbackStatus)";

    /**
     * @param coachFeedbackId            - coach's feedback's id that user want to
     *                                   get replies
     * @param replyOnCoachFeedbackStatus - status of reply on coach's feedback that
     *                                   user want to get (this parameter could be
     *                                   optional)
     * @return list of replies
     */
    @Query(nativeQuery = true, value = GET_REPLIES_ON_COACH_FEEDBACK)
    List<Object> getRepliesOnCoachFeedback(Integer coachFeedbackId, Integer replyOnCoachFeedbackStatus);

    /**
     * @param id - reply on coach's feedback's id that user want to get
     * @return selected reply on coach's feedback
     */
    ReplyOnCoachFeedback findReplyOnCoachFeedbackById(Long id);
}
