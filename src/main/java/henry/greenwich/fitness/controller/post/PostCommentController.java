package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.service.post.PostCommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("post-management")
public class PostCommentController {
    private PostCommentService postCommentService;

    /**
     * @param postCommentService - inject postCommentService
     */
    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    /**
     * @param postId - post's id that user want to get post's comments
     * @param status - post's comment's status that user want to get post's comments
     * @return list of post's comments
     */
    @PostMapping(value = "/posts/{postId}/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostComment> getPostComments(@PathVariable Integer postId,
                                             @RequestParam(required = false) Integer status) {
        return this.postCommentService.getPostComments(postId, status);
    }

    /**
     * @param postComment - that user want to add to the database
     * @return postComment - inserted post's comment
     */
    @PostMapping(value = "/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostComment addPostComment(@RequestBody PostComment postComment) {
        return this.postCommentService.addPostComment(postComment);
    }
}
