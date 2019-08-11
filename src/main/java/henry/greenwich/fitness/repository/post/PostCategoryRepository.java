package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    /**
     *
     * @param id - post's category's id
     * @param status - post's category's status
     * @return selected post's category
     */
    PostCategory findPostCategoryByIdAndPostCategoryStatus(Long id, int status);

    /**
     *
     * @param status - post's category's status
     * @return list of post categories
     */
    List<PostCategory> findPostCategoriesByPostCategoryStatus(int status);

    /**
     *
     * @param id - post's category's id
     * @return selected post's category
     */
    PostCategory findPostCategoryById(Long id);
}
