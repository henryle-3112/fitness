package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.ReplyOnPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnPostCommentRepository extends JpaRepository<ReplyOnPostComment, Long> {

        String GET_REPLIES_ON_POST_COMMENT = "select * " + Constants.REPLY_ON_POST_COMMENT_TABLE + ""
                        + " where (:postCommentId is null or " + Constants.REPLY_ON_POST_COMMENT_TABLE + "."
                        + Constants.REPLY_ON_POST_COMMENT_POST_COMMENT_ID + " = :postCommentId)"
                        + " and (:replyOnPostCommentStatus is null or " + Constants.REPLY_ON_POST_COMMENT_TABLE + "."
                        + Constants.REPLY_ON_POST_COMMENT_STATUS + " = :replyOnPostCommentStatus)";

        /**
         * @param postCommentId            - post's comment's id that user want to get
         *                                 replies
         * @param replyOnPostCommentStatus - reply on post's comment's status that user
         *                                 want to get replies
         * @return list of replies
         */
        @Query(nativeQuery = true, value = GET_REPLIES_ON_POST_COMMENT)
        List<Object> getRepliesOnPostComment(Integer postCommentId, Integer replyOnPostCommentStatus);

        /**
         * @param id - reply on post's comment's id that user want to get selected reply
         *           on post's comment
         * @return selected reply on post's comment
         */
        ReplyOnPostComment findReplyOnPostCommentsById(Long id);
}
