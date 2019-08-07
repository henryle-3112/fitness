package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.ReplyOnCoachFeedbackRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyOnCoachFeedbackService {
    private ReplyOnCoachFeedbackRepository replyOnCoachFeedbackRepository;
    private CoachFeedbackService coachFeedbackService;
    private UserProfileService userProfileService;

    /**
     * @param replyOnCoachFeedbackRepository - inject replyOnCoachFeedbackRepository
     * @param coachFeedbackService           - inject coachFeedbackService
     * @param userProfileService             - inject userProfileService
     */
    public ReplyOnCoachFeedbackService(ReplyOnCoachFeedbackRepository replyOnCoachFeedbackRepository,
            CoachFeedbackService coachFeedbackService, UserProfileService userProfileService) {
        this.replyOnCoachFeedbackRepository = replyOnCoachFeedbackRepository;
        this.coachFeedbackService = coachFeedbackService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param coachFeedbackId            - coach's feedback's id that user want to
     *                                   get replies
     * @param replyOnCoachFeedbackStatus - status of reply on coach's feedback that
     *                                   user want to get (this parameter could be
     *                                   optional)
     * @return list of replies
     */
    public List<ReplyOnCoachFeedback> getRepliesOnCoachFeedback(Integer coachFeedbackId,
            Integer replyOnCoachFeedbackStatus) {
        List<Object> repliesOnCoachFeedbackObjectList = this.replyOnCoachFeedbackRepository
                .getRepliesOnCoachFeedback(coachFeedbackId, replyOnCoachFeedbackStatus);
        return this.getRepliesOnCoachFeedbackFromObjectList(repliesOnCoachFeedbackObjectList);
    }

    /**
     * @param replyOnCoachFeedback - reply on coach's feedback that user want to add
     *                             to the database
     * @return inserted reply on coach's feedback
     */
    public ReplyOnCoachFeedback addReplyOnCoachFeedback(ReplyOnCoachFeedback replyOnCoachFeedback) {
        return this.replyOnCoachFeedbackRepository.saveAndFlush(replyOnCoachFeedback);
    }

    /**
     * @param repliesOnCoachFeedbackObjectList - replies on coach's feedback object
     *                                         list that user want to convert to
     *                                         replies on coach's feedback list
     * @return list of replies on coach's feedback
     */
    private List<ReplyOnCoachFeedback> getRepliesOnCoachFeedbackFromObjectList(
            List<Object> repliesOnCoachFeedbackObjectList) {
        List<ReplyOnCoachFeedback> repliesOnCoachFeedback = new ArrayList<>();
        for (Object o : repliesOnCoachFeedbackObjectList) {
            Object[] replyOnCoachFeedbackObjectArr = (Object[]) o;
            ReplyOnCoachFeedback replyOnCoachFeedback = this
                    .createReplyOnCoachFeedbackFromObjectArr(replyOnCoachFeedbackObjectArr);
            repliesOnCoachFeedback.add(replyOnCoachFeedback);
        }
        return repliesOnCoachFeedback;
    }

    /**
     * @param replyOnCoachFeedbackObjectArr - reply on coach's feedback object array
     *                                      that user want to convert to reply on
     *                                      coach's feedback object
     * @return converted reply on coach's feedback
     */
    private ReplyOnCoachFeedback createReplyOnCoachFeedbackFromObjectArr(Object[] replyOnCoachFeedbackObjectArr) {
        int replyOnCoachFeedbackId = (int) replyOnCoachFeedbackObjectArr[0];
        String replyOnCoachFeedbackContent = (String) replyOnCoachFeedbackObjectArr[1];
        Date replyOnCoachFeedbackCreatedDate = (Date) replyOnCoachFeedbackObjectArr[2];
        int replyOnCoachFeedbackStatus = (int) replyOnCoachFeedbackObjectArr[3];
        int coachFeedbackId = (int) replyOnCoachFeedbackObjectArr[4];
        CoachFeedback coachFeedback = this.getCoachFeedback((long) coachFeedbackId);
        int userProfileId = (int) replyOnCoachFeedbackObjectArr[5];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int nReplyOnCoachFeedbackLikes = (int) replyOnCoachFeedbackObjectArr[6];
        int nReplyOnCoachFeedbackDislikes = (int) replyOnCoachFeedbackObjectArr[7];
        return new ReplyOnCoachFeedback((long) replyOnCoachFeedbackId, replyOnCoachFeedbackContent,
                replyOnCoachFeedbackStatus, replyOnCoachFeedbackCreatedDate, coachFeedback, userProfile,
                nReplyOnCoachFeedbackLikes, nReplyOnCoachFeedbackDislikes);
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
     * @param userProfile - user's profile that user want to get selected user's
     *                    profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfile) {
        return this.userProfileService.getUserProfile(userProfile);
    }

    /**
     * @param id - id of reply on coach's feedback that user want to get
     * @return selected reply on coach's feedback
     */
    public ReplyOnCoachFeedback getRelyOnCoachFeedback(Long id) {
        return this.replyOnCoachFeedbackRepository.findReplyOnCoachFeedbackById(id);
    }
}
