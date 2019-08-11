package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.repository.feed.NewFeedCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFeedCommentService {
    /**
     * newFeedCommentRepository - interact with new's feed's comment
     */
    private NewFeedCommentRepository newFeedCommentRepository;

    /**
     * @param newFeedCommentRepository - inject newFeedCommentRepository
     */
    public NewFeedCommentService(NewFeedCommentRepository newFeedCommentRepository) {
        this.newFeedCommentRepository = newFeedCommentRepository;
    }

    /**
     * @param newFeed - new's feed
     * @param status  - status
     * @return list of new's feed's comment
     */
    public List<NewFeedComment> findNewFeedCommentsByNewFeedAndNewFeedCommentStatus(NewFeed newFeed, int status) {
        return this.newFeedCommentRepository.findNewFeedCommentsByNewFeedAndNewFeedCommentStatus(newFeed, status);
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @return inserted new's feed's comment
     */
    public NewFeedComment addNewFeedComment(NewFeedComment newFeedComment) {
        return this.newFeedCommentRepository.saveAndFlush(newFeedComment);
    }

    /**
     * @param id - new's feed's comment's id
     */
    public void deleteNewFeedComment(Long id) {
        this.newFeedCommentRepository.deleteById(id);
    }

    /**
     *
     * @param newFeed - new feed
     * @param status - status
     * @return number of new feed comments
     */
    public int countNewFeedCommentsByNewFeedAndNewFeedCommentStatus(NewFeed newFeed, int status) {
        return this.newFeedCommentRepository.countNewFeedCommentsByNewFeedAndNewFeedCommentStatus(newFeed, status);
    }
}
