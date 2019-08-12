package henry.greenwich.fitness.repository.membership;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.membership.Membership;
import henry.greenwich.fitness.model.user.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    String GET_MEMBERSHIPS_BY_PAGE = "select" + " " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_COACH_ID + "," + " " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_USER_PROFILE_ID + "," + " " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_ID + "," + " " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_STATUS + "," + " " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_START_DATE + " from " + Constants.MEMBERSHIP_TABLE + "" + " inner join "
            + Constants.USER_PROFILE_TABLE + " on " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_ID + "" + " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE
            + "." + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" + " and (:membershipStatus is null or "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_STATUS + " = :membershipStatus)"
            + " and (:userProfileFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_FULL_NAME + ") like %:userProfileFullNameKeywords%)"
            + " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NUMBER_OF_MEMBERSHIPS_PAGING = "select count(*) from " + Constants.MEMBERSHIP_TABLE + ""
            + " inner join " + Constants.USER_PROFILE_TABLE + " on " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_ID + "" + " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE
            + "." + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" + " and (:membershipStatus is null or "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_STATUS + " = :membershipStatus)"
            + " and (:userProfileFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_FULL_NAME + ") like %:userProfileFullNameKeywords%)";

    String GET_MEMBERSHIPS = "select" + " " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_COACH_ID + ","
            + " " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + "," + " "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_ID + "," + " "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_STATUS + "," + " "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_START_DATE + " from "
            + Constants.MEMBERSHIP_TABLE + "" + " inner join " + Constants.USER_PROFILE_TABLE + " on "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + " = "
            + Constants.USER_PROFILE_TABLE + "." + Constants.USER_PROFILE_ID + ""
            + " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" + " and (:membershipStatus is null or "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_STATUS + " = :membershipStatus)"
            + " and (:userProfileFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_FULL_NAME + ") like %:userProfileFullNameKeywords%)";

    String GET_COACHES_BY_PAGE = "select membership.coach_id from " + Constants.MEMBERSHIP_TABLE + ""
            + " inner join " + Constants.COACH_TABLE + "" + " on " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_COACH_ID + " = " + Constants.COACH_TABLE + "." + Constants.COACH_ID + ""
            + " inner join " + Constants.USER_PROFILE_TABLE + " on " + Constants.COACH_TABLE + "."
            + Constants.COACH_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_ID + "" + " where (:userProfileId is null or " + Constants.COACH_TABLE
            + "." + Constants.COACH_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:membershipStatus is null or " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_STATUS + " = :membershipStatus)"
            + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%) limit :startIndex, "
            + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NUMBER_OF_COACHES_PAGING = "select count(*) from " + Constants.MEMBERSHIP_TABLE + "" + " inner join "
            + Constants.COACH_TABLE + "" + " on " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_COACH_ID + " = " + Constants.COACH_TABLE + "." + Constants.COACH_ID + ""
            + " inner join " + Constants.USER_PROFILE_TABLE + " on " + Constants.COACH_TABLE + "."
            + Constants.COACH_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_ID + "" + " where (:userProfileId is null or "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:membershipStatus is null or " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_STATUS + " = :membershipStatus)"
            + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%)";

    String GET_COACHES = "select membership.coach_id from " + Constants.MEMBERSHIP_TABLE + "" + " inner join "
            + Constants.COACH_TABLE + "" + " on " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_COACH_ID + " = " + Constants.COACH_TABLE + "." + Constants.COACH_ID + ""
            + " inner join " + Constants.USER_PROFILE_TABLE + " on " + Constants.COACH_TABLE + "."
            + Constants.COACH_USER_PROFILE_ID + " = " + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_ID + "" + " where (:userProfileId is null or "
            + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + " = :userProfileId)"
            + " and (:membershipStatus is null or " + Constants.MEMBERSHIP_TABLE + "."
            + Constants.MEMBERSHIP_STATUS + " = :membershipStatus)"
            + " and (:coachFullNameKeywords is null or lower(" + Constants.USER_PROFILE_TABLE + "."
            + Constants.USER_PROFILE_FULL_NAME + ") like %:coachFullNameKeywords%)";

    /**
     * @param id - membership's id that user want to get
     * @return selected membership
     */
    Membership findMembershipById(Long id);

    /**
     * @param coachId                     - coach's id that user want to get
     *                                    memberships (this parameter could be
     *                                    optional)
     * @param membershipStatus            - membership's status that user want to
     *                                    get memberships (this parameter could be
     *                                    optional)
     * @param userProfileFullNameKeywords - user's profile's fullname keywords that
     *                                    user want to get memberships (this
     *                                    parameter could be optional)
     * @param startIndex                  - start index (for pagination)
     * @return list of memberships
     */
    @Query(nativeQuery = true, value = GET_MEMBERSHIPS_BY_PAGE)
    List<Object> getMembershipsByPage(@Param("coachId") Integer coachId,
                                      @Param("membershipStatus") Integer membershipStatus,
                                      @Param("userProfileFullNameKeywords") String userProfileFullNameKeywords,
                                      @Param("startIndex") Integer startIndex);

    /**
     * @param coachId                     - coach's id that user want to count
     *                                    number of memberships (this parameter
     *                                    could be optional)
     * @param membershipStatus            - membership's status that user want to
     *                                    count number of memberships (this
     *                                    parameter could be optional)
     * @param userProfileFullNameKeywords - user's profile's fullname's keywords
     *                                    that user want to count number of
     *                                    memberships (this parameter could be
     *                                    optional)
     * @return number of memberships
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_MEMBERSHIPS_PAGING)
    List<Object> getNumberOfMembershipsPaging(@Param("coachId") Integer coachId,
                                              @Param("membershipStatus") Integer membershipStatus,
                                              @Param("userProfileFullNameKeywords") String userProfileFullNameKeywords);

    /**
     * @param coachId                     - coach's id that user want to get
     *                                    memberships (this parameter could be
     *                                    optional)
     * @param membershipStatus            - membership's status that user want to
     *                                    get memberships (this parameter could be
     *                                    optional)
     * @param userProfileFullNameKeywords - user's profile's fullname keywords that
     *                                    user want to get memberships (this
     *                                    parameter could be optional)
     * @return list of memberships
     */
    @Query(nativeQuery = true, value = GET_MEMBERSHIPS)
    List<Object> getMemberships(@Param("coachId") Integer coachId,
                                @Param("membershipStatus") Integer membershipStatus,
                                @Param("userProfileFullNameKeywords") String userProfileFullNameKeywords);

    /**
     * @param userProfileId         - user's profile's id that user want to get list
     *                              of coaches (this parameter could be optional)
     * @param membershipStatus      - membership's status that user want to get list
     *                              of coaches (this parameter could be optional)
     * @param coachFullNameKeywords - user's profile's fullname's keywords that user
     *                              want to get list of coaches (this parameter
     *                              could be optional)
     * @param startIndex            - start index that user want to get list of
     *                              coaches (this parameter could be optional)
     * @return list of coaches
     */
    @Query(nativeQuery = true, value = GET_COACHES_BY_PAGE)
    List<Object> getCoachesByPage(@Param("userProfileId") Integer userProfileId,
                                  @Param("membershipStatus") Integer membershipStatus,
                                  @Param("coachFullNameKeywords") String coachFullNameKeywords,
                                  @Param("startIndex") Integer startIndex);

    /**
     * @param userProfileId         - user's profile's id that user want to count
     *                              number of coaches (this parameter could be
     *                              optional)
     * @param membershipStatus      - membership's status that user want to count
     *                              number of coaches (this parameter could be
     *                              optional)
     * @param coachFullNameKeywords - user's profile's fullname keywords that user
     *                              want to count number of coaches (this parameter
     *                              could be optional)
     * @return number of coaches
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_COACHES_PAGING)
    List<Object> getNumberOfCoachesPaging(@Param("userProfileId") Integer userProfileId,
                                          @Param("membershipStatus") Integer membershipStatus,
                                          @Param("coachFullNameKeywords") String coachFullNameKeywords);

    /**
     * @param userProfileId         - user's profile's id that user want to get list
     *                              of coaches (this parameter could be optional)
     * @param membershipStatus      - membership's status that user want to get list
     *                              of coaches (this parameter could be optional)
     * @param coachFullNameKeywords - user's profile's fullname's keywords that user
     *                              want to get list of coaches (this parameter
     *                              could be optional)
     * @return list of coaches
     */
    @Query(nativeQuery = true, value = GET_COACHES)
    List<Object> getCoaches(@Param("userProfileId") Integer userProfileId,
                            @Param("membershipStatus") Integer membershipStatus,
                            @Param("coachFullNameKeywords") String coachFullNameKeywords);

    /**
     * @param coach       - coach that user want to get selected membership
     * @param userProfile - user's profile that user want to get selected membership
     * @return selected membership
     */
    Membership findMembershipByCoachAndUserProfile(Coach coach, UserProfile userProfile);

}
