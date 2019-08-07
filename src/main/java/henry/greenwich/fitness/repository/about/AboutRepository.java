package henry.greenwich.fitness.repository.about;

import henry.greenwich.fitness.model.about.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
    /**
     *
     * @param id - about's id that user want to get
     * @return selected about
     */
    About findAboutById(Long id);
}
