package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.model.feed.NewFeedComment;
import henry.greenwich.fitness.model.feed.ReplyOnNewFeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyOnNewFeedCommentRepository extends JpaRepository<ReplyOnNewFeedComment, Long> {

    /**
     * @param newFeedComment - new's feed's comment
     * @param status         - status
     * @return number of replies
     */
    int countReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(NewFeedComment newFeedComment, int status);

    /**
     * @param newFeedComment - new's feed's comment
     * @param status         - status
     * @return list of replies
     */
    List<ReplyOnNewFeedComment> findReplyOnNewFeedCommentsByNewFeedCommentAndReplyOnNewFeedCommentStatus(
            NewFeedComment newFeedComment,
            int status);
}
