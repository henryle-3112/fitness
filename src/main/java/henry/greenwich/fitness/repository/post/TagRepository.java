package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * @param id     - tag's id
     * @param status - tag's status
     * @return selected tag
     */
    Tag findTagByIdAndTagStatus(Long id, int status);

    /**
     * @param status - tag's status
     * @return list of tags
     */
    List<Tag> findTagsByTagStatus(int status);
}
