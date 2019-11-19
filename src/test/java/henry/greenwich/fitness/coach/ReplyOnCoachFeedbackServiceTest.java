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
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.ReplyOnCoachFeedbackRepository;
import henry.greenwich.fitness.service.coach.CoachFeedbackService;
import henry.greenwich.fitness.service.coach.ReplyOnCoachFeedbackService;
import henry.greenwich.fitness.service.user.UserProfileService;

public class ReplyOnCoachFeedbackServiceTest {
	private UserProfile userProfile;
	private Coach coach;
	private CoachFeedback coachFeedback;
	private ReplyOnCoachFeedback replyOnCoachFeedback;

	@Mock
	private ReplyOnCoachFeedbackRepository replyOnCoachFeedbackRepository;

	@Mock
	private CoachFeedbackService coachFeedbackService;

	@Mock
	private UserProfileService userProfileService;

	@InjectMocks
	private ReplyOnCoachFeedbackService replyOnCoachFeedbackService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.userProfile = new UserProfile(1L, "David", "", 1, 1, 1000);
		this.coach = new Coach(1L, userProfile, "", 1, 5.0f, 1);
		this.coachFeedback = new CoachFeedback(1L, this.userProfile, this.coach, "", 1, new Date(), 1, 1, 1);
		this.replyOnCoachFeedback = new ReplyOnCoachFeedback(1L, "", 1, new Date(), this.coachFeedback,
				this.userProfile, 1, 1);

		when(this.userProfileService.getUserProfile(1L)).thenReturn(this.userProfile);
		when(this.coachFeedbackService.getCoachFeedback(1L)).thenReturn(this.coachFeedback);
	}

	@Test
	public void testAddReplyOnCoachFeedback() {
		ReplyOnCoachFeedback replyOnCoachFeedback = new ReplyOnCoachFeedback(1L, "Content", 1, new Date(),
				coachFeedback, userProfile, 1, 1);

		when(this.replyOnCoachFeedbackRepository.saveAndFlush(replyOnCoachFeedback)).thenReturn(replyOnCoachFeedback);

		ReplyOnCoachFeedback insertedReplyOnCoachFeedback = this.replyOnCoachFeedbackService
				.addReplyOnCoachFeedback(replyOnCoachFeedback);

		assertNotNull(insertedReplyOnCoachFeedback);
	}

	@Test
	public void testGetRepliesOnCoachFeedback() {
		Object[] replyOnCoachFeedbackObjectArr = new Object[] { 1, "Content", new Date(), 1, 1, 1, 1, 1 };
		List<Object> repliesOnCoachFeedbackObjectList = new ArrayList<>();
		repliesOnCoachFeedbackObjectList.add(replyOnCoachFeedbackObjectArr);

		when(this.replyOnCoachFeedbackRepository.getRepliesOnCoachFeedback(1, 1))
				.thenReturn(repliesOnCoachFeedbackObjectList);

		List<ReplyOnCoachFeedback> repliesOnCoachFeedback = this.replyOnCoachFeedbackService
				.getRepliesOnCoachFeedback(1, 1);

		assertEquals(1, repliesOnCoachFeedback.size());
	}

	@Test
	public void testGetRelyOnCoachFeedback() {
		when(this.replyOnCoachFeedbackRepository.findReplyOnCoachFeedbackById(1L))
				.thenReturn(this.replyOnCoachFeedback);

		ReplyOnCoachFeedback selectedReplyOnCoachFeedback = this.replyOnCoachFeedbackService.getRelyOnCoachFeedback(1L);

		assertNotNull(selectedReplyOnCoachFeedback);
	}

	@Test
	public void testUpdateReplyOnCoachFeedback() {
		when(this.replyOnCoachFeedbackRepository.saveAndFlush(this.replyOnCoachFeedback))
				.thenReturn(this.replyOnCoachFeedback);

		ReplyOnCoachFeedback updatedReplyOnCoachFeedback = this.replyOnCoachFeedbackService
				.updateReplyOnCoachFeedback(this.replyOnCoachFeedback);

		assertNotNull(updatedReplyOnCoachFeedback);
	}
}
