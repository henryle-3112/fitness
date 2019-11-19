package henry.greenwich.fitness.chat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.chat.ChatRoom;
import henry.greenwich.fitness.model.chat.Participant;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.chat.ParticipantRepository;
import henry.greenwich.fitness.service.chat.ParticipantService;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class ParticipantServiceTest {
	
	@Mock
	private UserProfileService userProfileService;
	
	@Mock
	private CoachService coachService;
	
	@Mock
	private ParticipantRepository participantRepository;
	
	@InjectMocks
	private ParticipantService participantService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetParticipant() {		
		UserProfile userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(userProfile);
		
		UserProfile coachProfile = new UserProfile(2L, "Jack", "", 1, 1, 1000);
		Coach coach = new Coach(1L, coachProfile, "", 1, 5.0f, 1);
		when(this.coachService.getCoach(1, null)).thenReturn(coach);
		
		ChatRoom chatRoom = new ChatRoom(1L, "Chat Room", "normal");
		
		Participant participant = new Participant(1L, coach, userProfile, chatRoom);
		
		when(this.participantRepository.findParticipantByUserProfileAndCoach(userProfile, coach)).thenReturn(participant);
		
		Participant selectedParticipant = this.participantService.getParticipant(1L, 1);
		
		assertNotNull(selectedParticipant);
	}

}
