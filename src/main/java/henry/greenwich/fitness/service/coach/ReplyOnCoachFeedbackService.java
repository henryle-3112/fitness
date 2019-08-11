package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.repository.coach.ReplyOnCoachFeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnCoachFeedbackService {
    /**
     * replyOnCoachFeedbackRepository - interact with reply on coach feedback data
     */
    private ReplyOnCoachFeedbackRepository replyOnCoachFeedbackRepository;

    /**
     * @param replyOnCoachFeedbackRepository - inject replyOnCoachFeedbackRepository
     */
    public ReplyOnCoachFeedbackService(ReplyOnCoachFeedbackRepository replyOnCoachFeedbackRepository) {
        this.replyOnCoachFeedbackRepository = replyOnCoachFeedbackRepository;
    }

    /**
     * @param coachFeedback - coach's feedback
     * @param status        - status
     * @return number of replies
     */
    public int countReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(CoachFeedback coachFeedback, int status) {
        return this.replyOnCoachFeedbackRepository.countReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(coachFeedback, status);
    }

    /**
     * @param coachFeedback - coach's feedback
     * @param status        - status
     * @return list of replies
     */
    public List<ReplyOnCoachFeedback> getReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(
            CoachFeedback coachFeedback, int status) {
        return this.replyOnCoachFeedbackRepository.findReplyOnCoachFeedbacksByCoachFeedbackAndReplyOnCoachFeedbackStatus(coachFeedback, status);
    }

    /**
     * @param replyOnCoachFeedback - reply on coach feedback
     * @return inserted replyOnCoachFeedback
     */
    public ReplyOnCoachFeedback addReplyOnCoachFeedback(ReplyOnCoachFeedback replyOnCoachFeedback) {
        return this.replyOnCoachFeedbackRepository.saveAndFlush(replyOnCoachFeedback);
    }
}
