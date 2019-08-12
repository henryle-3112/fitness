package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachFeedbackReactionRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachFeedbackReactionService {
    private CoachFeedbackReactionRepository coachFeedbackReactionRepository;
    private CoachFeedbackService coachFeedbackService;
    private UserProfileService userProfileService;

    /**
     * @param coachFeedbackReactionRepository - inject
     *                                        coachFeedbackReactionRepository
     * @param coachFeedbackService            - inject coachFeedbackService
     * @param userProfileService              - inject userProfileService
     */
    public CoachFeedbackReactionService(CoachFeedbackReactionRepository coachFeedbackReactionRepository,
                                        CoachFeedbackService coachFeedbackService,
                                        UserProfileService userProfileService) {
        this.coachFeedbackReactionRepository = coachFeedbackReactionRepository;
        this.coachFeedbackService = coachFeedbackService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param coachFeedbackReaction - coach's feedback's reaction that user want to
     *                              add
     * @return inserted coach's feedback's reaction
     */
    public CoachFeedbackReaction addCoachFeedbackReaction(CoachFeedbackReaction coachFeedbackReaction) {
        // check coach feedback reaction existed in the database or not
        // if not create new one, if yes, update coach feedback reactioin
        Long userProfileId = coachFeedbackReaction.getUserProfile().getId();
        Long coachFeedbackId = coachFeedbackReaction.getCoachFeedback().getId();
        CoachFeedbackReaction selectedCoachFeedbackReaction = this.getCoachFeedbackReaction(userProfileId,
                coachFeedbackId);
        if (selectedCoachFeedbackReaction != null) {
            selectedCoachFeedbackReaction.setReaction(coachFeedbackReaction.getReaction());
            return this.coachFeedbackReactionRepository.saveAndFlush(selectedCoachFeedbackReaction);
        }
        return this.coachFeedbackReactionRepository.saveAndFlush(coachFeedbackReaction);
    }

    /**
     * @param userProfileId   - user's profile's id that user want to get selected
     *                        coach's feedback's reaction
     * @param coachFeedbackId - coach's feedback's id that user want to get selected
     *                        coach's feedback's reaction
     * @return selected coach's feedback's reaction
     */
    private CoachFeedbackReaction getCoachFeedbackReaction(Long userProfileId, Long coachFeedbackId) {
        List<Object> coachFeedbackReactionsObjectList = this.coachFeedbackReactionRepository
                .getCoachFeedbackReaction(userProfileId, coachFeedbackId);
        // because userProfileId and coachFeedbackId are unique. Therefore, the maximum
        // length of the list is one.
        // That's why .get(0) will be use in this situation
        if (coachFeedbackReactionsObjectList.size() > 0) {
            return this.getCoachFeedbackReactionsFromObjectList(coachFeedbackReactionsObjectList).get(0);
        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get
     * @return list of coach's feedback's reactions
     */
    public List<CoachFeedbackReaction> getCoachFeedbackReactions(Integer userProfileId) {
        List<Object> coachFeedbackReactionsObjectList = this.coachFeedbackReactionRepository
                .getCoachFeedbackReactions(userProfileId);
        return this.getCoachFeedbackReactionsFromObjectList(coachFeedbackReactionsObjectList);
    }

    /**
     * @param coachFeedbackReactionsObjectList - coach's feedback's reactions object
     *                                         list that user want to convert to
     *                                         coach's feedback's reactions list
     * @return list of coach's feedback's reactions
     */
    private List<CoachFeedbackReaction> getCoachFeedbackReactionsFromObjectList(
            List<Object> coachFeedbackReactionsObjectList) {
        List<CoachFeedbackReaction> coachFeedbackReactions = new ArrayList<>();
        for (Object o : coachFeedbackReactionsObjectList) {
            Object[] coachFeedbackReactionArr = (Object[]) o;
            CoachFeedbackReaction coachFeedbackReaction = this
                    .createCoachFeedbackReactionFromObjectArray(coachFeedbackReactionArr);
            coachFeedbackReactions.add(coachFeedbackReaction);
        }
        return coachFeedbackReactions;
    }

    /**
     * @param coachFeedbackReactionArr - coach's feedback's reaction object array
     *                                 that user want to convert to coach's
     *                                 feedback's reaction
     * @return converted coach's feedback's reaction
     */
    private CoachFeedbackReaction createCoachFeedbackReactionFromObjectArray(Object[] coachFeedbackReactionArr) {
        int eachCoachFeedbackReactionId = (int) coachFeedbackReactionArr[0];
        int eachCoachFeedbackReactionValue = (int) coachFeedbackReactionArr[1];
        int coachFeedbackId = (int) coachFeedbackReactionArr[2];
        CoachFeedback coachFeedback = this.getCoachFeedback((long) coachFeedbackId);
        int userProfileId = (int) coachFeedbackReactionArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new CoachFeedbackReaction((long) eachCoachFeedbackReactionId, eachCoachFeedbackReactionValue,
                coachFeedback, userProfile);
    }

    /**
     * @param coachFeedbackId - coach's feedback's id that user want to get selected
     *                        coach's feedback
     * @return selected coach's feedback
     */
    private CoachFeedback getCoachFeedback(Long coachFeedbackId) {
        return this.coachFeedbackService.getCoachFeedback(coachFeedbackId);
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
