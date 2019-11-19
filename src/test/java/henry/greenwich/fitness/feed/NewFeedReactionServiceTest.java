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
import henry.greenwich.fitness.model.feed.NewFeedReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedReactionRepository;
import henry.greenwich.fitness.service.feed.NewFeedReactionService;
import henry.greenwich.fitness.service.feed.NewFeedService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class NewFeedReactionServiceTest {
	private UserProfile userProfile;
	private NewFeed newFeed;
	private List<Object> newFeedReactionObjectList;
	private NewFeedReaction newFeedReaction;
	
	@Mock
	private NewFeedReactionRepository newFeedReactionRepository;
	
	@Mock
	private NewFeedService newFeedService;
	
	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private NewFeedReactionService newFeedReactionService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "", "", 1, 1, 1000);
		this.newFeed = new NewFeed(1L, "", "", "", "", 1, new Date(), this.userProfile, 1, 1, 1);
		this.newFeedReaction = new NewFeedReaction(1L, 1, this.newFeed, this.userProfile);
		
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		when(this.newFeedService.getNewFeedById(1L)).thenReturn(this.newFeed);
		
		Object[] newFeedReactionObjectArr = new Object[] {1, 1, 1, 1};
		this.newFeedReactionObjectList = new ArrayList<>();
		this.newFeedReactionObjectList.add(newFeedReactionObjectArr);
	}
	
	@Test
	public void testAddNewFeedReaction() {
		when(this.newFeedReactionRepository.getNewFeedReaction(1L, 1L)).thenReturn(new ArrayList<Object>());
		when(this.newFeedReactionRepository.saveAndFlush(this.newFeedReaction)).thenReturn(this.newFeedReaction);
		
		NewFeedReaction insertedNewFeedReaction = this.newFeedReactionService.addNewFeedReaction(this.newFeedReaction);
		
		assertNotNull(insertedNewFeedReaction);
	}
	
	@Test
	public void testGetNewFeedReactions() {
		when(this.newFeedReactionRepository.getNewFeedReactions(1)).thenReturn(this.newFeedReactionObjectList);
		
		List<NewFeedReaction> newFeedReactions = this.newFeedReactionService.getNewFeedReactions(1);
		
		assertEquals(1, newFeedReactions.size());
	}
}
