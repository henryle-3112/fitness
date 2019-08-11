package henry.greenwich.fitness.repository.feed;

import henry.greenwich.fitness.model.feed.NewFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewFeedRepository extends JpaRepository<NewFeed, Long> {

    /**
     * @param status     - status
     * @param keyword    - keyword
     * @param startIndex - start's index
     * @return list of new's feeds
     */
    @Query(nativeQuery = true, value = "select new_feed.id, new_feed.image, new_feed.achievement, new_feed.achievement_time, new_feed.content, new_feed.user_profile_id, new_feed.created_date, new_feed.status from new_feed inner join user_profile on new_feed.user_profile_id = user_profile.id where new_feed.status = :status and lower(user_profile.full_name) like :keyword limit :startIndex, 8")
    List<Object> findNewFeedsByStatusAndUserProfileAndPage(int status, String keyword, int startIndex);

    /**
     * @param status  - status
     * @param keyword - keyword
     * @return number of new's feeds
     */
    @Query(nativeQuery = true, value = "select count(*) from new_feed inner join user_profile on new_feed.user_profile_id = user_profile.id where new_feed.status = :status and lower(user_profile.full_name) like :keyword")
    List<Object> countNewFeedsByStatusAndUserProfileAndPage(int status, String keyword);
}
