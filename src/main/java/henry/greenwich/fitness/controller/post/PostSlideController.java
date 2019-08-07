package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostSlide;
import henry.greenwich.fitness.service.post.PostSlideService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostSlideController {
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
    @GetMapping(value = "/post/slides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<PostSlide> getPostSlides(@RequestParam(required = false) Integer status) {
        return this.postSlideService.getPostSlides(status);
    }
}
