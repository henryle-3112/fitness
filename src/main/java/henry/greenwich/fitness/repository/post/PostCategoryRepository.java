package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    /**
     * @param postCategoryStatus - post's category's status that user want to get post's categories
     * @return list of post's categories
     */
    List<PostCategory> findPostCategoriesByPostCategoryStatus(Integer postCategoryStatus);

    /**
     * @param id - post's category's id that user want to get post's category
     * @return selected post's category
     */
    PostCategory findPostCategoryById(Long id);
}
