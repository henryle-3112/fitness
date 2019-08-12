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

    /**
     * @param response   - response to add number of posts and number of pages to
     *                   header
     * @param categoryId - post's category's id that user want to get posts
     * @param status     - post's status that user want to get posts
     * @param page       - page (for pagination)
     * @param search     - post's title's keywords that user want to get posts
     * @return list of posts
     */
    @GetMapping(value = "/posts", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Post> getPosts(HttpServletResponse response,
                               @RequestParam(required = false) Integer categoryId,
                               @RequestParam(required = false) Integer status,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) String search,
                               @RequestParam(required = false) Integer top) {
        if (page != null) {
            return this.getPostsPaging(response, categoryId, status, page, search);
        } else if (top != null) {
            return this.postService.getTopPosts(categoryId, status, search, top);
        }
        return this.postService.getPosts(categoryId, status, search);
    }

    /**
     * @param response   - response to add number of posts and number of pages to
     *                   header
     * @param categoryId - post's category's id that user want to get posts
     * @param status     - post's status that user want to get posts
     * @param page       - page (for pagination)
     * @param search     - post's title's keywords that user want to get posts
     * @return list of posts
     */
    private List<Post> getPostsPaging(HttpServletResponse response, Integer categoryId, Integer status, Integer page,
                                      String search) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nPosts = this.postService.getNumberOfPosts(categoryId, status, search);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nPosts));
        int nPages = nPosts > 0 ? (nPosts >= Constants.NUMBER_ITEMS_PER_PAGE ? nPosts / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.postService.getPostsPaging(categoryId, status, search, startIndex - 1);
    }
}
