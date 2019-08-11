package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.model.user.BodyIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyIndexRepository extends JpaRepository<BodyIndex, Long> {

    /**
     *
     * @param userId - userId that user want to get lit of body index
     * @return list of body index
     */
    @Query(nativeQuery = true, value = "select * from body_index where user_profile_id = :userId")
    List<Object> getBodyIndexById(Long userId);

    /**
     *
     * @param currentDate - current's date that user want to get body index
     * @return selected body index
     */
    BodyIndex getBodyIndexByCurrentDate(String currentDate);
}
