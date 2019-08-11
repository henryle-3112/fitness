package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostRate;
import henry.greenwich.fitness.model.product.ProductRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRateRepository extends JpaRepository<PostRate, Long> {
    /**
     * @param id - post's rate's id
     * @return selected post's rate
     */
    PostRate findPostRateById(Long id);


    @Query(nativeQuery = true, value = "select * from post_rate where user_profile_id = :userId and post_id = :postId")
    List<Object> getPostRateByUserIdAndByPostId(Long userId, Long postId);
}
