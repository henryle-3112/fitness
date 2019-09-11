package henry.greenwich.fitness.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import henry.greenwich.fitness.model.chat.Participant;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    
    /**
     * 
     * @param userProfile - user's profile that will be used to get selected participant
     * @param coach - coach that will be used to get selected participant
     * @return selected participant
     */
    Participant findParticipantByUserProfileAndCoach(UserProfile userProfile, Coach coach);
}
