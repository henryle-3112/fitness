package henry.greenwich.fitness.repository.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.Membership;
import henry.greenwich.fitness.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    /**
     * @param coach  - coach
     * @param status - status
     * @return number of members that following the coach
     */
    int countMembershipsByCoachAndStatus(Coach coach, int status);

    /**
     * @param coach  - coach
     * @param status - status
     * @return list of members
     */
    List<Membership> findMembershipsByCoachAndStatus(Coach coach, int status);

    /**
     * @param coachId    - coach's id
     * @param keyword    - keyword
     * @param startIndex - start index
     * @param status     - status
     * @return list of memberships
     */
    @Query(nativeQuery = true, value = "select membership.coach_id, membership.user_profile_id, membership.id, membership.status, membership.start_date from membership inner join user_profile on membership.user_profile_id = user_profile.id where membership.coach_id = :coachId and membership.status = :status and lower(user_profile.full_name) like :keyword limit :startIndex, 8")
    List<Object> findMembershipsByCoachAndByPageAndByStatus(int coachId, String keyword, int startIndex, int status);

    /**
     * @param coachId - coach's id
     * @param keyword - keyword
     * @param status  - status
     * @return number of memberships
     */
    @Query(nativeQuery = true, value = "select count(*) from membership inner join user_profile on membership.user_profile_id = user_profile.id where membership.coach_id = :coachId and membership.status = :status and lower(user_profile.full_name) like :keyword")
    List<Object> countMembershipsByCoachAndByStatus(int coachId, String keyword, int status);

    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @param startIndex    - start index
     * @param status        - status
     * @return list of coaches
     */
    @Query(nativeQuery = true, value = "select membership.coach_id, membership.user_profile_id, membership.id, membership.status, membership.start_date from membership inner join coach on membership.coach_id = coach.id inner join user_profile on coach.user_profile_id = user_profile.id where membership.user_profile_id = :userProfileId and membership.status = :status and lower(user_profile.full_name) like :keyword limit :startIndex, 8")
    List<Object> findCoachesByUserProfileIdAndByPageAndByStatus(int userProfileId, int status, String keyword, int startIndex);


    /**
     * @param userProfileId - user's profile's id
     * @param keyword       - keyword
     * @param status        - status
     * @return number of coaches
     */
    @Query(nativeQuery = true, value = "select count(*) from membership inner join coach on membership.coach_id = coach.id inner join user_profile on coach.user_profile_id = user_profile.id where membership.user_profile_id = :userProfileId and membership.status = :status and lower(user_profile.full_name) like :keyword")
    List<Object> countCoachesByUserProfileIdAndByStatus(int userProfileId, int status, String keyword);

    /**
     * @param coach       - coach
     * @param userProfile - user's profile
     * @param status      - status
     * @return number of memberships
     */
    int countMembershipsByCoachAndUserProfileAndStatus(Coach coach, UserProfile userProfile, int status);

    /**
     * @param coachId    - coach id
     * @param keyword    - keyword
     * @param startIndex - start index
     * @return list of memberships
     */
    @Query(nativeQuery = true, value = "select membership.coach_id, membership.user_profile_id, membership.id, membership.status, membership.start_date from membership inner join user_profile on membership.user_profile_id = user_profile.id where membership.coach_id = :coachId and (membership.status = 1 or membership.status = 0) and lower(user_profile.full_name) like :keyword limit :startIndex, 8")
    List<Object> findMembershipsByCoachAndByPage(int coachId, String keyword, int startIndex);

    /**
     * @param coachId - coach id
     * @param keyword - keyword
     * @return number of memberships
     */
    @Query(nativeQuery = true, value = "select count(*) from membership inner join user_profile on membership.user_profile_id = user_profile.id where membership.coach_id = :coachId and (membership.status = 1 or membership.status = 0) and lower(user_profile.full_name) like :keyword")
    List<Object> countMembershipsByCoach(int coachId, String keyword);

    /**
     * @param userProfileId - user profile id
     * @param keyword       - keyword
     * @param startIndex    - start index
     * @return list of coaches
     */
    @Query(nativeQuery = true, value = "select membership.coach_id, membership.user_profile_id, membership.id, membership.status, membership.start_date from membership inner join coach on membership.coach_id = coach.id inner join user_profile on coach.user_profile_id = user_profile.id where membership.user_profile_id = :userProfileId and (membership.status = 1 or membership.status = 0) and lower(user_profile.full_name) like :keyword limit :startIndex, 8")
    List<Object> findCoachesByUserProfileIdAndByPage(int userProfileId, String keyword, int startIndex);

    /**
     * @param userProfileId - user profile id
     * @param keyword       - keyword
     * @return number of coaches
     */
    @Query(nativeQuery = true, value = "select count(*) from membership inner join coach on membership.coach_id = coach.id inner join user_profile on coach.user_profile_id = user_profile.id where membership.user_profile_id = :userProfileId and (membership.status = 1 or membership.status = 0) and lower(user_profile.full_name) like :keyword")
    List<Object> countCoachesByUserProfileId(int userProfileId, String keyword);

    /**
     *
     * @param coach - coach
     * @param userProfile - user profile
     * @return selected membership
     */
    Membership findMembershipByCoachAndUserProfile(Coach coach, UserProfile userProfile);

    /**
     *
     * @param id - membership id
     * @return selected membership
     */
    Membership findMembershipById(Long id);
}
