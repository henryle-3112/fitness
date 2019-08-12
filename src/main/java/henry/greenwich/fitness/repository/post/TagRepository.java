package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    /**
     * @param id - tag's id that user want to get selected tag
     * @return selected tag
     */
    Tag findTagById(Long id);
}
