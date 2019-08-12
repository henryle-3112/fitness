package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    String GET_POST_COMMENTS = "select * from " + Constants.POST_COMMENT_TABLE + " where (:postId is null or "
            + Constants.POST_COMMENT_TABLE + "." + Constants.POST_COMMENT_POST_ID + " = :postId)"
            + " and (:postCommentStatus is null or " + Constants.POST_COMMENT_TABLE + "."
            + Constants.POST_COMMENT_STATUS + " = :postCommentStatus)";

    /**
     * @param postId            - post's id that user want to get list of post's
     *                          comments
     * @param postCommentStatus - post's comment's status that user want to get list
     *                          of post's comments
     * @return list of post's comments
     */
    @Query(nativeQuery = true, value = GET_POST_COMMENTS)
    List<Object> getPostComments(@Param("postId") Integer postId, @Param("postCommentStatus") Integer postCommentStatus);

    /**
     * @param id - post's comment id that user want to get selected post's comment
     * @return selected post's comment
     */
    PostComment findPostCommentById(Long id);
}
