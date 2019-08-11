package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.model.post.PostCommentReaction;
import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.product.ProductFeedbackReaction;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.post.PostCommentReactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostCommentReactionController {
    /**
     *
     */
    private PostCommentReactionService postCommentReactionService;

    public PostCommentReactionController(PostCommentReactionService postCommentReactionService) {
        this.postCommentReactionService = postCommentReactionService;
    }

    /**
     * @param postCommentReaction - that user want to add to the database
     * @return postCommentReaction - that was inserted to the database
     */
    @PostMapping(value = "/post/comment/reactions/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostCommentReaction addPostCommentReaction(@RequestBody PostCommentReaction postCommentReaction) {
        return this.postCommentReactionService.addPostCommentReaction(postCommentReaction);
    }

    /**
     *
     * @param postComment - post's comment
     * @param reaction - reaction value
     * @return number of reactions
     */
    @PostMapping(value = "/post/comment/reactions/count/{reaction}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countNumberOfPostCommentReactionsByPostCommentAndReaction(
            @RequestBody PostComment postComment,
            @PathVariable int reaction) {
        int nReactions = this.postCommentReactionService.countNumberOfPostCommentReactionsByPostCommentAndReaction(postComment, reaction);
        return new ResponseMessage(String.valueOf(nReactions));
    }

    /**
     * @param userProfile - user's profile
     * @return list of reactions
     */
    @PostMapping(value = "/post/comment/reactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostCommentReaction> getPostCommentReactionsByUserProfile(
            @RequestBody UserProfile userProfile) {
        return this.postCommentReactionService.getPostCommentReactionsByUserProfile(userProfile);
    }

}
