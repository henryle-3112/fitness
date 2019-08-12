package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostCategory;
import henry.greenwich.fitness.repository.post.PostCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryService {
    private PostCategoryRepository postCategoryRepository;

    /**
     * @param postCategoryRepository - inject postCategoryRepository
     */
    public PostCategoryService(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
    }

    /**
     * @param postCategoryStatus - post's category's status that user want to get
     *                           post's categories
     * @return list of post categories
     */
    public List<PostCategory> getPostCategories(Integer postCategoryStatus) {
        if (postCategoryStatus != null) {
            return this.postCategoryRepository.findPostCategoriesByPostCategoryStatus(postCategoryStatus);
        }
        return this.postCategoryRepository.findAll();
    }

    /**
     * @param postCategoryId - post's category's id that user want to get selected
     *                       post's category
     * @return list of post's category
     */
    public PostCategory getPostCategory(Long postCategoryId) {
        return this.postCategoryRepository.findPostCategoryById(postCategoryId);
    }
}
