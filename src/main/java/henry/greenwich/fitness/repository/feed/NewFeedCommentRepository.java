package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import henry.greenwich.fitness.model.feed.NewFeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewFeedCommentRepository extends JpaRepository<NewFeedComment, Long> {

    /**
     *
     * @param newFeed - new's feed
     * @param status - status
     * @return list of new's feed's comments
     */
    List<NewFeedComment> findNewFeedCommentsByNewFeedAndNewFeedCommentStatus(NewFeed newFeed, int status);

    /**
     *
     * @param newFeed - new's feed
     * @param status - status
     * @return number of new feed comments
     */
    int countNewFeedCommentsByNewFeedAndNewFeedCommentStatus(NewFeed newFeed, int status);

}
