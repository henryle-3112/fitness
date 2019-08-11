package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {
    /**
     * coachRepository - interact with coach's data
     */
    private CoachRepository coachRepository;

    /**
     * @param coachRepository - inject coachRepository
     */
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    /**
     * @param status - coach's status
     * @return list of coaches
     */
    public List<Coach> getCoaches(int status) {
        return this.coachRepository.findCoachesByStatus(status);
    }

    /**
     * @param id     - coach's id
     * @param status - coach's status
     * @return selected coach
     */
    public Coach getCoach(Long id, int status) {
        return this.coachRepository.findCoachByIdAndStatus(id, status);
    }

    /**
     * @param coach - coach
     * @return inserted coach
     */
    public Coach addCoach(Coach coach) {
        return this.coachRepository.saveAndFlush(coach);
    }

    /**
     * @param coach - coach
     * @return updated coach
     */
    public Coach updateCoach(Coach coach) {
        return this.coachRepository.saveAndFlush(coach);
    }

    /**
     * @param id - coach's id
     */
    public void deleteCoach(Long id) {
        this.coachRepository.deleteById(id);
    }

    /**
     * @param selectedKeyWord - selected keywords
     * @param startIndex      - start's index
     * @return list of coaches
     */
    public List<Object> findCoachesByPage(String selectedKeyWord, int startIndex, int status) {
        return this.coachRepository.findCoachesByPage(selectedKeyWord, startIndex, status);
    }

    /**
     * @param selectedKeyWord - selected keywords
     * @return number of coaches
     */
    public List<Object> countCoaches(String selectedKeyWord, int status) {
        return this.coachRepository.countCoaches(selectedKeyWord, status);
    }

    /**
     * @param id - coach's id
     * @return selected coach
     */
    public Coach getCoachById(Long id) {
        return this.coachRepository.findCoachById(id);
    }

    /**
     * @param userProfile - user's profile
     * @return selected coach
     */
    public Coach getCoachByUserProfile(UserProfile userProfile, int status) {
        return this.coachRepository.findCoachByUserProfileAndStatus(userProfile, status);
    }
}
