package henry.greenwich.fitness.feed;

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

import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedCommentReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.ReplyOnNewFeedCommentReactionRepository;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentReactionService;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class ReplyOnNewFeedCommentReactionServiceTest {
	private UserProfile userProfile;
	private List<Object> replyOnNewFeedCommentReactionObjectList;
	private ReplyOnNewFeedCommentReaction replyOnNewFeedCommentReaction;

	@Mock
	private UserProfileService userProfileService;

	@Mock
	private ReplyOnNewFeedCommentService replyOnNewFeedCommentService;

	@Mock
	private ReplyOnNewFeedCommentReactionRepository replyOnNewFeedCommentReactionRepository;

	@InjectMocks
	private ReplyOnNewFeedCommentReactionService replyOnNewFeedCommentReactionService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.userProfile = new UserProfile(1L, "", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);

		Object[] replyOnNewFeedCommentReactionObjectArr = new Object[] { 1, 1, 1, 1 };
		this.replyOnNewFeedCommentReactionObjectList = new ArrayList<>();
		this.replyOnNewFeedCommentReactionObjectList.add(replyOnNewFeedCommentReactionObjectArr);

		this.replyOnNewFeedCommentReaction = new ReplyOnNewFeedCommentReaction(1L, 1, new ReplyOnNewFeedComment(),
				this.userProfile);
	}

	@Test
	public void testAddReplyOnNewFeedCommentReaction() {
		when(this.replyOnNewFeedCommentReactionRepository.getReplyOnNewFeedCommentReaction(1L, 1L))
				.thenReturn(new ArrayList<Object>());
		when(this.replyOnNewFeedCommentReactionRepository.saveAndFlush(this.replyOnNewFeedCommentReaction))
				.thenReturn(this.replyOnNewFeedCommentReaction);

		ReplyOnNewFeedCommentReaction insertedReplyOnNewFeedCommentReaction = this.replyOnNewFeedCommentReactionService
				.addReplyOnNewFeedCommentReaction(this.replyOnNewFeedCommentReaction);

		assertNotNull(insertedReplyOnNewFeedCommentReaction);
	}

	@Test
	public void testGetReplyOnNewFeedCommentReactions() {
		when(this.replyOnNewFeedCommentReactionRepository.getReplyOnNewFeedCommentReactions(1))
				.thenReturn(this.replyOnNewFeedCommentReactionObjectList);
		
		List<ReplyOnNewFeedCommentReaction> replyOnNewFeedCommentReactions = this.replyOnNewFeedCommentReactionService.getReplyOnNewFeedCommentReactions(1);
		
		assertEquals(1, replyOnNewFeedCommentReactions.size());
	}

}
