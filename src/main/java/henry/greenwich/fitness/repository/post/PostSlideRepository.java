package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostSlideRepository extends JpaRepository<PostSlide, Long> {

    /**
     * @param id     - post's slide's id
     * @param status - post's slide's status
     * @return selected post's slide
     */
    PostSlide findPostSlideByIdAndPostSlideStatus(Long id, int status);

    /**
     * @param status - post's slide's status
     * @return list of post slides
     */
    List<PostSlide> findPostSlidesByPostSlideStatus(int status);
}
