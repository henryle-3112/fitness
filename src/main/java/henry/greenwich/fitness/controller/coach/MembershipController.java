package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.membership.Membership;
import henry.greenwich.fitness.service.coach.MembershipService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("membership-management")
public class MembershipController {
    private MembershipService membershipService;

    public MembershipController(@Qualifier("membershipService") MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    /**
     * @param response - response to add number of pages and memberships to header
     *                 of response (this parameter could be optional)
     * @param coachId  - coach's id that user want to get memberships (this
     *                 parameter could be optional)
     * @param status   - membership's status that user want to get memberships (this
     *                 parameter could be optional)
     * @param search   - user's profile's full name's keywords that user wan to get
     *                 memberships (this parameter could be optional)
     * @param page     - start index (for pagination) (this parameter could be
     *                 optional)
     * @return list of memberships
     */
    @GetMapping(value = "/memberships", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Membership> getMemberships(HttpServletResponse response,
            @RequestParam(required = false) Integer coachId, @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String search, @RequestParam(required = false) Integer page) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nMembershipsPaging = this.membershipService.getNumberOfMembershipsPaging(coachId, status, search);
            response.addHeader("X-Total-Count", String.valueOf(nMembershipsPaging));
            response.addHeader("X-Total-Page", String.valueOf(nMembershipsPaging / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.membershipService.getMembershipsByPage(coachId, status, search, startIndex);
        } else {
            return this.membershipService.getMemberships(coachId, status, search);
        }
    }

    /**
     * @param response      - response to add number of pages and coaches to header
     *                      of response (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get coaches
     *                      (this parameter could be optional)
     * @param status        - membership's status that user want to get coaches
     *                      (this parameter could be optional)
     * @param search        - user's profile's full name's keywords that user wan to
     *                      get coaches (this parameter could be optional)
     * @param page          - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of coaches
     */
    @GetMapping(value = "/coaches", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Coach> getCoaches(HttpServletResponse response, @RequestParam(required = false) Integer userProfileId,
            @RequestParam(required = false) Integer status, @RequestParam(required = false) String search,
            @RequestParam(required = false) Integer page) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nCoachesPaging = this.membershipService.getNumberOfCoachesPaging(userProfileId, status, search);
            response.addHeader("X-Total-Count", String.valueOf(nCoachesPaging));
            response.addHeader("X-Total-Page", String.valueOf(nCoachesPaging / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.membershipService.getCoachesByPage(userProfileId, status, search, startIndex);
        } else {
            return this.membershipService.getCoaches(userProfileId, status, search);
        }
    }

    /**
     * @param coachId       - coach's id that user want to get selected membership
     * @param userProfileId - user's profile's id that user want to get selected
     *                      membership
     * @return selected membership
     */
    @GetMapping(value = "/users/{userProfileId}/coaches/{coachId}/memberships", produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Membership getCoachByCoachIdAndUserProfileId(@PathVariable int coachId, @PathVariable int userProfileId) {
        return this.membershipService.getMembershipByCoachIdAndUserProfileId(coachId, userProfileId);
    }

    /**
     * @param membership - membership that user want to add to the database
     * @return inserted membership
     */
    @PostMapping(value = "/memberships", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Membership addMembership(@RequestBody Membership membership) {
        return this.membershipService.addMembership(membership);
    }

    /**
     * @param membership - membership that user want to update to the database
     * @return updated membership
     */
    @PutMapping(value = "/memberships", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Membership updateMembership(@RequestBody Membership membership) {
        return this.membershipService.updateMembership(membership);
    }

}
