package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

        String GET_COACHES_BY_PAGE = "select " + Constants.COACH_TABLE + "." + Constants.COACH_ID + ", "
                        + Constants.COACH_TABLE + "." + Constants.COACH_USER_PROFILE_ID + ", " + Constants.COACH_TABLE
                        + "." + Constants.COACH_ABOUT + ", " + Constants.COACH_TABLE + "." + Constants.COACH_STATUS
                        + ", " + Constants.COACH_TABLE + "." + Constants.COACH_RATING_AVERAGE + ", "
                        + Constants.COACH_TABLE + "." + Constants.COACH_NUMBER_OF_MEMBERSHIPS + "" + " from "
                        + Constants.COACH_TABLE + " inner join " + Constants.USER_PROFILE_TABLE + "" + " on "
                        + Constants.COACH_TABLE + "." + Constants.COACH_USER_PROFILE_ID + " = "
                        + Constants.USER_PROFILE_TABLE + "." + Constants.USER_PROFILE_ID + ""
                        + " where (:coachStatus is null or " + Constants.COACH_TABLE + "." + Constants.COACH_STATUS
                        + " = :coachStatus)" + " and (:coachFullNameKeywords is null or lower("
                        + Constants.USER_PROFILE_TABLE + "." + Constants.USER_PROFILE_FULL_NAME
                        + ") like %:coachFullNameKeywords%)" + " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

        String GET_COACHES_BY_PAGE_AND_IDS_IN = "select " + Constants.COACH_TABLE + "." + Constants.COACH_ID + ", "
                        + Constants.COACH_TABLE + "." + Constants.COACH_USER_PROFILE_ID + ", " + Constants.COACH_TABLE
                        + "." + Constants.COACH_ABOUT + ", " + Constants.COACH_TABLE + "." + Constants.COACH_STATUS
                        + ", " + Constants.COACH_TABLE + "." + Constants.COACH_RATING_AVERAGE + ", "
                        + Constants.COACH_TABLE + "." + Constants.COACH_NUMBER_OF_MEMBERSHIPS + "" + " from "
                        + Constants.COACH_TABLE + " inner join " + Constants.USER_PROFILE_TABLE + "" + " on "
                        + Constants.COACH_TABLE + "." + Constants.COACH_USER_PROFILE_ID + " = "
                        + Constants.USER_PROFILE_TABLE + "." + Constants.USER_PROFILE_ID + ""
                        + " where (:coachStatus is null or " + Constants.COACH_TABLE + "." + Constants.COACH_STATUS
                        + " = :coachStatus) and (:coachIds is null or coach.id in :coachIds)"
                        + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%)" + " limit :startIndex, "
                        + Constants.NUMBER_ITEMS_PER_PAGE;

        String GET_NUMBER_OF_COACHES_BY_IDS_IN = "select count(*) from " + Constants.COACH_TABLE + " inner join "
                        + Constants.USER_PROFILE_TABLE + "" + " on " + Constants.COACH_TABLE + "."
                        + Constants.COACH_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_ID + "" + " where (:coachStatus is null or " + Constants.COACH_TABLE
                        + "." + Constants.COACH_STATUS
                        + " = :coachStatus) and (:coachIds is null or coach.id in :coachIds)"
                        + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%)";

        String GET_COACHES = "select " + Constants.COACH_TABLE + "." + Constants.COACH_ID + ", " + Constants.COACH_TABLE
                        + "." + Constants.COACH_USER_PROFILE_ID + ", " + Constants.COACH_TABLE + "."
                        + Constants.COACH_ABOUT + ", " + Constants.COACH_TABLE + "." + Constants.COACH_STATUS + ", "
                        + Constants.COACH_TABLE + "." + Constants.COACH_RATING_AVERAGE + ", " + Constants.COACH_TABLE
                        + "." + Constants.COACH_NUMBER_OF_MEMBERSHIPS + "" + " from " + Constants.COACH_TABLE
                        + " inner join " + Constants.USER_PROFILE_TABLE + "" + " on " + Constants.COACH_TABLE + "."
                        + Constants.COACH_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_ID + "" + " where (:coachStatus is null or " + Constants.COACH_TABLE
                        + "." + Constants.COACH_STATUS + " = :coachStatus)"
                        + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%)";

        String GET_NUMBER_OF_COACHES = "select count(*) from " + Constants.COACH_TABLE + " inner join "
                        + Constants.USER_PROFILE_TABLE + "" + " on " + Constants.COACH_TABLE + "."
                        + Constants.COACH_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_ID + "" + " where (:coachStatus is null or " + Constants.COACH_TABLE
                        + "." + Constants.COACH_STATUS + " = :coachStatus)"
                        + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
                        + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%)";

        String GET_COACH = "select * from " + Constants.COACH_TABLE + "" + " where (:coachId is null or "
                        + Constants.COACH_TABLE + "." + Constants.COACH_ID + " = :coachId)"
                        + " and (:coachStatus is null or " + Constants.COACH_TABLE + "." + Constants.COACH_STATUS
                        + " = :coachStatus)";

        String GET_COACH_BY_USER_PROFILE_ID = "select * from " + Constants.COACH_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.COACH_TABLE + "."
                        + Constants.COACH_USER_PROFILE_ID + " = :userProfileId)" + " and (:coachStatus is null or "
                        + Constants.COACH_TABLE + "." + Constants.COACH_STATUS + " = :coachStatus)";

        /**
         * @param coachStatus           - coach's status that user want to get coaches
         *                              (this parameter could be optional)
         * @param coachFullNameKeywords - coach's full name that user want to get
         *                              coaches (this parameter could be optional)
         * @param startIndex            - start index (for pagination) (this parameter
         *                              could be optional)
         * @return list of coaches
         */
        @Query(nativeQuery = true, value = GET_COACHES_BY_PAGE)
        List<Object> getCoachesByPage(@Param("coachStatus") Integer coachStatus,
                        @Param("coachFullNameKeywords") String coachFullNameKeywords,
                        @Param("startIndex") Integer startIndex);

        /**
         * 
         * @param coachStatus           - coach's status that user want to get coaches
         *                              (this parameter could be optional)
         * @param coachIds              - coach ids that user want to get coaches (this
         *                              parameter could be optional)
         * @param coachFullNameKeywords - coach's full name that user want to get
         *                              coaches (this parameter could be optional)
         * @param startIndex            - start index (for pagination) (this parameter
         *                              could be optional)
         * @return list of coaches
         */
        @Query(nativeQuery = true, value = GET_COACHES_BY_PAGE_AND_IDS_IN)
        List<Object> getCoachesByPageAndIdsIn(@Param("coachStatus") Integer coachStatus,
                        @Param("coachIds") List<Long> coachIds,
                        @Param("coachFullNameKeywords") String coachFullNameKeywords,
                        @Param("startIndex") Integer startIndex);

        /**
         * @param coachStatus           - coach's status that user want to get coaches
         *                              (this parameter could be optional)
         * @param coachFullNameKeywords - coach's full name that user want to get
         *                              coaches (this parameter could be optional)
         * @return list of coaches
         */
        @Query(nativeQuery = true, value = GET_COACHES)
        List<Object> getCoaches(@Param("coachStatus") Integer coachStatus,
                        @Param("coachFullNameKeywords") String coachFullNameKeywords);

        /**
         * @param coachStatus           - coach's status that user want to get (this
         *                              parameter could be optional)
         * @param coachFullNameKeywords - coach's full name that user want to get (this
         *                              parameter could be optional)
         * @return number of coaches
         */
        @Query(nativeQuery = true, value = GET_NUMBER_OF_COACHES)
        List<Object> getNumberOfCoaches(@Param("coachStatus") Integer coachStatus,
                        @Param("coachFullNameKeywords") String coachFullNameKeywords);

        /**
         * @param coachStatus           - coach's status that user want to get (this
         *                              parameter could be optional)
         * @param coachIds              - coach's ids that user want to get (this
         *                              parameter could be optional)
         * @param coachFullNameKeywords - coach's full name that user want to get (this
         *                              parameter could be optional)
         * @return number of coaches
         */
        @Query(nativeQuery = true, value = GET_NUMBER_OF_COACHES_BY_IDS_IN)
        List<Object> getNumberOfCoachesByIdsIn(@Param("coachStatus") Integer coachStatus,
                        @Param("coachIds") List<Long> coachIds,
                        @Param("coachFullNameKeywords") String coachFullNameKeywords);

        /**
         * @param coachId     - coach's id that user want to get
         * @param coachStatus - coach's status that user want to get (this parameter
         *                    could be optional)
         * @return selected coach
         */
        @Query(nativeQuery = true, value = GET_COACH)
        List<Object> getCoach(@Param("coachId") Integer coachId, @Param("coachStatus") Integer coachStatus);

        /**
         * @param userProfileId - user's profile's id that user want to get coach
         * @param coachStatus   - coach's status that user want to get coach (this
         *                      parameter could be optional)
         * @return selected coach
         */
        @Query(nativeQuery = true, value = GET_COACH_BY_USER_PROFILE_ID)
        List<Object> getCoachByUser(@Param("userProfileId") Integer userProfileId,
                        @Param("coachStatus") Integer coachStatus);

}
