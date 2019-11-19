package henry.greenwich.fitness.feed;

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

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedRepository;
import henry.greenwich.fitness.service.feed.NewFeedService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class NewFeedServiceTest {
	private UserProfile userProfile;
	private NewFeed newFeed;
	private List<Object> newFeedObjectList;
	
	@Mock
	private NewFeedRepository newFeedRepository;
	
	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private NewFeedService newFeedService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		
		this.newFeed = new NewFeed(1L, "", "", "", "", 1, new Date(), this.userProfile, 1, 1, 1);
		
		Object[] newFeedObjectArr = new Object[] {1, "", "", "", "", 1, new Date() ,1, 1, 1, 1};
		this.newFeedObjectList = new ArrayList<>();
		this.newFeedObjectList.add(newFeedObjectArr);
	}
	
	@Test
	public void testGetNewFeedById() {
		when(this.newFeedRepository.findNewFeedById(1L)).thenReturn(this.newFeed);
		
		NewFeed selectedNewFeed = this.newFeedService.getNewFeedById(1L);
		
		assertNotNull(selectedNewFeed);
	}
	
	@Test
	public void testUpdateNewFeed() {
		when(this.newFeedRepository.saveAndFlush(this.newFeed)).thenReturn(this.newFeed);
		
		NewFeed updatedNewFeed = this.newFeedService.updateNewFeed(this.newFeed);
		
		assertNotNull(updatedNewFeed);
	}
	
	@Test
	public void testAddNewFeed() {
		when(this.newFeedRepository.saveAndFlush(this.newFeed)).thenReturn(this.newFeed);
		
		NewFeed insertedNewFeed = this.newFeedService.addNewFeed(this.newFeed);
		
		assertNotNull(insertedNewFeed);
	}
	
	@Test
	public void testGetNewFeeds() {
		when(this.newFeedRepository.getNewFeeds(1, "")).thenReturn(this.newFeedObjectList);
		
		List<NewFeed> newFeeds = this.newFeedService.getNewFeeds(1, "");
		
		assertEquals(1, newFeeds.size());
	}
	
	@Test
	public void testGetNewFeedsByPage() {
		when(this.newFeedRepository.getNewFeedsByPage(1, "", 0)).thenReturn(this.newFeedObjectList);
		
		List<NewFeed> newFeeds = this.newFeedService.getNewFeedsByPage(1, "", 0);
		
		assertEquals(1,newFeeds.size());
	}
	
	@Test
	public void testGetNumberOfNewFeeds() {
		List<Object> nNewFeedsObjectList = new ArrayList<>();
		nNewFeedsObjectList.add(new Integer(8));
		
		when(this.newFeedRepository.getNumberOfNewFeeds(1, "")).thenReturn(nNewFeedsObjectList);
		
		int nNewFeeds = this.newFeedService.getNumberOfNewFeeds(1, "");
		
		assertEquals(8, nNewFeeds);
	}
}
