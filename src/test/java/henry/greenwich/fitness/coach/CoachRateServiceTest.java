package henry.greenwich.fitness.coach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.CoachRate;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachRateRepository;
import henry.greenwich.fitness.service.coach.CoachRateService;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class CoachRateServiceTest {
	private UserProfile userProfile;
	private Coach coach;
	
	@Mock
	private UserProfileService userProfileService;
	
	@Mock
	private CoachService coachService;
	
	@Mock
	private CoachRateRepository coachRateRepository;
	
	@InjectMocks
	private CoachRateService coachRateService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		
		this.coach = new Coach(1L, userProfile, "", 1, 5.0f, 1);
		when(this.coachService.getCoach(1, null)).thenReturn(this.coach);
	}
	
	@Test
	public void testAddCoachRate() {
		when(this.coachRateRepository.getCoachRate(1L, 1L)).thenReturn(new ArrayList<Object>());
		
		CoachRate coachRate = new CoachRate(1L, 5, this.coach, this.userProfile);
		when(this.coachRateRepository.saveAndFlush(coachRate)).thenReturn(coachRate);
		
		CoachRate insertedCoachRate = this.coachRateService.addCoachRate(coachRate);
		
		assertNotNull(insertedCoachRate);
	}
	
	@Test
	public void testGetCoachRate() {
		Object[] coachRateObjectArr = new Object[] {1, 5, 1, 1, 1};
		List<Object> coachRateObjectList = new ArrayList<>();
		coachRateObjectList.add(coachRateObjectArr);
		
		when(this.coachRateRepository.getCoachRate(1L, 1L)).thenReturn(coachRateObjectList);
		
		CoachRate selectedCoachRate = this.coachRateService.getCoachRate(1L, 1L);
		
		assertNotNull(selectedCoachRate);		
	}
}
