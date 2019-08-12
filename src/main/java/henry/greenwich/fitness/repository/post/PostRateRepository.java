package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostRate;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRateRepository extends JpaRepository<PostRate, Long> {

    /**
     * @param userProfile - user's profile that user want to get post's rate
     * @param post        - post that user want to get post's rate
     * @return selected post's rate
     */
    PostRate findPostRateByUserProfileAndPost(UserProfile userProfile, Post post);

}
