package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostRate;
import henry.greenwich.fitness.service.post.PostService;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.post.PostRateService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostRateController {
    /**
     * postRateService - interact with post's rate's data
     * postService - interact with post's data
     * userProfileService - interact with user's profile's data
     */
    private PostRateService postRateService;
    private PostService postService;
    private UserProfileService userProfileService;

    /**
     * @param postRateService - inject postRateService
     * @param postService - inject postService
     * @param userProfileService - inject userProfileService
     */
    public PostRateController(PostRateService postRateService,
                              PostService postService,
                              UserProfileService userProfileService) {
        this.postRateService = postRateService;
        this.postService = postService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of post's rate
     */
    @GetMapping(value = "/post/rates", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostRate> getPostRates() {
        return this.postRateService.getPostRates();
    }

    /**
     * @param id - post's rate's id that user want to get
     * @return selected post's rate
     */
    @GetMapping(value = "/post/rates/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostRate getPostRate(@PathVariable Long id) {
        return this.postRateService.getPostRate(id);
    }

    /**
     * @param postRate - that user want to add to the database
     * @return postRate - that was inserted to the database
     */
    @PostMapping(value = "/post/rates/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostRate addPostRate(@RequestBody PostRate postRate) {
        return this.postRateService.addPostRate(postRate);
    }

    /**
     * @param postRate - that user want to update to the database
     * @return postRate - that was updated to the database
     */
    @PostMapping(value = "/post/rates/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostRate updatePostRate(@RequestBody PostRate postRate) {
        return this.postRateService.updatePostRate(postRate);
    }

    /**
     * @param id - post's rate's id that user want to delete
     */
    @PostMapping(value = "/post/rates/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletePostRate(@PathVariable Long id) {
        this.postRateService.deletePostRate(id);
    }


    @GetMapping(value = "/post/rates/{userId}/{postId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostRate getPostRate(@PathVariable Long userId, @PathVariable Long postId) {
        PostRate postRate = new PostRate();
        List<Object> postRateObjectList = this.postRateService.getPostRateByUserIdAndPostId(userId, postId);
        if (!postRateObjectList.isEmpty()) {
            Object[] postRateObjectArray = (Object[]) postRateObjectList.get(0);
            int postRateId = (int) postRateObjectArray[0];
            int postRateValue = (int) postRateObjectArray[1];
            Post post = this.postService.getPost(postId);
            UserProfile userProfile = this.userProfileService.getUserProfile(userId);
            postRate = new PostRate(
                    (long) postRateId,
                    postRateValue,
                    post,
                    userProfile
            );
            return postRate;
        }
        return postRate;
    }


}
