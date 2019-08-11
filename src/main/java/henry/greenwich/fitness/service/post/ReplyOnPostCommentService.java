package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import henry.greenwich.fitness.repository.post.ReplyOnPostCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyOnPostCommentService {
    /**
     * replyOnPostCommentRepository - interact with reply on post comment data
     */
    private ReplyOnPostCommentRepository replyOnPostCommentRepository;

    /**
     *
     * @param replyOnPostCommentRepository - inject replyOnPostCommentRepository
     */
    public ReplyOnPostCommentService(ReplyOnPostCommentRepository replyOnPostCommentRepository) {
        this.replyOnPostCommentRepository = replyOnPostCommentRepository;
    }

    /**
     * @param postComment - post's comment that user want to count number of reply
     */
    public int countNumberOfReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(PostComment postComment, int status) {
        return this.replyOnPostCommentRepository.countReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(postComment, status);
    }

    /**
     * @param postComment - post's comment that user want to view replies
     * @return list of replies
     */
    public List<ReplyOnPostComment> getReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(PostComment postComment, int status) {
        return this.replyOnPostCommentRepository.findReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(postComment, status);
    }

    /**
     * @param replyOnPostComment - reply that user want to add to the database
     * @return reply that was inserted to the database
     */
    public ReplyOnPostComment addReplyOnPostComment(ReplyOnPostComment replyOnPostComment) {
        return this.replyOnPostCommentRepository.saveAndFlush(replyOnPostComment);
    }


}
