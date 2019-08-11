package henry.greenwich.fitness.service.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.repository.feed.ReplyOnNewFeedCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnNewFeedCommentService {
    /**
     * replyOnNewFeedCommentRepository - interact with reply feed comment data
     */
    private ReplyOnNewFeedCommentRepository replyOnNewFeedCommentRepository;

    /**
     * @param replyOnNewFeedCommentRepository - inject replyOnNewFeedCommentRepository
     */
    public ReplyOnNewFeedCommentService(ReplyOnNewFeedCommentRepository replyOnNewFeedCommentRepository) {
        this.replyOnNewFeedCommentRepository = replyOnNewFeedCommentRepository;
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @param status         - status
     * @return number of replies
     */
    public int countReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
            NewFeedComment newFeedComment, int status) {
        return this.replyOnNewFeedCommentRepository.countReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
                newFeedComment, status);
    }

    /**
     * @param newFeedComment - new's feed's comment
     * @param status         - status
     * @return list of replies
     */
    public List<ReplyOnNewFeedComment> findReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
            NewFeedComment newFeedComment, int status) {
        return this.replyOnNewFeedCommentRepository.findReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
                newFeedComment, status);
    }

    /**
     * @param replyOnNewFeedComment - reply's on new's feed's comment
     * @return inserted replyOnNewFeedComment
     */
    public ReplyOnNewFeedComment addReplyOnNewFeedComment(ReplyOnNewFeedComment replyOnNewFeedComment) {
        return this.replyOnNewFeedCommentRepository.saveAndFlush(replyOnNewFeedComment);
    }
}
