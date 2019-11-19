package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.feed.NewFeedCommentRepository;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewFeedCommentService {
	private NewFeedCommentRepository newFeedCommentRepository;
	private NewFeedService newFeedService;
	private UserProfileService userProfileService;

	/**
	 * @param newFeedCommentRepository - inject newFeedCommentRepository
	 * @param newFeedService           - inject newFeedService
	 * @param userProfileService       - inject userProfileService
	 */
	public NewFeedCommentService(NewFeedCommentRepository newFeedCommentRepository, NewFeedService newFeedService,
			UserProfileService userProfileService) {
		this.newFeedCommentRepository = newFeedCommentRepository;
		this.newFeedService = newFeedService;
		this.userProfileService = userProfileService;
	}

	/**
	 * @param newFeedComment - newfeed's comment that user want to add to the
	 *                       database
	 * @return inserted newfeed's comment
	 */
	public NewFeedComment addNewFeedComment(NewFeedComment newFeedComment) {
		return this.newFeedCommentRepository.saveAndFlush(newFeedComment);
	}

	/**
	 * @param newFeedId            - newfeed's id that user want to get newfeed's
	 *                             comments
	 * @param newFeedCommentStatus - newfeed's comment's status that user want to
	 *                             filter newfeed's comment (this parameter could be
	 *                             optional)
	 * @return list of newfeed's comments
	 */
	public List<NewFeedComment> getNewFeedComments(Integer newFeedId, Integer newFeedCommentStatus) {
		List<Object> newFeedCommentsObjectList = this.newFeedCommentRepository.getNewFeedComments(newFeedId,
				newFeedCommentStatus);
		return this.getNewFeedCommentsFromObjectList(newFeedCommentsObjectList);
	}

	/**
	 * @param newFeedCommentsObjectList - newfeed's comments object list that user
	 *                                  want to convert to newfeed's comments list
	 * @return list of newfeed's comments
	 */
	private List<NewFeedComment> getNewFeedCommentsFromObjectList(List<Object> newFeedCommentsObjectList) {
		List<NewFeedComment> newFeedComments = new ArrayList<>();
		for (Object o : newFeedCommentsObjectList) {
			Object[] newFeedCommentObjectArr = (Object[]) o;
			NewFeedComment newFeedComment = this.createNewFeedCommentFromObjectArr(newFeedCommentObjectArr);
			newFeedComments.add(newFeedComment);
		}
		return newFeedComments;
	}

	/**
	 * @param newFeedCommentObjectArr - newfeed's comment object array that user
	 *                                want to convert to newfeed's comment object
	 * @return converted newfeed's comment
	 */
	private NewFeedComment createNewFeedCommentFromObjectArr(Object[] newFeedCommentObjectArr) {
		int newFeedCommentId = (int) newFeedCommentObjectArr[0];
		String newFeedCommentContent = (String) newFeedCommentObjectArr[1];
		Date newFeedCommentCreatedDate = (Date) newFeedCommentObjectArr[2];
		int newFeedCommentStatus = (int) newFeedCommentObjectArr[3];
		int userProfileId = (int) newFeedCommentObjectArr[4];
		UserProfile userProfile = this.getUserProfile((long) userProfileId);
		int newFeedId = (int) newFeedCommentObjectArr[5];
		NewFeed newFeed = this.getNewFeed((long) newFeedId);
		int nNewFeedCommentLikes = (int) newFeedCommentObjectArr[6];
		int nNewFeedCommentDislikes = (int) newFeedCommentObjectArr[7];
		int nRepliesOnNewFeedComment = (int) newFeedCommentObjectArr[8];
		return new NewFeedComment((long) newFeedCommentId, newFeedCommentContent, newFeedCommentCreatedDate, newFeed,
				userProfile, newFeedCommentStatus, nNewFeedCommentLikes, nNewFeedCommentDislikes,
				nRepliesOnNewFeedComment);
	}

	/**
	 * @param userProfileId - user's profile's id that user want to get user's
	 *                      profile
	 * @return selected user's profile
	 */
	private UserProfile getUserProfile(Long userProfileId) {
		return this.userProfileService.getUserProfile(userProfileId);
	}

	/**
	 * @param newFeedId - newfeed's id that user want to get selected newfeed
	 * @return selected newfeed
	 */
	private NewFeed getNewFeed(Long newFeedId) {
		return this.newFeedService.getNewFeedById(newFeedId);
	}

	/**
	 * @param id - newfeed's comment's id that user want to get
	 * @return selected newfeed's comment
	 */
	public NewFeedComment getNewFeedComment(Long id) {
		return this.newFeedCommentRepository.findNewFeedCommentById(id);
	}

	/**
	 * @param newFeedComment - newfeed's comment that user want to update
	 * @return updated newfeed's comment
	 */
	public NewFeedComment updateNewFeedComment(NewFeedComment newFeedComment) {
		return this.newFeedCommentRepository.saveAndFlush(newFeedComment);
	}
}
