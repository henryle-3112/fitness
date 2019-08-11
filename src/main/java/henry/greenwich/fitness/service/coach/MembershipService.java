package henry.greenwich.fitness.service.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.Membership;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.coach.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {
    /**
     * memberShipRepository - interact with membership's data
     */
    private MembershipRepository membershipRepository;

    /**
     * @param membershipRepository - inject membershipRepository
     */
    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * @param coach  - coach
     * @param status - status
     * @return number of memberships
     */
    public int countMembershipsByCoachAndStatus(Coach coach, int status) {
        return this.membershipRepository.countMembershipsByCoachAndStatus(coach, status);
    }

    /**
     * @param coach  - coach
     * @param status - status
     * @return - number of memberships
     */
    public List<Membership> findMembershipsByCoachAndStatus(Coach coach, int status) {
        return this.membershipRepository.findMembershipsByCoachAndStatus(coach, status);
    }

    /**
     * @param coachId         - coach's id
     * @param selectedKeyWord - selected keyword
     * @param startIndex      - start's index
     * @param status          - status
     * @return list of memberships
     */
    public List<Object> findMembershipsByCoachAndByPageAndByStatus(int coachId, String selectedKeyWord, int startIndex, int status) {
        return this.membershipRepository.findMembershipsByCoachAndByPageAndByStatus(coachId, selectedKeyWord, startIndex, status);
    }

    /**
     * @param coachId         - coach's id
     * @param selectedKeyWord - selected keyword
     * @param status          - status
     * @return number of memberships
     */
    public List<Object> countMembershipsByCoachAndByStatus(int coachId, String selectedKeyWord, int status) {
        return this.membershipRepository.countMembershipsByCoachAndByStatus(coachId, selectedKeyWord, status);
    }

    /**
     * @param userProfileId   - user's profile's id
     * @param selectedKeyWord - selected keyword
     * @param startIndex      - start index
     * @param status          - status
     * @return list of coaches
     */
    public List<Object> findCoachesByUserProfileIdAndByPageAndByStatus(
            int userProfileId, String selectedKeyWord, int startIndex, int status) {
        return this.membershipRepository.findCoachesByUserProfileIdAndByPageAndByStatus(userProfileId, status, selectedKeyWord, startIndex);
    }

    /**
     * @param userProfileId   - user's profile's id
     * @param selectedKeyWord - selected keyword
     * @param status          - status
     * @return number of coaches
     */
    public List<Object> countCoachesByUserProfileIdAndByStatus(int userProfileId, String selectedKeyWord, int status) {
        return this.membershipRepository.countCoachesByUserProfileIdAndByStatus(userProfileId, status, selectedKeyWord);
    }

    /**
     * @param coach       - coach
     * @param userProfile - user's profile
     * @return number of memberships
     */
    public int countMembershipsByCoachAndUserProfileAndStatus(Coach coach, UserProfile userProfile, int status) {
        return this.membershipRepository.countMembershipsByCoachAndUserProfileAndStatus(coach, userProfile, status);
    }

    /**
     * @param membership - membership
     * @return inserted membership
     */
    public Membership addMembership(Membership membership) {
        return this.membershipRepository.saveAndFlush(membership);
    }

    /**
     * @param membership - membership
     * @return updated membership
     */
    public Membership updateMembership(Membership membership) {
        return this.membershipRepository.saveAndFlush(membership);
    }

    /**
     * @param coachId         - coach id
     * @param selectedKeyWord - selected keyword
     * @param startIndex      - start index
     * @return list of memberships
     */
    public List<Object> findMembershipsByCoachAndByPage(int coachId, String selectedKeyWord, int startIndex) {
        return this.membershipRepository.findMembershipsByCoachAndByPage(coachId, selectedKeyWord, startIndex);
    }

    /**
     * @param coachId         - coach id
     * @param selectedKeyWord - selected keyword
     * @return number of memberships
     */
    public List<Object> countMembershipsByCoach(int coachId, String selectedKeyWord) {
        return this.membershipRepository.countMembershipsByCoach(coachId, selectedKeyWord);
    }

    /**
     * @param userProfileId   - user profile id
     * @param selectedKeyWord - selected keyword
     * @param startIndex      - start index
     * @return list of coaches
     */
    public List<Object> findCoachesByUserProfileIdAndByPage(
            int userProfileId, String selectedKeyWord, int startIndex) {
        return this.membershipRepository.findCoachesByUserProfileIdAndByPage(userProfileId, selectedKeyWord, startIndex);
    }

    /**
     * @param userProfileId   - user profile id
     * @param selectedKeyWord - selected keyword
     * @return number of coaches
     */
    public List<Object> countCoachesByUserProfileId(int userProfileId, String selectedKeyWord) {
        return this.membershipRepository.countCoachesByUserProfileId(userProfileId, selectedKeyWord);
    }

    /**
     *
     * @param coach - coach
     * @param userProfile - user profile
     * @return selected membership
     */
    public Membership findMembershipByCoachAndUserProfile(Coach coach, UserProfile userProfile) {
        return this.membershipRepository.findMembershipByCoachAndUserProfile(coach, userProfile);
    }

    /**
     *
     * @param id - membership id
     * @return selected membership
     */
    public Membership findMembershipById(Long id) {
        return this.membershipRepository.findMembershipById(id);
    }
}
