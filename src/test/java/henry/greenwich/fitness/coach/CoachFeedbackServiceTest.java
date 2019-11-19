package henry.greenwich.fitness.coach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachFeedbackRepository;
import henry.greenwich.fitness.service.coach.CoachFeedbackService;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class CoachFeedbackServiceTest {
	private UserProfile userProfile;
	private Coach coach;
	
	@Mock
	private CoachFeedbackRepository coachFeedbackRepository;
	
	@Mock
	private CoachService coachService;
	
	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private CoachFeedbackService coachFeedbackService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		this.coach = new Coach(1L, userProfile, "", 1, 5.0f, 1);
	}
	
	@Test
	public void testGetCoachFeedbacks() {
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);

		when(this.coachService.getCoach(1, null)).thenReturn(this.coach);
		
		Object[] coachFeedbackObjectArr = new Object[] {1, "", new Date(), 1, 1, 1, 1, 1, 1};
		List<Object> coachFeedbackObjectList = new ArrayList<>();
		coachFeedbackObjectList.add(coachFeedbackObjectArr);
		
		when(this.coachFeedbackRepository.getCoachFeedbacks(1, 1)).thenReturn(coachFeedbackObjectList);
		List<CoachFeedback> coachFeedbacks = this.coachFeedbackService.getCoachFeedbacks(1, 1);
		assertEquals(1, coachFeedbacks.size());
	}
	
	@Test
	public void testAddCoachFeedback() {
		CoachFeedback coachFeedback = new CoachFeedback(1L,  this.userProfile, this.coach, "", 1, new Date(), 1, 1, 1);
		
		when(this.coachFeedbackRepository.saveAndFlush(coachFeedback)).thenReturn(coachFeedback);
		
		CoachFeedback insertedCoachFeedback = this.coachFeedbackService.addCoachFeedback(coachFeedback);
		
		assertNotNull(insertedCoachFeedback);
	}
}
