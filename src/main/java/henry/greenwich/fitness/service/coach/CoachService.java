package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachService {
    private CoachRepository coachRepository;
    private UserProfileService userProfileService;

    /**
     * @param coachRepository    - inject coachRepository
     * @param userProfileService - inject userProfileService
     */
    public CoachService(CoachRepository coachRepository, UserProfileService userProfileService) {
        this.coachRepository = coachRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param coachStatus           - coach's status that user want to get coaches
     *                              (this parameter could be optional)
     * @param coachFullNameKeywords - coach's fullname's keywords that user want to
     *                              filter coaches (this parameter can be optional)
     * @return list of coaches
     */
    public List<Coach> getCoaches(Integer coachStatus, String coachFullNameKeywords) {
        List<Object> coachesObjectList = this.coachRepository.getCoaches(coachStatus, coachFullNameKeywords);
        return this.getCoachesFromObjectList(coachesObjectList);
    }

    /**
     * @param coachStatus           - coach's status that user want to get coaches
     *                              (this parameter could be optional)
     * @param coachFullNameKeywords - coach's full name that user want to get (this
     *                              parameter could be optional)
     * @param startIndex            - start index (for pagination)
     * @return list of coaches
     */
    public List<Coach> getCoachesByPage(Integer coachStatus, String coachFullNameKeywords, Integer startIndex) {
        List<Object> coachesObjectList = this.coachRepository.getCoachesByPage(coachStatus, coachFullNameKeywords,
                startIndex);
        return this.getCoachesFromObjectList(coachesObjectList);
    }

    /**
     * 
     * @param coachStatus           - coach's status that user want to get coaches
     *                              (this parameter could be optional)
     * @param coachIds              - coach ids that user want to get coaches (this
     *                              parameter could be optional)
     * @param coachFullNameKeywords - coach's full name that user want to get
     *                              coaches (this parameter could be optional)
     * @param startIndex            - start index (for pagination) (this parameter
     *                              could be optional)
     * @return list of coaches
     */
    public List<Coach> getCoachesByPageAndIdsIn(Integer coachStatus, List<Long> coachIds, String coachFullNameKeywords,
            Integer startIndex) {
        List<Object> coachesObjectList = this.coachRepository.getCoachesByPageAndIdsIn(coachStatus, coachIds,
                coachFullNameKeywords, startIndex);
        return this.getCoachesFromObjectList(coachesObjectList);
    }

    /**
     * @param coachId     - coach's id that user want to get selected coach
     * @param coachStatus - coach's status that user want to get selected coach
     * @return selected coach
     */
    public Coach getCoach(Integer coachId, Integer coachStatus) {
        List<Object> coachesObjectList = this.coachRepository.getCoach(coachId, coachStatus);
        // because coachId and userProfileId are unique. Therefore the maximum length of
        // the list is one.
        // That's why we will use .get(0) in this situation
        List<Coach> coaches = this.getCoachesFromObjectList(coachesObjectList);
        if (coaches.size() > 0) {
            return coaches.get(0);
        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      coach
     * @param coachStatus   - coach's status that user want to get selected coach
     * @return selected coach
     */
    public Coach getCoachByUser(Integer userProfileId, Integer coachStatus) {
        List<Object> coachesObjectList = this.coachRepository.getCoachByUser(userProfileId, coachStatus);
        // because userProfileId are unique. Therefore the maximum length of the list is
        // one.
        // That's why we will use .get(0) in this situation
        List<Coach> coaches = this.getCoachesFromObjectList(coachesObjectList);
        if (coaches.size() > 0) {
            return coaches.get(0);
        }
        return null;
    }

    /**
     * @param coachStatus           - coach's status that user want to get (this
     *                              parameter could be optional)
     * @param coachIds              - coach's ids that user want to get (this
     *                              parameter could be optional)
     * @param coachFullNameKeywords - coach's full name that user want to get (this
     *                              parameter could be optional)
     * @return number of coaches
     */
    public int getNumberOfCoachesByIdsIn(Integer coachStatus, List<Long> coachIds, String coachFullNameKeywords) {
        List<Object> nCoachesObjectList = this.coachRepository.getNumberOfCoachesByIdsIn(coachStatus, coachIds,
                coachFullNameKeywords);
        if (nCoachesObjectList.size() > 0) {
            return Integer.valueOf(nCoachesObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param coachStatus           - coach's status that user want to get number of
     *                              coaches
     * @param coachFullNameKeywords - coach's full name that user want to get
     * @return number of coaches
     */
    public int getNumberOfCoaches(Integer coachStatus, String coachFullNameKeywords) {
        List<Object> nCoachesObjectList = this.coachRepository.getNumberOfCoaches(coachStatus, coachFullNameKeywords);
        if (nCoachesObjectList.size() > 0) {
            return Integer.valueOf(nCoachesObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param coachesObjectList - coaches object list that user want to convert to
     *                          coaches list
     * @return list of coaches
     */
    private List<Coach> getCoachesFromObjectList(List<Object> coachesObjectList) {
        List<Coach> coaches = new ArrayList<>();
        for (Object o : coachesObjectList) {
            Object[] coachObjectArr = (Object[]) o;
            Coach coach = this.createCoachFromObjectArray(coachObjectArr);
            coaches.add(coach);
        }
        return coaches;
    }

    /**
     * @param coachObjectArr - coach object array that user want to convert to coach
     *                       object
     * @return converted coach
     */
    private Coach createCoachFromObjectArray(Object[] coachObjectArr) {
        int coachId = (int) coachObjectArr[0];
        int userProfileId = (int) coachObjectArr[1];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        String coachAbout = (String) coachObjectArr[2];
        int coachStatus = (int) coachObjectArr[3];
        int coachRatingAverage = (int) coachObjectArr[4];
        int nMemberships = (int) coachObjectArr[5];
        return new Coach((long) coachId, userProfile, coachAbout, coachStatus, coachRatingAverage, nMemberships);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

}
