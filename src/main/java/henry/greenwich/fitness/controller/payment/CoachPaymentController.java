package henry.greenwich.fitness.controller.payment;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.payment.CoachPayment;
import henry.greenwich.fitness.service.payment.CoachPaymentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CoachPaymentController {
    private CoachPaymentService coachPaymentService;

    /**
     * @param coachPaymentService - inject coachPaymentService
     */
    public CoachPaymentController(CoachPaymentService coachPaymentService) {
        this.coachPaymentService = coachPaymentService;
    }

    /**
     * @param coachPayment - coach payment
     * @return inserted coach payment
     */
    @PostMapping(value = "/coaches-payment", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public CoachPayment addCoachPayment(@RequestBody CoachPayment coachPayment) {
        return this.coachPaymentService.addCoachPayment(coachPayment);
    }

    /**
     * @param coachId - coach's id that user want to get coach payment histories
     *                (this parameter could be optional)
     * @param month   - month to view coach payment histories (this parameter could
     *                be optional)
     * @param year    - year to view coach payment histories (this parameter could
     *                be optional)
     * @return list of coach payment histories
     */
    @GetMapping(value = "/coaches/{coachId}/coaches-payment", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<CoachPayment> getCoachPaymentHistoriesPagingForCoach(HttpServletResponse response,
            @PathVariable Integer coachId, @RequestParam(required = false) String month,
            @RequestParam(required = false) String year, @RequestParam(required = false) Integer page) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nCoachPaymentHistories = this.coachPaymentService.getNumberOfCoachPaymentHistories(coachId, null, month,
                    year);
            int coachPaymentHistoriesTotal = this.coachPaymentService.getCoachPaymentHistoriesTotal(coachId, null,
                    month, year);
            response.addHeader("X-Total-Payment", String.valueOf(coachPaymentHistoriesTotal));
            response.addHeader("X-Total-Count", String.valueOf(nCoachPaymentHistories));
            response.addHeader("X-Total-Page",
                    String.valueOf(nCoachPaymentHistories / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.coachPaymentService.getCoachPaymentHistoriesPaging(coachId, null, month, year, startIndex);
        }
        return this.coachPaymentService.getCoachPaymentHistories(coachId, null, month, year);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get coach
     *                      payment histories (this parameter could be optional)
     * @param month         - month to view coach payment histories (this parameter
     *                      could be optional)
     * @param year          - year to view coach payment histories (this parameter
     *                      could be optional)
     * @return list of coach payment histories
     */
    @GetMapping(value = "/users/{userProfileId}/coaches-payment", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<CoachPayment> getCoachPaymentHistoriesPagingForUser(HttpServletResponse response,
            @PathVariable Integer userProfileId, @RequestParam(required = false) String month,
            @RequestParam(required = false) String year, @RequestParam(required = false) Integer page) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nCoachPaymentHistories = this.coachPaymentService.getNumberOfCoachPaymentHistories(null, userProfileId,
                    month, year);
            int coachPaymentHistoriesTotal = this.coachPaymentService.getCoachPaymentHistoriesTotal(null, userProfileId,
                    month, year);
            response.addHeader("X-Total-Payment", String.valueOf(coachPaymentHistoriesTotal));
            response.addHeader("X-Total-Count", String.valueOf(nCoachPaymentHistories));
            response.addHeader("X-Total-Page",
                    String.valueOf(nCoachPaymentHistories / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.coachPaymentService.getCoachPaymentHistoriesPaging(null, userProfileId, month, year,
                    startIndex);
        }
        return this.coachPaymentService.getCoachPaymentHistories(null, userProfileId, month, year);
    }

}
