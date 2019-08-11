package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostCategory;
import henry.greenwich.fitness.repository.post.PostCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryService {
    /**
     * postCategoryRepository - interact with post's category's data
     */
    private PostCategoryRepository postCategoryRepository;

    /**
     * @param postCategoryRepository - inject postCategoryRepository
     */
    public PostCategoryService(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
    }

    /**
     * @param status - post's category's status
     * @return list of post categories
     */
    public List<PostCategory> getPostCategories(int status) {
        return this.postCategoryRepository.findPostCategoriesByPostCategoryStatus(status);
    }

    /**
     * @param id     - post's category's id
     * @param status - post's category's status
     * @return selected post's category
     */
    public PostCategory getPostCategory(Long id, int status) {
        return this.postCategoryRepository.findPostCategoryByIdAndPostCategoryStatus(id, status);
    }

    /**
     *
     * @param id - post's category's id
     * @return selected post's category
     */
    public PostCategory getPostCategory(Long id) {
        return this.postCategoryRepository.findPostCategoryById(id);
    }

    /**
     * @param postCategory - post's category that user want to add to the database
     * @return inserted post's category
     */
    public PostCategory addPostCategory(PostCategory postCategory) {
        return this.postCategoryRepository.saveAndFlush(postCategory);
    }

    /**
     * @param postCategory - post's category that user want to update
     * @return updated post's category
     */
    public PostCategory updatePostCategory(PostCategory postCategory) {
        return this.postCategoryRepository.saveAndFlush(postCategory);
    }

    /**
     * @param id - post's category's id that user want to delete
     */
    public void deletePostCategory(Long id) {
        this.postCategoryRepository.deleteById(id);
    }
}
