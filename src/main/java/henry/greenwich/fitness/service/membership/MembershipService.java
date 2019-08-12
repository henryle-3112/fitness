package henry.greenwich.fitness.service.membership;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.membership.Membership;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.membership.MembershipRepository;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MembershipService {
    private MembershipRepository membershipRepository;
    private CoachService coachService;
    private UserProfileService userProfileService;

    /**
     * @param membershipRepository - inject membershipRepository
     * @param userProfileService   - inject userProfileService
     * @param coachService         - inject coachService
     */
    public MembershipService(MembershipRepository membershipRepository,
                             UserProfileService userProfileService,
                             CoachService coachService) {
        this.membershipRepository = membershipRepository;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param coachId                     - coach's id that user want to get
     *                                    memberships (this parameter could be
     *                                    optional)
     * @param membershipStatus            - membership's status that user want to
     *                                    get memberships (this parameter could be
     *                                    optional)
     * @param userProfileFullNameKeywords - user's profile's full name's keywords
     *                                    that user wan to get memberships (this
     *                                    parameter could be optional)
     * @param startIndex                  - start index (for pagination) (this
     *                                    parameter could be optional)
     * @return list of memberships
     */
    public List<Membership> getMembershipsByPage(Integer coachId, Integer membershipStatus,
                                                 String userProfileFullNameKeywords, Integer startIndex) {
        List<Object> membershipsObjectList = this.membershipRepository.getMembershipsByPage(coachId, membershipStatus,
                userProfileFullNameKeywords, startIndex);
        return this.getMembershipsFromObjectList(membershipsObjectList);
    }

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
    public int getNumberOfMembershipsPaging(Integer coachId, Integer membershipStatus,
                                            String userProfileFullNameKeywords) {
        List<Object> nMembershipPagingObjectList = this.membershipRepository.getNumberOfMembershipsPaging(coachId,
                membershipStatus, userProfileFullNameKeywords);
        if (nMembershipPagingObjectList.size() > 0) {
            return Integer.valueOf(nMembershipPagingObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param coachId                     - coach's id that user want to get
     *                                    memberships (this parameter could be
     *                                    optional)
     * @param membershipStatus            - membership's status that user want to
     *                                    get memberships (this parameter could be
     *                                    optional)
     * @param userProfileFullNameKeywords - user's profile's full name's keywrods
     *                                    that user wan to get memberships (this
     *                                    parameter could be optional)
     * @return list of memberships
     */
    public List<Membership> getMemberships(Integer coachId, Integer membershipStatus,
                                           String userProfileFullNameKeywords) {
        List<Object> membershipsObjectList = this.membershipRepository.getMemberships(coachId, membershipStatus,
                userProfileFullNameKeywords);
        return this.getMembershipsFromObjectList(membershipsObjectList);
    }

    /**
     * @param userProfileId               - user's profile's id that user want to
     *                                    get list of coaches (this parameter could
     *                                    be optional)
     * @param membershipStatus            - membership's status that user want to
     *                                    get list of coaches (this parameter could
     *                                    be optional)
     * @param userProfileFullNameKeywords - user's profile's fullname's keywords
     *                                    that user want to get list of coaches
     *                                    (this parameter could be optional)
     * @param startIndex                  - start index that user want to get list
     *                                    of coaches (this parameter could be
     *                                    optional)
     * @return list of coaches
     */
    public List<Coach> getCoachesByPage(Integer userProfileId, Integer membershipStatus,
                                        String userProfileFullNameKeywords, Integer startIndex) {
        List<Object> coacheIdsObjectList = this.membershipRepository.getCoachesByPage(userProfileId, membershipStatus,
                userProfileFullNameKeywords, startIndex);
        return this.getCoachesFromObjectList(coacheIdsObjectList);
    }

    /**
     * @param userProfileId         - user's profile's id that user want to count
     *                              number of coaches
     * @param membershipStatus      - membership's status that user want to count
     *                              number of coaches
     * @param coachFullNameKeywords - user's profile's fullname keywords that user
     *                              want to count number of coaches
     * @return number of coaches by user's profile's id, membership's status and
     * user's profile's fullname's keywords
     */
    public int getNumberOfCoachesPaging(Integer userProfileId, Integer membershipStatus, String coachFullNameKeywords) {
        List<Object> nCoachesPagingObjectList = this.membershipRepository.getNumberOfCoachesPaging(userProfileId,
                membershipStatus, coachFullNameKeywords);
        if (nCoachesPagingObjectList.size() > 0) {
            return Integer.valueOf(nCoachesPagingObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param userProfileId         - user's profile's id that user want to get list
     *                              of coaches
     * @param membershipStatus      - membership's status that user want to get list
     *                              of coaches
     * @param coachFullNameKeywords - user's profile's fullname's keywords that user
     *                              want to get list of coaches
     * @return list of coaches by user's profile's id, membership's status, user's
     * profile's fulname's keywords
     */
    public List<Coach> getCoaches(Integer userProfileId, Integer membershipStatus, String coachFullNameKeywords) {
        List<Object> coacheIdsObjectList = this.membershipRepository.getCoaches(userProfileId, membershipStatus,
                coachFullNameKeywords);
        return this.getCoachesFromObjectList(coacheIdsObjectList);
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
     * @param id - membership id
     * @return selected membership
     */
    public Membership findMembershipById(Long id) {
        return this.membershipRepository.findMembershipById(id);
    }

    /**
     * @param membershipsObjectList - memberships object list that user want to
     *                              convert to memberships list
     * @return list of memberships
     */
    private List<Membership> getMembershipsFromObjectList(List<Object> membershipsObjectList) {
        List<Membership> memberships = new ArrayList<>();
        for (Object o : membershipsObjectList) {
            Object[] membershipObjectArr = (Object[]) o;
            Membership membership = this.createMembershipFromObjectArr(membershipObjectArr);
            memberships.add(membership);
        }
        return memberships;
    }

    /**
     * @param membershipObjectArr - membership object arr that user want to convert
     *                            membership object
     * @return converted membership
     */
    private Membership createMembershipFromObjectArr(Object[] membershipObjectArr) {
        int membershipCoachId = (int) membershipObjectArr[0];
        Coach coach = this.getCoach(membershipCoachId, null);
        int membershipUserProfileId = (int) membershipObjectArr[1];
        UserProfile userProfile = this.getUserProfile((long) membershipUserProfileId);
        int membershipId = (int) membershipObjectArr[2];
        int membershipStatus = (int) membershipObjectArr[3];
        Date membershipStartDate = (Date) membershipObjectArr[4];
        return new Membership((long) membershipId, userProfile, coach, membershipStatus, membershipStartDate);
    }

    /**
     * @param coacheIdsObjectList - coaches object list that user want to convert to
     *                            coaches list
     * @return list of coaches
     */
    private List<Coach> getCoachesFromObjectList(List<Object> coacheIdsObjectList) {
        List<Coach> coaches = new ArrayList<>();
        for (Object o : coacheIdsObjectList) {
            int eachCoachId = (int) o;
            Coach coach = this.getCoach(eachCoachId, null);
            coaches.add(coach);
        }
        return coaches;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get user's
     *                      profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param coachId     - coach's id that user want to get selected coach
     * @param coachStatus - coach's status that user want to get selected coach
     * @return selected coach
     */
    private Coach getCoach(Integer coachId, Integer coachStatus) {
        return this.coachService.getCoach(coachId, coachStatus);
    }

    /**
     * @param coachId       - coach's id that user want to get selected membership
     * @param userProfileId - user's profile's id that user want to get selected
     *                      membership
     * @return selected membership
     */
    public Membership getMembershipByCoachIdAndUserProfileId(int coachId, int userProfileId) {
        Coach coach = this.getCoach(coachId, null);
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return this.membershipRepository.findMembershipByCoachAndUserProfile(coach, userProfile);
    }
}
