package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachFeedbackRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CoachFeedbackService {
    private CoachFeedbackRepository coachFeedbackRepository;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param coachFeedbackRepository - inject coachFeedbackRepository
     * @param userProfileService      - inject userProfileService
     * @param coachService            - inject coachService
     */
    public CoachFeedbackService(CoachFeedbackRepository coachFeedbackRepository,
                                UserProfileService userProfileService,
                                CoachService coachService) {
        this.coachFeedbackRepository = coachFeedbackRepository;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param coachFeedbackStatus - coach's feedback's status that user want to get
     *                            (this parameter could be optional)
     * @return list of coach's feedbacks
     */
    public List<CoachFeedback> getCoachFeedbacks(Integer coachId, Integer coachFeedbackStatus) {
        List<Object> coachFeedbacksObjectList = this.coachFeedbackRepository.getCoachFeedbacks(coachFeedbackStatus,
                coachId);
        return this.getCoachFeedbacksFromObjectList(coachFeedbacksObjectList);
    }

    /**
     * @param coachFeedback - coach's feedback that user want to add to the database
     * @return inserted coach's feedbacks
     */
    public CoachFeedback addCoachFeedback(CoachFeedback coachFeedback) {
        return this.coachFeedbackRepository.saveAndFlush(coachFeedback);
    }

    /**
     * @param coachFeedbacksObjectList - coach's feedbacks object list that user
     *                                 want to convert to coach's feedback list
     * @return list of coach's feedbacks
     */
    private List<CoachFeedback> getCoachFeedbacksFromObjectList(List<Object> coachFeedbacksObjectList) {
        List<CoachFeedback> coachFeedbacks = new ArrayList<>();
        for (Object o : coachFeedbacksObjectList) {
            Object[] coachFeedbackObjectArr = (Object[]) o;
            CoachFeedback coachFeedback = this.createCoachFeedbackFromObjectArray(coachFeedbackObjectArr);
            coachFeedbacks.add(coachFeedback);
        }
        return coachFeedbacks;
    }

    /**
     * @param coachFeedbackObjectArr - coach's feedback object array that user want
     *                               to convert to coach's feedback object
     * @return converted coach's feedback
     */
    private CoachFeedback createCoachFeedbackFromObjectArray(Object[] coachFeedbackObjectArr) {
        int coachFeedbackId = (int) coachFeedbackObjectArr[0];
        String coachFeedbackContent = (String) coachFeedbackObjectArr[1];
        Date coachFeedbackCreatedDate = (Date) coachFeedbackObjectArr[2];
        int coachFeedbackStatus = (int) coachFeedbackObjectArr[3];
        int userProfileId = (int) coachFeedbackObjectArr[4];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int coachId = (int) coachFeedbackObjectArr[5];
        Coach coach = this.getCoach(coachId, null);
        int nCoachFeebackLikes = (int) coachFeedbackObjectArr[6];
        int nCoachFeedbackDislikes = (int) coachFeedbackObjectArr[7];
        int nCoachFeedbackReplies = (int) coachFeedbackObjectArr[8];
        return new CoachFeedback((long) coachFeedbackId, userProfile, coach, coachFeedbackContent, coachFeedbackStatus,
                coachFeedbackCreatedDate, nCoachFeebackLikes, nCoachFeedbackDislikes, nCoachFeedbackReplies);
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

    /**
     * @param id - coach's feedback's id that user want to get
     * @return selected coach's feedback
     */
    public CoachFeedback getCoachFeedback(Long id) {
        return this.coachFeedbackRepository.findCoachFeedbackById(id);
    }


    /**
     * @param coachFeedback - coach's feedback that user want to update
     * @return updated coach's feedback
     */
    public CoachFeedback updateCoachFeedback(CoachFeedback coachFeedback) {
        return this.coachFeedbackRepository.saveAndFlush(coachFeedback);
    }
}
