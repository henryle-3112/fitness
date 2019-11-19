package henry.greenwich.fitness.coach;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.CoachRepository;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class CoachServiceTest {
	private UserProfile userProfile;
	private List<Object> coachObjectList;
	
	@Mock
	private CoachRepository coachRepository;
	
	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private CoachService coachService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(userProfile);
		
		Object[] coachObjectArr = new Object[] {1, 1, "Coach About Content", 1, 5, 1};
		this.coachObjectList = new ArrayList<>();
		this.coachObjectList.add(coachObjectArr);
	}
	
	@Test
	public void testGetCoaches() {		
		when(this.coachRepository.getCoaches(1, "David")).thenReturn(this.coachObjectList);
		
		List<Coach> coaches = this.coachService.getCoaches(1, "David");
		
		assertEquals(1, coaches.size());
	}
	
	@Test
	public void testGetCoachesByPage() {
		when(this.coachRepository.getCoachesByPage(1, "David", 0)).thenReturn(this.coachObjectList);
		
		List<Coach> coaches = this.coachService.getCoachesByPage(1, "David", 0);
		
		assertEquals(1, coaches.size());
	}
	
	@Test
	public void testGetCoach() {
		when(this.coachRepository.getCoach(1, 1)).thenReturn(this.coachObjectList);
		
		Coach selectedCoach = this.coachService.getCoach(1, 1);
		
		assertNotNull(selectedCoach);
	}
	
	@Test
	public void getCoachByUser() {
		when(this.coachRepository.getCoach(1, 1)).thenReturn(this.coachObjectList);
		
		Coach selectedCoach = this.coachService.getCoach(1, 1);
		
		assertNotNull(selectedCoach);
	}
	
	@Test
	public void testNumberOfCoaches() {
		List<Object> nCoachesObjectList = new ArrayList<>();
		nCoachesObjectList.add(new Integer(8));
		
		when(this.coachRepository.getNumberOfCoaches(1, "David")).thenReturn(nCoachesObjectList);
		
		int nCoaches = this.coachService.getNumberOfCoaches(1, "David");
		
		assertEquals(8, nCoaches);
	}
}
