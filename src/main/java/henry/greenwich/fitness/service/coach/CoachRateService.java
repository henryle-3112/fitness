package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachRate;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachRateRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachRateService {
    private CoachRateRepository coachRateRepository;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param coachRateRepository - inject coachRateRepository
     * @param userProfileService  - inject userProfileService
     * @param coachService        - inject coachService
     */
    public CoachRateService(CoachRateRepository coachRateRepository, UserProfileService userProfileService,
            CoachService coachService) {
        this.coachRateRepository = coachRateRepository;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param coachRate - coach's rate that user want to add to the database
     * @return inserted coach's rate
     */
    public CoachRate addCoachRate(CoachRate coachRate) {
        // check coachRate existed in the database or not
        // if not, create new one, if yes, just update
        Long userProfileId = coachRate.getUserProfile().getId();
        Long coachId = coachRate.getCoach().getId();
        CoachRate selectedCoachRate = this.getCoachRate(userProfileId, coachId);
        if (selectedCoachRate == null) {
            return this.coachRateRepository.saveAndFlush(coachRate);
        }
        selectedCoachRate.setRate(coachRate.getRate());
        return this.coachRateRepository.saveAndFlush(selectedCoachRate);
    }

    /**
     * @param userProfileId - user's profile' id that user want to get coach's rate
     * @param coachId       - coach's id that user want to get coach's rate
     * @return selected coach's rate
     */
    public CoachRate getCoachRate(Long userProfileId, Long coachId) {
        List<Object> coachRatesObjectList = this.coachRateRepository.getCoachRate(userProfileId, coachId);
        // because userProfileId and coachId are unique. Therefore, the maximum length
        // of the list is one.
        // That's why .get(0) will be used in this situation
        if (coachRatesObjectList.size() > 0) {
            return this.getCoachRatesFromObjectList(coachRatesObjectList).get(0);
        }
        return null;
    }

    /**
     * @param coachRatesObjectList - coach rates object list that user want to
     *                             convert to coach rates list
     * @return list of coach's rates
     */
    private List<CoachRate> getCoachRatesFromObjectList(List<Object> coachRatesObjectList) {
        List<CoachRate> coachRates = new ArrayList<>();
        for (Object o : coachRatesObjectList) {
            Object[] coachRateObjectArr = (Object[]) o;
            CoachRate coachRate = this.createCoachRateFromObjectList(coachRateObjectArr);
            coachRates.add(coachRate);
        }
        return coachRates;
    }

    /**
     * @param coachRateObjectArr - coach's rate object array that user want to
     *                           convert to coach's rate object
     * @return converted coach's rate
     */
    private CoachRate createCoachRateFromObjectList(Object[] coachRateObjectArr) {
        int coachRateId = (int) coachRateObjectArr[0];
        int coachRateValue = (int) coachRateObjectArr[1];
        int coachId = (int) coachRateObjectArr[2];
        Coach coach = this.getCoach(coachId, null);
        int userProfileId = (int) coachRateObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new CoachRate((long) coachRateId, coachRateValue, coach, userProfile);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param coachId     - coach's id that user want to get selected coach
     * @param coachStatus - coach's status that user want to get selected coach
     * @return selected coach
     */
    private Coach getCoach(Integer coachId, Integer coachStatus) {
        return this.coachService.getCoach(coachId, coachStatus);
    }
}
