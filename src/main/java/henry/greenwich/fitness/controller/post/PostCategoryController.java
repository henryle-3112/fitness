package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.PostCategory;
import henry.greenwich.fitness.service.post.PostCategoryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostCategoryController {
    /**
     * postCategoryService - interact with post's category's data
     */
    private PostCategoryService postCategoryService;

    /**
     *
     * @param postCategoryService - inject postCategoryService
     */
    public PostCategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    /**
     *
     * @return list of post's categories
     */
    @GetMapping(value = "/post/categories/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostCategory> getPostCategories(@PathVariable int status) {
        return this.postCategoryService.getPostCategories(status);
    }

    /**
     *
     * @param id - post's category's that user want to get
     * @return selected post's category
     */
    @GetMapping(value = "/post/categories/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostCategory getPostCategory(@PathVariable Long id, @PathVariable int status) {
        return this.postCategoryService.getPostCategory(id, status);
    }

    /**
     *
     * @param postCategory - that user want to add to the database
     * @return postCategory - that was inserted to the database
     */
    @PostMapping(value = "/post/categories/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostCategory addPostCategory(@RequestBody PostCategory postCategory) {
        return this.postCategoryService.addPostCategory(postCategory);
    }

    /**
     *
     * @param postCategory - that user want to update to the database
     * @return postCategory - that was updated to the database
     */
    @PostMapping(value = "/post/categories/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostCategory updatePostCategory(@RequestBody PostCategory postCategory) {
        return this.postCategoryService.updatePostCategory(postCategory);
    }

    /**
     *
     * @param id - post's category's id that user want to delete
     */
    @PostMapping(value = "/post/categories/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletePostCategory(@PathVariable Long id) {
        this.postCategoryService.deletePostCategory(id);
    }
}
