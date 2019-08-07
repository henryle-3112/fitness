package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostCommentReaction;
import henry.greenwich.fitness.service.post.PostCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("post-management")
public class PostCommentReactionController {
    private PostCommentReactionService postCommentReactionService;

    /**
     * @param postCommentReactionService - inject postCommentReactionService
     */
    public PostCommentReactionController(PostCommentReactionService postCommentReactionService) {
        this.postCommentReactionService = postCommentReactionService;
    }

    /**
     * @param postCommentReaction - that user want to add to the database
     * @return inserted post's comment
     */
    @PostMapping(value = "/comment-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PostCommentReaction addPostCommentReaction(@RequestBody PostCommentReaction postCommentReaction) {
        return this.postCommentReactionService.addPostCommentReaction(postCommentReaction);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get list of
     *                      post's comment's reactions
     * @return list of post's comment's reactions
     */
    @GetMapping(value = "/users/{userProfileId}/comment-reactions", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<PostCommentReaction> getPostCommentReactionsByUserProfile(@PathVariable Integer userProfileId) {
        return this.postCommentReactionService.getPostCommentReactions(userProfileId);
    }

}
