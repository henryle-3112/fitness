package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostSlideRepository extends JpaRepository<PostSlide, Long> {

    /**
     *
     * @param postSlideStatus - post's slide's status that user want to get post's slides
     * @return post's slides
     */
    List<PostSlide> findPostSlidesByPostSlideStatus(Integer postSlideStatus);
}
