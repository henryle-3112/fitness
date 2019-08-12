package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReplyOnNewFeedCommentRepository extends JpaRepository<ReplyOnNewFeedComment, Long> {

    String GET_REPLIES_ON_NEW_FEED_COMMENT = "select * from " + Constants.REPLY_ON_NEW_FEED_COMMENT_TABLE + ""
            + " where (:newFeedCommentId is null or " + Constants.REPLY_ON_NEW_FEED_COMMENT_TABLE + "."
            + Constants.REPLY_ON_NEW_FEED_COMMENT_NEW_FEED_COMMENT_ID + " = :newFeedCommentId)"
            + " and (:replyOnNewFeedCommentStatus is null or " + Constants.REPLY_ON_NEW_FEED_COMMENT_TABLE
            + "." + Constants.REPLY_ON_NEW_FEED_COMMENT_STATUS + " = :replyOnNewFeedCommentStatus)";

    /**
     * @param newFeedCommentId            - newfeed's comment's id that user want to
     *                                    get replies
     * @param replyOnNewFeedCommentStatus - status of reply on newfeed's comment
     *                                    that user want to get replies (this
     *                                    parameter could be optional)
     * @return list of replies
     */
    @Query(nativeQuery = true, value = GET_REPLIES_ON_NEW_FEED_COMMENT)
    List<Object> getRepliesOnNewFeedComment(@RequestParam("newFeedCommentId") Integer newFeedCommentId,
                                            @RequestParam("replyOnNewFeedCommentStatus") Integer replyOnNewFeedCommentStatus);

    /**
     * @param id - id of reply on newfeed's comment that user want to get
     * @return selected reply on newfeed's comment
     */
    ReplyOnNewFeedComment findReplyOnNewFeedCommentById(Long id);
}
