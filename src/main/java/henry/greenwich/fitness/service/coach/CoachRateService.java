package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.CoachRate;
import henry.greenwich.fitness.repository.coach.CoachRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachRateService {
    /**
     * coachRateRepository - interact with coach's rate data
     */
    private CoachRateRepository coachRateRepository;

    /**
     * @param coachRateRepository - inject coachRateRepository
     */
    public CoachRateService(CoachRateRepository coachRateRepository) {
        this.coachRateRepository = coachRateRepository;
    }

    /**
     * @param coachRate - coach's rate
     * @return inserted coach's rate
     */
    public CoachRate addCoachRate(CoachRate coachRate) {
        // check coachRate existed in the database or not
        // if not, create new one, if yes, just update
        List<Object> coachRateObjectList = this.coachRateRepository.getCoachRateByUserIdAndCoachId(
                coachRate.getUserProfile().getId(),
                coachRate.getCoach().getId()
        );
        if (coachRateObjectList.isEmpty()) {
            return this.coachRateRepository.saveAndFlush(coachRate);
        }
        Object[] coachRateObjectArray = (Object[]) coachRateObjectList.get(0);
        int coachRateId = (int) coachRateObjectArray[0];
        coachRate.setId((long) coachRateId);
        return this.coachRateRepository.saveAndFlush(coachRate);
    }

    /**
     * @param userId  - user' id
     * @param coachId - coach's id
     * @return selected coach's rate
     */
    public List<Object> getCoachRateByUserIdAndCoachId(Long userId, Long coachId) {
        return this.coachRateRepository.getCoachRateByUserIdAndCoachId(userId, coachId);
    }

    /**
     * @param coachId - coach's id
     * @return selected rate's average
     */
    public List<Object> findRateAverate(int coachId) {
        return this.coachRateRepository.findRateAverage(coachId);
    }
}
