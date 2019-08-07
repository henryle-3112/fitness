package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.service.post.PostService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("post-management")
public class PostController {
    private PostService postService;

    /**
     * postService - inject postService
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Post> getPosts(HttpServletResponse response, @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer status, @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String search, @RequestParam(required = false) Integer top) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nPosts = this.postService.getNumberOfPosts(categoryId, status, search);
            response.addHeader("X-Total-Count", String.valueOf(nPosts));
            response.addHeader("X-Total-Page", String.valueOf(nPosts / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.postService.getPostsPaging(categoryId, status, search, startIndex);
        } else if (top != null) {
            return this.postService.getTopPosts(categoryId, status, search, top);
        }
        return this.postService.getPosts(categoryId, status, search);
    }
}
