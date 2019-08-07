package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostRate;
import henry.greenwich.fitness.service.post.PostRateService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("post-management")
public class PostRateController {
    private PostRateService postRateService;

    /**
     * @param postRateService - inject postRateService
     */
    public PostRateController(PostRateService postRateService) {
        this.postRateService = postRateService;
    }

    /**
     * @param postRate - that user want to add to the database
     * @return inserted post's rate
     */
    @PostMapping(value = "/rates", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PostRate addPostRate(@RequestBody PostRate postRate) {
        return this.postRateService.addPostRate(postRate);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get post's rate
     * @param postId        - post's id that user want to get post's rate
     * @return selected post's rate
     */
    @GetMapping(value = "/users/{userProfileId}/posts/{postId}/rates", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PostRate getPostRate(@PathVariable Long userProfileId, @PathVariable Long postId) {
        return this.postRateService.getPostRateByUserProfileIsAndPostId(userProfileId, postId);
    }

}
