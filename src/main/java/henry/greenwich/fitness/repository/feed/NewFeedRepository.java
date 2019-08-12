package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.feed.NewFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewFeedRepository extends JpaRepository<NewFeed, Long> {

    String GET_NEW_FEEDS_BY_PAGE = "select * from " + Constants.NEW_FEED_TABLE + "" +
            " where (:newFeedStatus is null or " + Constants.NEW_FEED_TABLE + "." + Constants.NEW_FEED_STATUS + " = :newFeedStatus)" +
            " and (:newFeedContentKeywords is null or lower(" + Constants.NEW_FEED_TABLE + "." + Constants.NEW_FEED_CONTENT + ") like %:newFeedContentKeywords%)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NEW_FEEDS = "select * from " + Constants.NEW_FEED_TABLE + "" +
            " where (:newFeedStatus is null or " + Constants.NEW_FEED_TABLE + "." + Constants.NEW_FEED_STATUS + " = :newFeedStatus)" +
            " and (:newFeedContentKeywords is null or lower(" + Constants.NEW_FEED_TABLE + "." + Constants.NEW_FEED_CONTENT + ") like %:newFeedContentKeywords%)";

    String GET_NUMBER_OF_NEW_FEEDS = "select count(*) from " + Constants.NEW_FEED_TABLE + "" +
            " where (:newFeedStatus is null or " + Constants.NEW_FEED_TABLE + "." + Constants.NEW_FEED_STATUS + " = :newFeedStatus)" +
            " and (:newFeedContentKeywords is null or lower(" + Constants.NEW_FEED_TABLE + "." + Constants.NEW_FEED_CONTENT + ") like %:newFeedContentKeywords%)";

    /**
     * @param newFeedStatus          - newfeeds' status that user want to filter newfeeds (this parameter could be optional)
     * @param newFeedContentKeywords - newfeed's content's keywords that user want to get newfeeds (this parameter could be optional)
     * @param startIndex             - start index to get newfeeds (for pagination) (this parameter could be optional)
     * @return list of newfeeds
     */
    @Query(nativeQuery = true, value = GET_NEW_FEEDS_BY_PAGE)
    List<Object> getNewFeedsByPage(@Param("newFeedStatus") Integer newFeedStatus,
                                   @Param("newFeedContentKeywords") String newFeedContentKeywords,
                                   @Param("startIndex") Integer startIndex);

    /**
     * @param newFeedStatus          - newfeeds' status that user want to filter newfeeds (this parameter could be optional)
     * @param newFeedContentKeywords - newfeed's content's keywords that user want to get newfeeds (this parameter could be optional)
     * @return list of newfeeds
     */
    @Query(nativeQuery = true, value = GET_NEW_FEEDS)
    List<Object> getNewFeeds(@Param("newFeedStatus") Integer newFeedStatus,
                             @Param("newFeedContentKeywords") String newFeedContentKeywords);

    /**
     * @param newFeedStatus          - newfeeds' status that user want to filter newfeeds (this parameter could be optional)
     * @param newFeedContentKeywords - newfeed's content's keywords that user want to get newfeeds (this parameter could be optional)
     * @return number of newfeeds
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_NEW_FEEDS)
    List<Object> getNumberOfNewFeeds(@Param("newFeedStatus") Integer newFeedStatus,
                                     @Param("newFeedContentKeywords") String newFeedContentKeywords);

    /**
     * @param id - newfeed's id that user want to get selected newfeed
     * @return selected newfeed
     */
    NewFeed findNewFeedById(Long id);
}
