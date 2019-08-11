package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostSlide;
import henry.greenwich.fitness.repository.post.PostSlideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSlideService {
    /**
     * postSlideRepository - interact with post's slide's data
     */
    private PostSlideRepository postSlideRepository;

    /**
     * @param postSlideRepository - interact with post's slide
     */
    public PostSlideService(PostSlideRepository postSlideRepository) {
        this.postSlideRepository = postSlideRepository;
    }

    /**
     * @return list of post's slides
     */
    public List<PostSlide> getPostSlides(int status) {
        return this.postSlideRepository.findPostSlidesByPostSlideStatus(status);
    }

    /**
     *
     * @param id - post's slide's id
     * @param status - post's slide's status
     * @return selected post's slide
     */
    public PostSlide getPostSlide(Long id, int status) {
        return this.postSlideRepository.findPostSlideByIdAndPostSlideStatus(id, status);
    }

    /**
     * @param postSlide - that user want to add to the database
     * @return postSlide that was inserted to the database
     */
    public PostSlide addPostSlide(PostSlide postSlide) {
        return this.postSlideRepository.saveAndFlush(postSlide);
    }

    /**
     * @param postSlide - that user want to update to the database
     * @return postSlide tha was inserted to the database
     */
    public PostSlide updatePostSlide(PostSlide postSlide) {
        return this.postSlideRepository.saveAndFlush(postSlide);
    }

    /**
     * @param id - post's slide's id that user want to delete
     */
    public void deletePostSlide(Long id) {
        this.postSlideRepository.deleteById(id);
    }
}
