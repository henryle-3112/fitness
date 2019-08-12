package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostCategory;
import henry.greenwich.fitness.service.post.PostCategoryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("post-management")
public class PostCategoryController {
    private PostCategoryService postCategoryService;

    /**
     * @param postCategoryService - inject postCategoryService
     */
    public PostCategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    /**
     * @param status - post's category's status that user want to add to the
     *               database
     * @return list of post's categories
     */
    @GetMapping(value = "/categories", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostCategory> getPostCategories(@RequestParam(required = false) Integer status) {
        return this.postCategoryService.getPostCategories(status);
    }
}
