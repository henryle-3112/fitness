package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.service.coach.CoachService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/coach-management")
public class CoachController {
    private CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    /**
     * @param response - response to add to header number of pages and number of
     *                 coaches (for pagination)
     * @param page     - current page that user want to get number of coaches (this
     *                 parameter could be optional)
     * @param status   - coach's status that user want to get coaches (this
     *                 parameter could be optional)
     * @param search   - coach's fullname's keywords that user want to get filter
     *                 coaches (this parameter could be optional)
     * @return list of coaches
     */
    @GetMapping(value = "/coaches", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Coach> getCoaches(HttpServletResponse response,
                                  @RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer status,
                                  @RequestParam(required = false) String search) {
        if (page != null) {
            return this.getCoachesPaging(response, page, status, search);
        }
        return this.coachService.getCoaches(status, search);
    }

    /**
     * @param response - response to add to header number of pages and number of
     *                 coaches (for pagination)
     * @param page     - current page that user want to get number of coaches (this
     *                 parameter could be optional)
     * @param status   - coach's status that user want to get coaches (this
     *                 parameter could be optional)
     * @param search   - coach's fullname's keywords that user want to get filter
     *                 coaches (this parameter could be optional)
     * @return list of coaches
     */
    private List<Coach> getCoachesPaging(HttpServletResponse response, Integer page, Integer status, String search) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nCoaches = this.coachService.getNumberOfCoaches(status, search);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nCoaches));
        int nPages = nCoaches > 0 ? (nCoaches >= Constants.NUMBER_ITEMS_PER_PAGE ? nCoaches / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.coachService.getCoachesByPage(status, search, startIndex - 1);
    }

    /**
     * @param coachId - coach's id that user want to get coach
     * @param status  - coach's status that user want to get coach (this parameter
     *                could be optional)
     * @return selected coach
     */
    @GetMapping(value = "/coaches/{coachId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Coach getCoach(@PathVariable Integer coachId, @RequestParam(required = false) Integer status) {
        return this.coachService.getCoach(coachId, status);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach
     * @param status        - coach's status that user want to get coach
     * @return selected coach
     */
    @GetMapping(value = "/users/{userProfileId}/coaches", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Coach getCoachByUser(@PathVariable Integer userProfileId, @RequestParam(required = false) Integer status) {
        return this.coachService.getCoachByUser(userProfileId, status);
    }
}
