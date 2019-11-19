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
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.ReplyOnNewFeedCommentRepository;
import henry.greenwich.fitness.service.feed.NewFeedCommentService;
import henry.greenwich.fitness.service.feed.ReplyOnNewFeedCommentService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class ReplyOnNewFeedCommentServiceTest {
	private UserProfile userProfile;
	private NewFeedComment newFeedComment;
	private ReplyOnNewFeedComment replyOnNewFeedComment;
	private List<Object> repliesOnNewFeedCommentObjectList;

	@Mock
	private NewFeedCommentService newFeedCommentService;

	@Mock
	private UserProfileService userProfileService;

	@Mock
	private ReplyOnNewFeedCommentRepository replyOnNewFeedCommentRepository;

	@InjectMocks
	private ReplyOnNewFeedCommentService replyOnNewFeedComService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.userProfile = new UserProfile(1L, "", "", 1, 1, 1000);
		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);

		NewFeed newFeed = new NewFeed(1L, "", "", "", "", 1, new Date(), this.userProfile, 1, 1, 1);
		this.newFeedComment = new NewFeedComment(1L, "", new Date(), newFeed, this.userProfile, 1, 1, 1, 1);
		when(this.newFeedCommentService.getNewFeedComment(1L)).thenReturn(this.newFeedComment);

		this.replyOnNewFeedComment = new ReplyOnNewFeedComment(1L, "", this.newFeedComment, this.userProfile, 1,
				new Date(), 1, 1);

		Object[] replyOnNewFeedCommentObjectArr = new Object[] { 1, "", new Date(), 1, 1, 1, 1, 1 };
		this.repliesOnNewFeedCommentObjectList = new ArrayList<>();
		this.repliesOnNewFeedCommentObjectList.add(replyOnNewFeedCommentObjectArr);
	}

	@Test
	public void testAddReplyOnNewFeedComment() {
		when(this.replyOnNewFeedCommentRepository.saveAndFlush(this.replyOnNewFeedComment))
				.thenReturn(this.replyOnNewFeedComment);

		ReplyOnNewFeedComment insertedReplyOnNewFeedComment = this.replyOnNewFeedComService
				.addReplyOnNewFeedComment(this.replyOnNewFeedComment);

		assertNotNull(insertedReplyOnNewFeedComment);
	}

	@Test
	public void testGetReplyOnNewFeedComment() {
		when(this.replyOnNewFeedCommentRepository.findReplyOnNewFeedCommentById(1L))
				.thenReturn(this.replyOnNewFeedComment);

		ReplyOnNewFeedComment selectedReplyOnNewFeedComment = this.replyOnNewFeedComService
				.getReplyOnNewFeedComment(1L);

		assertNotNull(selectedReplyOnNewFeedComment);
	}

	@Test
	public void testUpdateReplyOnNewFeedComment() {
		when(this.replyOnNewFeedCommentRepository.saveAndFlush(this.replyOnNewFeedComment))
				.thenReturn(this.replyOnNewFeedComment);

		ReplyOnNewFeedComment updatedReplyOnNewFeedComment = this.replyOnNewFeedComService
				.updateReplyOnNewFeedComment(this.replyOnNewFeedComment);

		assertNotNull(updatedReplyOnNewFeedComment);
	}

	@Test
	public void testGetRepliesOnNewFeedComment() {
		when(this.replyOnNewFeedCommentRepository.getRepliesOnNewFeedComment(1, 1))
				.thenReturn(this.repliesOnNewFeedCommentObjectList);
		
		List<ReplyOnNewFeedComment> repliesOnNewFeedComments = this.replyOnNewFeedComService.getRepliesOnNewFeedComment(1, 1);
		
		assertEquals(1, repliesOnNewFeedComments.size());
	}
}
