package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnPostCommentRepository extends JpaRepository<ReplyOnPostComment, Long> {

    /**
     * @param postComment - post's comment
     * @param status      - reply on post's comment status
     * @return number of reply on post's comment
     */
    int countReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(PostComment postComment, int status);

    /**
     * @param postComment - post's comment
     * @param status      - status
     * @return list of reply on post's comment
     */
    List<ReplyOnPostComment> findReplyOnPostCommentsByPostCommentAndReplyOnPostCommentStatus(
            PostComment postComment,
            int status);
}
