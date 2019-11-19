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
import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedCommentRepository;
import henry.greenwich.fitness.service.feed.NewFeedCommentService;
import henry.greenwich.fitness.service.feed.NewFeedService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class NewFeedCommentServiceTest {
	private UserProfile userProfile;
	private NewFeed newFeed;
	private NewFeedComment newFeedComment;
	
	@Mock
	private UserProfileService userProfileService;
	
	@Mock
	private NewFeedService newFeedService;
	
	@Mock
	private NewFeedCommentRepository newFeedCommentRepository;
	
	@InjectMocks
	private NewFeedCommentService newFeedCommentService;
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "", "", 1, 1, 1000);
		this.newFeed = new NewFeed(1L, "", "", "11/17/2019", "", 1, new Date(), this.userProfile, 1, 1, 1);
		this.newFeedComment = new NewFeedComment(1L, "", new Date(), this.newFeed, this.userProfile, 1, 1, 1, 1);

		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		when(this.newFeedService.getNewFeedById(1L)).thenReturn(this.newFeed);
	}
	
	@Test
	public void testAddNewFeedComment() {
		
		when(this.newFeedCommentRepository.saveAndFlush(this.newFeedComment)).thenReturn(this.newFeedComment);
		
		NewFeedComment insertedNewFeedComment = this.newFeedCommentService.addNewFeedComment(this.newFeedComment);
		
		assertNotNull(insertedNewFeedComment);
	}
	
	@Test
	public void testGetNewFeedComments() {
		Object[] newFeedCommentObjectArr = new Object[] {1, "", new Date(), 1, 1, 1, 1, 1, 1};
		List<Object> newFeedCommentObjectList = new ArrayList<>();
		newFeedCommentObjectList.add(newFeedCommentObjectArr);
		
		when(this.newFeedCommentRepository.getNewFeedComments(1, 1)).thenReturn(newFeedCommentObjectList);
		
		List<NewFeedComment> newFeedComments = this.newFeedCommentService.getNewFeedComments(1, 1);
		
		assertEquals(1, newFeedComments.size());
		
	}
	
	@Test
	public void testGetNewFeedComment() {
		when(this.newFeedCommentRepository.findNewFeedCommentById(1L)).thenReturn(this.newFeedComment);
		
		NewFeedComment selectedNewFeedComment = this.newFeedCommentService.getNewFeedComment(1L);
		
		assertNotNull(selectedNewFeedComment);
	}
	
	@Test
	public void updateNewFeedComment() {
		when(this.newFeedCommentRepository.saveAndFlush(this.newFeedComment)).thenReturn(this.newFeedComment);
		
		NewFeedComment updatedNewFeedComment = this.newFeedCommentService.updateNewFeedComment(this.newFeedComment);
		
		assertNotNull(updatedNewFeedComment);
	}
}
