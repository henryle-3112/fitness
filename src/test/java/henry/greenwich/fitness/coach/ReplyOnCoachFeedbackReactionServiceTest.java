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
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedback;
import henry.greenwich.fitness.model.coach.ReplyOnCoachFeedbackReaction;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.ReplyOnCoachFeedbackReactionRepository;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackReactionService;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class ReplyOnCoachFeedbackReactionServiceTest {
	private UserProfile userProfile;
	private ReplyOnCoachFeedback replyOnCoachFeedback;
	private Coach coach;
	private CoachFeedback coachFeedback;
	private ReplyOnCoachFeedbackReaction replyOnCoachFeedbackReaction;

	@Mock
	private ReplyOnCoachFeedbackReactionRepository replyOnCoachFeedbackReactionRepository;

	@Mock
	private UserProfileService userProfileService;

	@Mock
	private ReplyOnCoachFeedbackService replyOnCoachFeedbackService;

	@InjectMocks
	private ReplyOnCoachFeedbackReactionService replyOnCoachFeedbackReactionService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		this.coach = new Coach(1L, userProfile, "", 1, 5.0f, 1);
		this.coachFeedback = new CoachFeedback(1L, this.userProfile, this.coach, "", 1, new Date(), 1, 1, 1);
		this.replyOnCoachFeedback = new ReplyOnCoachFeedback(1L, "Reply On Coach Feedback Content", 1, new Date(),
				this.coachFeedback, this.userProfile, 1, 1);
		this.replyOnCoachFeedbackReaction = new ReplyOnCoachFeedbackReaction(1L, 1, this.replyOnCoachFeedback,
				this.userProfile);

		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		when(this.replyOnCoachFeedbackService.getRelyOnCoachFeedback(1L)).thenReturn(this.replyOnCoachFeedback);
	}

	@Test
	public void testAddReplyOnCoachFeedbackReaction() {
		when(this.replyOnCoachFeedbackReactionRepository.getReplyOnCoachFeedbackReaction(1L, 1L))
				.thenReturn(new ArrayList<Object>());

		when(this.replyOnCoachFeedbackReactionRepository.saveAndFlush(this.replyOnCoachFeedbackReaction))
				.thenReturn(this.replyOnCoachFeedbackReaction);

		ReplyOnCoachFeedbackReaction insertedReplyOnCoachFeedbackReaction = this.replyOnCoachFeedbackReactionService
				.addReplyOnCoachFeedbackReaction(this.replyOnCoachFeedbackReaction);

		assertNotNull(insertedReplyOnCoachFeedbackReaction);
	}

	@Test
	public void testGetReplyOnCoachFeedbackReactions() {
		Object[] replyOnCoachFeedbackReactionObjectArr = new Object[] { 1, 1, 1, 1 };
		List<Object> replyOnCoachFeedbackReactionObjectList = new ArrayList<Object>();
		replyOnCoachFeedbackReactionObjectList.add(replyOnCoachFeedbackReactionObjectArr);

		when(this.replyOnCoachFeedbackReactionRepository.getReplyOnCoachFeedbackReactions(1))
				.thenReturn(replyOnCoachFeedbackReactionObjectList);

		List<ReplyOnCoachFeedbackReaction> replyOnCoachFeedbackReactions = this.replyOnCoachFeedbackReactionService
				.getReplyOnCoachFeedbackReactions(1);
		
		assertEquals(1, replyOnCoachFeedbackReactions.size());
	}
}
