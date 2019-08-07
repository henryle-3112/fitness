package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.NewFeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewFeedCommentRepository extends JpaRepository<NewFeedComment, Long> {
        String GET_NEW_FEED_COMMENTS = "select * " + Constants.NEW_FEED_COMMENT_TABLE + ""
                        + " where (:newFeedId is null or " + Constants.NEW_FEED_COMMENT_TABLE + "."
                        + Constants.NEW_FEED_COMMENT_NEW_FEED_ID + " = :newFeedId)"
                        + " and (:newFeedCommentStatus is null or " + Constants.NEW_FEED_COMMENT_TABLE + "."
                        + Constants.NEW_FEED_COMMENT_STATUS + " = %:newFeedCommentStatus%)";

        /**
         * @param newFeedId            - newfeed's id that user want to get newfeed's
         *                             comments
         * @param newFeedCommentStatus - newfeed's comment's status that user want to
         *                             filter newfeed's comments (this parameter could
         *                             be optional)
         * @return list of newfeed's comments
         */
        @Query(nativeQuery = true, value = GET_NEW_FEED_COMMENTS)
        List<Object> getNewFeedComments(Integer newFeedId, Integer newFeedCommentStatus);

        /**
         *
         * @param id - newfeed's comment's id that user want to get
         * @return selected newfeed's comment
         */
        NewFeedComment findNewFeedCommentById(Long id);

}
