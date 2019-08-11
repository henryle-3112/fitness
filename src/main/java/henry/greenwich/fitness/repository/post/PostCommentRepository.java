package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    /**
     * @param post   - post's status
     * @param status - status
     * @return list of post comments
     */
    List<PostComment> findPostCommentsByPostAndPostCommentStatus(Post post, int status);
}
