package henry.greenwich.fitness.coach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.coach.CoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachFeedbackReactionRepository;
import henry.greenwich.fitness.service.coach.CoachFeedbackReactionService;
import henry.greenwich.fitness.service.coach.CoachFeedbackService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class CoachFeedbackReactionServiceTest {
	@Mock
	private CoachFeedbackReactionRepository coachFeedbackReactionRepository;
	
	@Mock
	private CoachFeedbackService coachFeedbackService;
	
	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private CoachFeedbackReactionService coachFeedbackReactionService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddCoachFeedbackReaction() {
		UserProfile userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		
		Coach coach = new Coach(1L, userProfile, "", 1, 5.0f, 1);
		
		CoachFeedback coachFeedback = new CoachFeedback(1L, userProfile, coach, "", 1, new Date(), 1, 1, 1);
		
		CoachFeedbackReaction coachFeedbackReaction = new CoachFeedbackReaction(1L, 1, coachFeedback, userProfile);
		
		when(this.coachFeedbackReactionRepository.getCoachFeedbackReaction(1L, 1L)).thenReturn(new ArrayList<Object>());
		
		when(this.coachFeedbackReactionRepository.saveAndFlush(coachFeedbackReaction)).thenReturn(coachFeedbackReaction);
		
		CoachFeedbackReaction insertedCoachFeedbackReaction = this.coachFeedbackReactionService.addCoachFeedbackReaction(coachFeedbackReaction);
		
		assertNotNull(insertedCoachFeedbackReaction);
	}
}
