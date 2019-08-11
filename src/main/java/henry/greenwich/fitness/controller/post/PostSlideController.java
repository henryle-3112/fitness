package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostSlide;
import henry.greenwich.fitness.service.post.PostSlideService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostSlideController {
    /**
     * postSlideService - interact with post's slide's data
     */
    private PostSlideService postSlideService;

    /**
     * @param postSlideService - inject postSlideService
     */
    public PostSlideController(PostSlideService postSlideService) {
        this.postSlideService = postSlideService;
    }

    /**
     * @return list of post's slide
     */
    @GetMapping(value = "/post/slides/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostSlide> getPostSlides(@PathVariable int status) {
        return this.postSlideService.getPostSlides(status);
    }

    /**
     * @param id - post's slide's id that user want to get
     * @return selected post's slide
     */
    @GetMapping(value = "/post/slides/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostSlide getPostSlide(@PathVariable Long id, @PathVariable int status) {
        return this.postSlideService.getPostSlide(id, status);
    }

    /**
     * @param postSlide - that user want to add to the database
     * @return postSlide - that was inserted to the database
     */
    @PostMapping(value = "/post/slides/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostSlide addPostSlide(@RequestBody PostSlide postSlide) {
        return this.postSlideService.addPostSlide(postSlide);
    }

    /**
     * @param postSlide - that user want to update to the database
     * @return postSlide - that was updated to the database
     */
    @PostMapping(value = "/post/slides/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostSlide updatePostSlide(@RequestBody PostSlide postSlide) {
        return this.postSlideService.updatePostSlide(postSlide);
    }

    /**
     * @param id - post's slide's id that user want to delete
     */
    @PostMapping(value = "/post/slides/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletePostSlide(@PathVariable Long id) {
        this.postSlideService.deletePostSlide(id);
    }
}
