package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     *
     * @param id - post's id
     * @param status - post's status
     * @return selected post
     */
    Post findPostByIdAndPostStatus(Long id, int status);

    /**
     *
     * @param status - post's status
     * @return list of posts
     */
    List<Post> findPostsByPostStatus(int status);

    /**
     *
     * @param categoryId - post's category's id
     * @param top - number of posts that user want to get
     * @param status - post's status
     * @return list of posts
     */
    @Query(nativeQuery = true, value = "select * from post where status = :status and post_category_id = :categoryId limit :top")
    List<Object> findTopPostsByCategoryAndPostStatus(int categoryId, int top, int status);

    /**
     * @param categoryId - post's category's id
     * @param startIndex - start index
     * @param status     - status
     * @return list of posts
     */
    @Query(nativeQuery = true, value = "select * from post where post_category_id = :categoryId and status = :status limit :startIndex, 8")
    List<Object> findPostsByCategoryAndByPage(int categoryId, int startIndex, int status);

    /**
     * @param categoryId - post's category's id
     * @param status     - post's status
     * @return number of posts
     */
    @Query(nativeQuery = true, value = "select count(*) from post where post_category_id = :categoryId and status = :status")
    List<Object> countPostsByCategoryAndByPostStatus(int categoryId, int status);

    /**
     *
     * @param categoryId - post's category's id
     * @param postNameKeywords - post's name keywords
     * @param status - status
     * @return number of posts
     */
    @Query(nativeQuery = true, value = "select count(*) from post where (:categoryId is null or post_category_id = :categoryId) and status = :status and (:postNameKeywords is null or title like :postNameKeywords)")
    List<Object> countSearchingPosts(@Param("categoryId") Integer categoryId,
                                        @Param("postNameKeywords") String postNameKeywords,
                                        @Param("status") int status);

    /**
     *
     * @param selectedCategoryId - post's category's id
     * @param selectedPostNameKeyword - product's name keywords
     * @param startIndex - start index
     * @param status - status
     * @return list of post
     */
    @Query(nativeQuery = true, value = "select * from post where (:selectedCategoryId is null or post_category_id = :selectedCategoryId) and status = :status and (:selectedPostNameKeyword is null or title like :selectedPostNameKeyword) limit :startIndex, 8")
    List<Object> findSearchingPostsByPage(@Param("selectedCategoryId") Integer selectedCategoryId,
                                             @Param("selectedPostNameKeyword") String selectedPostNameKeyword,
                                             @Param("startIndex") int startIndex,
                                             @Param("status") int status);

    /**
     *
     * @param id - post's id
     * @return selected post
     */
    Post findPostsById(Long id);
}
