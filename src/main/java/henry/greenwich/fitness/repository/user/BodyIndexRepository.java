package henry.greenwich.fitness.repository.user;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.BodyIndex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyIndexRepository extends JpaRepository<BodyIndex, Long> {

    String GET_BODY_INDEXES_BY_USER = "select * from " + Constants.BODY_INDEX_TABLE + ""
            + " where (:userProfileId is null or " + Constants.BODY_INDEX_TABLE + "."
            + Constants.BODY_INDEX_USER_PROFILE_ID + " = :userProfileId)";

    /**
     * @param userProfileId - userId that user want to get lit of body index
     * @return list of body indexes
     */
    @Query(nativeQuery = true, value = GET_BODY_INDEXES_BY_USER)
    List<Object> getBodyIndexes(@Param("userProfileId") Long userProfileId);

    /**
     * @param currentDate - current's date that user want to get body index
     * @return selected body index
     */
    BodyIndex findBodyIndexByCurrentDate(String currentDate);
}
