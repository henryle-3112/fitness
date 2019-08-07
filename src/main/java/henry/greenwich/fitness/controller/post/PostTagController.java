package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.post.*;
import henry.greenwich.fitness.service.post.PostTagService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("post-management")
public class PostTagController {
    private PostTagService postTagService;

    /**
     * @param postTagService - inject postTagService
     */
    public PostTagController(PostTagService postTagService) {
        this.postTagService = postTagService;
    }

    /**
     * @param response   - response to add number of post tags and number of pages
     *                   to header (this parameter could be optional)
     * @param tagId      - tag's id (this parameter could be optional)
     * @param page       - current's page (this parameter could be optional)
     * @param postStatus - post's status (this parameter could be optional)
     * @return list of post tag
     */
    @GetMapping(value = "/tags/{tagId}/posts", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<PostTag> getPostTagsByTag(HttpServletResponse response, @PathVariable Integer tagId,
            @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer postStatus) {
        if (page != null) {
            return this.getPostTagsByTagPaging(response, tagId, page, postStatus);
        }
        return this.postTagService.getPostTagsByTag(tagId, postStatus);
    }

    /**
     * @param response   - response to add number of post tags and number of pages
     *                   to header
     * @param tagId      - tag's id
     * @param page       - current's page
     * @param postStatus - post's status
     * @return list of post tag
     */
    private List<PostTag> getPostTagsByTagPaging(HttpServletResponse response, Integer tagId, Integer page,
            Integer postStatus) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nPostTags = this.postTagService.getNumberOfPostTagsByTag(tagId, postStatus);
        response.addHeader("X-Total-Count", String.valueOf(nPostTags));
        response.addHeader("X-Total-Page", String.valueOf(nPostTags / Constants.NUMBER_ITEMS_PER_PAGE));
        return this.postTagService.getPostTagsByTagPaging(tagId, postStatus, startIndex);
    }

    /**
     * @param postId    - post's id that user want to get post tags
     * @param tagStatus - tag's status that user want to get post tags
     * @return list of post tags
     */
    @GetMapping(value = "/posts/{postId}/tags", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<PostTag> getPostTagsByPost(@PathVariable Integer postId,
            @RequestParam(required = false) Integer tagStatus) {
        return this.postTagService.getPostTagsByPost(postId, tagStatus);
    }
}
