package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.repository.coach.CoachFeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachFeedbackService {
    /**
     * coachFeedbackRepository - interact with coach's feeddacks
     */
    private CoachFeedbackRepository coachFeedbackRepository;

    /**
     * @param coachFeedbackRepository - inject coachFeedbackRepository
     */
    public CoachFeedbackService(CoachFeedbackRepository coachFeedbackRepository) {
        this.coachFeedbackRepository = coachFeedbackRepository;
    }

    /**
     * @param coach  - coach
     * @param status - status
     * @return list of coach feedbacks
     */
    public List<CoachFeedback> getCoachFeedbacksByCoachAndCoachFeedbackStatus(Coach coach, int status) {
        return this.coachFeedbackRepository.findCoachFeedbacksByCoachAndCoachFeedbackStatus(coach, status);
    }

    /**
     * @param coachFeedback - coach's feedback
     * @return list of coach's feedbacks
     */
    public CoachFeedback addCoachFeedback(CoachFeedback coachFeedback) {
        return this.coachFeedbackRepository.saveAndFlush(coachFeedback);
    }

    /**
     * @param id - coach's feedback's id
     */
    public void deleteCoachFeedback(Long id) {
        this.coachFeedbackRepository.deleteById(id);
    }


}
