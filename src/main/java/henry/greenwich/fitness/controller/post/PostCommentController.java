package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.service.post.PostService;
import henry.greenwich.fitness.service.post.PostCommentService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostCommentController {
    /**
     * postCommentService - interact with post's comment's data
     * postService - interact with post's data
     * userProfileService - interact with user's profile's data
     */
    private PostCommentService postCommentService;
    private PostService postService;
    private UserProfileService userProfileService;

    /**
     * @param postCommentService - inject postCommentService
     * @param postService        - inject postService
     * @param userProfileService - inject userProfileService
     */
    public PostCommentController(PostCommentService postCommentService,
                                 PostService postService,
                                 UserProfileService userProfileService) {
        this.postCommentService = postCommentService;
        this.postService = postService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of post's comments
     */
    @PostMapping(value = "/post/comments/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostComment> getPostComments(@RequestBody Post post, @PathVariable int status) {
        return this.postCommentService.getPostCommentsByPostAndPostCommentStatus(post, status);
    }

    /**
     * @param postComment - that user want to add to the database
     * @return postComment - that was inserted to the database
     */
    @PostMapping(value = "/post/comments/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostComment addPostComment(@RequestBody PostComment postComment) {
        return this.postCommentService.addPostComment(postComment);
    }
}
