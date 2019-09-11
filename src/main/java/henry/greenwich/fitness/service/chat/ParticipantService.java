package henry.greenwich.fitness.service.chat;

import org.springframework.stereotype.Service;

import henry.greenwich.fitness.model.chat.Participant;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.chat.ParticipantRepository;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;

@Service
public class ParticipantService {
    private ParticipantRepository participantRepository;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param chatRoomRepository - inject chatRoomRepository
     * @param userProfileService - inject userProfileService
     * @param coachService - inject coachService
     */
    public ParticipantService(ParticipantRepository participantRepository, UserProfileService userProfileService,
            CoachService coachService) {
        this.participantRepository = participantRepository;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * 
     * @param userProfileId - user's profile's id that will be used to get selected
     *                      participant
     * @param coachId       - coach's id that will be used to get selected
     *                      participant
     * @return selected participant
     */
    public Participant getParticipant(Long userProfileId, Integer coachId) {
        UserProfile userProfile = this.getUserProfile(userProfileId);
        Coach coach = this.getCoach(coachId);
        return this.participantRepository.findParticipantByUserProfileAndCoach(userProfile, coach);
    }

    /**
     *
     * @param participant - participant that will be added to the database
     * @return inserted participant
     */
    public Participant addParticipant(Participant participant) {
        return this.participantRepository.saveAndFlush(participant);
    }

    /**
     * 
     * @param userProfileId - user's profile's id that will be used to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * 
     * @param coachId - coach's id that will be used to get selected coach
     * @return selected coach
     */
    private Coach getCoach(Integer coachId) {
        return this.coachService.getCoach(coachId, null);
    }

}
