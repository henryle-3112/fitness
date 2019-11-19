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
import henry.greenwich.fitness.model.feed.NewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedCommentReactionRepository;
import henry.greenwich.fitness.service.feed.NewFeedCommentReactionService;
import henry.greenwich.fitness.service.feed.NewFeedCommentService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class NewFeedCommentReactionServiceTest {
	private UserProfile userProfile;
	private NewFeed newFeed;
	private NewFeedComment newFeedComment;
	private List<Object> newFeedCommentReactionObjectList;
	private NewFeedCommentReaction newFeedCommentReaction;
	
	@Mock
	private NewFeedCommentReactionRepository newFeedCommentReactionRepository;
	
	@Mock
	private NewFeedCommentService newFeedCommentService;
	
	@Mock
	private UserProfileService userProfileService;
	
	@InjectMocks
	private NewFeedCommentReactionService newFeedCommentReactionService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		this.newFeed = new NewFeed(1L, "Image", "Achievement", "11/16/2019", "Content", 1, new Date(), this.userProfile, 1, 1, 1);
		this.newFeedComment = new NewFeedComment(1L, "Content", new Date(), this.newFeed, this.userProfile, 1, 1, 1, 1);
		
		Object[] newFeedCommentReactionObjectArr = new Object[] {1, 1, 1, 1};
		this.newFeedCommentReactionObjectList = new ArrayList<>();
		this.newFeedCommentReactionObjectList.add(newFeedCommentReactionObjectArr);
		
		this.newFeedCommentReaction = new NewFeedCommentReaction(1L, 1, this.newFeedComment, this.userProfile);
		
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		when(this.newFeedCommentService.getNewFeedComment(1L)).thenReturn(this.newFeedComment);
	}
	
	@Test
	public void testAddNewFeedCommentReaction() {
		when(this.newFeedCommentReactionRepository.getNewFeedCommentReaction(1L, 1L)).thenReturn(new ArrayList<Object>());
		
		when(this.newFeedCommentReactionRepository.saveAndFlush(this.newFeedCommentReaction)).thenReturn(this.newFeedCommentReaction);
		
		NewFeedCommentReaction insertedNewFeedCommentReaction = this.newFeedCommentReactionService.addNewFeedCommentReaction(this.newFeedCommentReaction);
		
		assertNotNull(insertedNewFeedCommentReaction);
	}
	
	@Test
	public void testGetNewFeedCommentReactions() {
		when(this.newFeedCommentReactionRepository.getNewFeedCommentReactions(1)).thenReturn(this.newFeedCommentReactionObjectList);
		
		List<NewFeedCommentReaction> newFeedCommentReactions = this.newFeedCommentReactionService.getNewFeedCommentReactions(1);
		
		assertEquals(1, newFeedCommentReactions.size());
	}
}
