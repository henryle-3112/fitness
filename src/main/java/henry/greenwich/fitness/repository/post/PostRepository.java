package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    String GET_POSTS_PAGING = "select * from " + Constants.POST_TABLE + "" +
            " where (:postCategoryId is null or " + Constants.POST_TABLE + "." + Constants.POST_POST_CATEGORY_ID + " = :postCategoryId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)" +
            " and (:postNameKeywords is null or lower(" + Constants.POST_TABLE + "." + Constants.POST_TITLE + ") like %:postNameKeywords%)" +
            " limit :startIndex , " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NUMBER_OF_POSTS = "select count(*) from " + Constants.POST_TABLE + "" +
            " where (:postCategoryId is null or " + Constants.POST_TABLE + "." + Constants.POST_POST_CATEGORY_ID + " = :postCategoryId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)" +
            " and (:postNameKeywords is null or lower(" + Constants.POST_TABLE + "." + Constants.POST_TITLE + ") like %:postNameKeywords%)";

    String GET_TOP_POSTS = "select * from " + Constants.POST_TABLE + "" +
            " where (:postCategoryId is null or " + Constants.POST_TABLE + "." + Constants.POST_POST_CATEGORY_ID + " = :postCategoryId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)" +
            " and (:postNameKeywords is null or lower(" + Constants.POST_TABLE + "." + Constants.POST_TITLE + ") like %:postNameKeywords%)" +
            " limit :topPostLimit";

    String GET_POSTS = "select * from " + Constants.POST_TABLE + "" +
            " where (:postCategoryId is null or " + Constants.POST_TABLE + "." + Constants.POST_POST_CATEGORY_ID + " = :postCategoryId)" +
            " and (:postStatus is null or " + Constants.POST_TABLE + "." + Constants.POST_STATUS + " = :postStatus)" +
            " and (:postNameKeywords is null or lower(" + Constants.POST_TABLE + "." + Constants.POST_TITLE + ") like %:postNameKeywords%)";

    /**
     * @param postCategoryId   - post's category's id that user want to get posts (this paramter could be optional)
     * @param postStatus       - post's status that user want to get posts (this paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get posts (this paramter could be optional)
     * @param startIndex       - start index (for pagination) (this paramter could be optional)
     * @return list of posts
     */
    @Query(nativeQuery = true, value = GET_POSTS_PAGING)
    List<Object> getPostsPaging(Integer postCategoryId,
                                Integer postStatus,
                                String postNameKeywords,
                                Integer startIndex);

    /**
     * @param postCategoryId   - post's category's id that user want to get posts (this paramter could be optional)
     * @param postStatus       - post's status that user want to get posts (this paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get posts (this paramter could be optional)
     * @return list of posts
     */
    @Query(nativeQuery = true, value = GET_POSTS)
    List<Object> getPosts(Integer postCategoryId,
                          Integer postStatus,
                          String postNameKeywords);

    /**
     * @param postCategoryId   - post's category's id that user want to get number of posts (this paramter could be optional)
     * @param postStatus       - post's status that user want to get number of posts (this paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get number of posts (this paramter could be optional)
     * @return number of posts
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_POSTS)
    List<Object> getNumberOfPosts(Integer postCategoryId,
                                  Integer postStatus,
                                  String postNameKeywords);

    /**
     * @param postCategoryId   - post's category's id that user want to get posts (this paramter could be optional)
     * @param postStatus       - post's status that user want to get posts (this paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get posts (this paramter could be optional)
     * @param topPostLimit     - number of posts that user want to get posts (this parameter could be optional)
     * @return list of posts
     */
    @Query(nativeQuery = true, value = GET_TOP_POSTS)
    List<Object> getTopPosts(Integer postCategoryId,
                             Integer postStatus,
                             String postNameKeywords,
                             Integer topPostLimit);

    /**
     * @param id - post's id
     * @return selected post
     */
    Post findPostsById(Long id);
}
