package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.coach.Membership;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.coach.MembershipService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class MembershipController {
    /**
     * membershipService - interact with membership
     * userProfileService - interact with user's profile
     */
    private MembershipService membershipService;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param membershipService  - inject membershipService
     * @param userProfileService - inject userProfileService
     * @param coachService       - inject coachService
     */
    public MembershipController(MembershipService membershipService,
                                UserProfileService userProfileService,
                                CoachService coachService) {
        this.membershipService = membershipService;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param coach  - coach
     * @param status - status
     * @return number of memberships
     */
    @PostMapping(value = "/memberships/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countMembershipsByCoachAndStatus(@RequestBody Coach coach, @PathVariable int status) {
        int nMemberShips = this.membershipService.countMembershipsByCoachAndStatus(coach, status);
        return new ResponseMessage(String.valueOf(nMemberShips));
    }

    /**
     * @param coachId - coachId
     * @param page    - current page
     * @param status  - status
     * @param keyword - selected keyword
     * @return list of memberships
     */
    @GetMapping(value = "/memberships/paging/{coachId}/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Membership> getMembershipsByCoachByPageAndByStatus(
            @PathVariable int coachId, @PathVariable int page, @PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Membership> memberships = new ArrayList<>();
        List<Object> membershipsObject = this.membershipService.findMembershipsByCoachAndByPageAndByStatus(
                coachId, selectedKeyWord, startIndex - 1, status);
        for (int i = 0; i < membershipsObject.size(); i++) {
            Object[] eachMembershipObject = (Object[]) membershipsObject.get(i);
            int userProfileId = (int) eachMembershipObject[1];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            Coach coach = this.coachService.getCoachById((long) coachId);
            int membershipId = (int) eachMembershipObject[2];
            int membershipStatus = (int) eachMembershipObject[3];
            Date membershipStartDate = (Date) eachMembershipObject[4];
            Membership membership = new Membership(
                    (long) membershipId,
                    userProfile,
                    coach,
                    membershipStatus,
                    membershipStartDate
            );
            memberships.add(membership);
        }
        return memberships;
    }

    /**
     * @param coachId - coach's id
     * @param status  - status
     * @param keyword - selected keyword
     * @return - number of memberships
     */
    @GetMapping(value = "/memberships/count/{coachId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countMembershipsByCoachAndByStatus(
            @PathVariable int coachId, @PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countMembershipsObject = this.membershipService.countMembershipsByCoachAndByStatus(coachId, selectedKeyWord, status);
        Object eachCountMembershipObject = countMembershipsObject.get(0);
        return new ResponseMessage(eachCountMembershipObject.toString());
    }

    /**
     * @param userId  - user's id
     * @param page    - page
     * @param status  - status
     * @param keyword - keyword
     * @return list of coaches
     */
    @GetMapping(value = "/membership/coaches/paging/{userId}/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Coach> getCoachesByUserProfileIdAndByPageAndStatus(
            @PathVariable int userId, @PathVariable int page, @PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Coach> coaches = new ArrayList<>();
        List<Object> coachesObject = this.membershipService.findCoachesByUserProfileIdAndByPageAndByStatus(
                userId, selectedKeyWord, startIndex - 1, status);
        for (int i = 0; i < coachesObject.size(); i++) {
            Object[] eachCoachObject = (Object[]) coachesObject.get(i);
            int coachId = (int) eachCoachObject[0];
            Coach coach = this.coachService.getCoachById((long) coachId);
            coaches.add(coach);
        }
        return coaches;
    }

    /**
     * @param userId  - user's id
     * @param status  - status
     * @param keyword - keyword
     * @return number of coaches
     */
    @GetMapping(value = "/memberships/coaches/count/{userId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachesByUserProfileIdAndByStatus(
            @PathVariable int userId, @PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countCoachesObject = this.membershipService.countCoachesByUserProfileIdAndByStatus(userId, selectedKeyWord, status);
        Object eachCountCoachesObject = countCoachesObject.get(0);
        return new ResponseMessage(eachCountCoachesObject.toString());
    }

    /**
     * @param coachId       - coach id
     * @param userProfileId - user profile id
     * @param status        - status
     * @return number of memebrships
     */
    @GetMapping(value = "/memberships/count/{coachId}/{userProfileId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countMembershipsByCoachAndUserProfileAndStatus(
            @PathVariable int coachId, @PathVariable int userProfileId, @PathVariable int status) {
        Coach coach = this.coachService.getCoachById((long) coachId);
        UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
        int nMemberships = this.membershipService.countMembershipsByCoachAndUserProfileAndStatus(coach, userProfile, status);
        return new ResponseMessage(String.valueOf(nMemberships));
    }

    /**
     * @param membership - membership
     * @return inserted membership
     */
    @PostMapping(value = "/memberships/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Membership addMembership(@RequestBody Membership membership) {
        return this.membershipService.addMembership(membership);
    }

    /**
     * @param membership - membership
     * @return updated membership
     */
    @PostMapping(value = "/memberships/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Membership updateMembership(@RequestBody Membership membership) {
        return this.membershipService.updateMembership(membership);
    }

    /**
     * @param coachId - coach id
     * @param page    - page
     * @param keyword - keyword
     * @return list of memberships
     */
    @GetMapping(value = "/memberships/paging/{coachId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Membership> getMembershipsByCoachAndByPage(
            @PathVariable int coachId, @PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Membership> memberships = new ArrayList<>();
        List<Object> membershipsObject = this.membershipService.findMembershipsByCoachAndByPage(
                coachId, selectedKeyWord, startIndex - 1);
        for (int i = 0; i < membershipsObject.size(); i++) {
            Object[] eachMembershipObject = (Object[]) membershipsObject.get(i);
            int userProfileId = (int) eachMembershipObject[1];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            Coach coach = this.coachService.getCoachById((long) coachId);
            int membershipId = (int) eachMembershipObject[2];
            int membershipStatus = (int) eachMembershipObject[3];
            Date membershipStartDate = (Date) eachMembershipObject[4];
            Membership membership = new Membership(
                    (long) membershipId,
                    userProfile,
                    coach,
                    membershipStatus,
                    membershipStartDate
            );
            memberships.add(membership);
        }
        return memberships;
    }

    /**
     * @param coachId - coach id
     * @param status  - status
     * @param keyword - keyword
     * @return number of memberships
     */
    @GetMapping(value = "/memberships/count/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countMembershipsByCoachAndStatus(
            @PathVariable int coachId, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countMembershipsObject = this.membershipService.countMembershipsByCoach(coachId, selectedKeyWord);
        Object eachCountMembershipObject = countMembershipsObject.get(0);
        return new ResponseMessage(eachCountMembershipObject.toString());
    }

    /**
     * @param userId  - user profile id
     * @param page    - page
     * @param keyword - keyword
     * @return list of coaches
     */
    @GetMapping(value = "/membership/coaches/paging/{userId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Coach> getCoachesByUserAndByStatus(
            @PathVariable int userId, @PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Coach> coaches = new ArrayList<>();
        List<Object> coachesObject = this.membershipService.findCoachesByUserProfileIdAndByPage(
                userId, selectedKeyWord, startIndex - 1);
        for (int i = 0; i < coachesObject.size(); i++) {
            Object[] eachCoachObject = (Object[]) coachesObject.get(i);
            int coachId = (int) eachCoachObject[0];
            Coach coach = this.coachService.getCoachById((long) coachId);
            coaches.add(coach);
        }
        return coaches;
    }

    /**
     * @param userId  - user profile id
     * @param keyword - keyword
     * @return number of coaches
     */
    @GetMapping(value = "/memberships/coaches/count/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachesByUserAndStatus(@PathVariable int userId, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countCoachesObject = this.membershipService.countCoachesByUserProfileId(userId, selectedKeyWord);
        Object eachCountCoachesObject = countCoachesObject.get(0);
        return new ResponseMessage(eachCountCoachesObject.toString());
    }

    /**
     *
     * @param coachId - coach id
     * @param userProfileId - user profile id
     * @return selected membership
     */
    @GetMapping(value = "/memberships/{coachId}/{userProfileId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Membership countCoachesByUserAndStatus(@PathVariable int coachId, @PathVariable int userProfileId) {
        // get selected coach
        Coach coach = this.coachService.getCoachById((long) coachId);
        // get selected user profile
        UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
        return this.membershipService.findMembershipByCoachAndUserProfile(coach, userProfile);
    }
}
