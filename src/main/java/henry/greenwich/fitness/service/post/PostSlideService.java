package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostSlide;
import henry.greenwich.fitness.repository.post.PostSlideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSlideService {
    private PostSlideRepository postSlideRepository;

    /**
     * @param postSlideRepository - interact with post's slide
     */
    public PostSlideService(PostSlideRepository postSlideRepository) {
        this.postSlideRepository = postSlideRepository;
    }

    /**
     * @param postSlideStatus - post's slide's status that user want to get post's
     *                        slides
     * @return post's slides
     */
    public List<PostSlide> getPostSlides(Integer postSlideStatus) {
        if (postSlideStatus != null) {
            return postSlideRepository.findPostSlidesByPostSlideStatus(postSlideStatus);
        }
        return this.postSlideRepository.findAll();
    }
}
