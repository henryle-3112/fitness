package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    String GET_POST_TAGS_BY_TAG_PAGING = "select " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_ID + ", " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + ", " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " from " + Constants.POST_TAG_TABLE + "" +
            " inner join " + Constants.POST_TABLE + " on " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " = " + Constants.POST_TABLE + "." + Constants.POST_ID + "" +
            " where (:tagId is null or " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + " = :tagId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_POST_TAGS_BY_TAG = "select " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_ID + ", " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + ", " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " from " + Constants.POST_TAG_TABLE + "" +
            " inner join " + Constants.POST_TABLE + " on " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " = " + Constants.POST_TABLE + "." + Constants.POST_ID + "" +
            " where (:tagId is null or " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + " = :tagId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)";

    String GET_NUMBER_OF_POST_TAGS_BY_TAG = "select count(*) from " + Constants.POST_TAG_TABLE + "" +
            " inner join " + Constants.POST_TABLE + " on " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " = " + Constants.POST_TABLE + "." + Constants.POST_ID + "" +
            " where (:tagId is null or " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + " = :tagId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)";

    String GET_POST_TAGS_BY_POST = "select " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_ID + ", " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + ", " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " from " + Constants.POST_TAG_TABLE + "" +
            " inner join " + Constants.TAG_TABLE + " on " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_TAG_ID + " = " + Constants.TAG_TABLE + "." + Constants.TAG_ID + "" +
            " where (:postId is null or " + Constants.POST_TAG_TABLE + "." + Constants.POST_TAG_POST_ID + " = :postId)" +
            " and (:tagStatus is null or " + Constants.TAG_TABLE + "." + Constants.TAG_STATUS + " = :tagStatus)";


    /**
     * @param tagId      - tag's id that user want to get list of posts (this parameter could be optional)
     * @param postStatus - post's status that user want to get list of posts (this parameter could be optional)
     * @param startIndex - start index (for pagination) (this parameter could be optional)
     * @return list of post tags
     */
    @Query(nativeQuery = true, value = GET_POST_TAGS_BY_TAG_PAGING)
    List<Object> getPostTagsByTagPaging(Integer tagId,
                                        Integer postStatus,
                                        Integer startIndex);

    /**
     * @param tagId      - tag's id that user want to get list of post tags (this parameter could be optional)
     * @param postStatus - post's status that user want to get list of post tags (this parameter could be optional)
     * @return list of post tags
     */
    @Query(nativeQuery = true, value = GET_POST_TAGS_BY_TAG)
    List<Object> getPostTagsByTag(Integer tagId,
                                  Integer postStatus);

    /**
     * @param postId    - post's id that user want to get list of post tags (this parameter could be optional)
     * @param tagStatus - tag's status that user want to get list of post tags (this parameter could be optional)
     * @return list of post tags
     */
    @Query(nativeQuery = true, value = GET_POST_TAGS_BY_POST)
    List<Object> getPostTagsByPost(Integer postId,
                                   Integer tagStatus);

    /**
     * @param tagId      - tag's id that user want to get number of post tags (this parameter could be optional)
     * @param postStatus - post's status that user want to get number of post tags (this parameter could be optional)
     * @return numer of post tags
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_POST_TAGS_BY_TAG)
    List<Object> getNumberOfPostTagsByTag(Integer tagId,
                                          Integer postStatus);

}
