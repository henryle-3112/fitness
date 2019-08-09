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
@RequestMapping("payment-management")
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
                        return this.getCoachPaymentHistoriesPaging(response, coachId, null, month, year, page);
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
                        return this.getCoachPaymentHistoriesPaging(response, null, userProfileId, month, year, page);
                }
                return this.coachPaymentService.getCoachPaymentHistories(null, userProfileId, month, year);
        }

        /**
         * @param coachId       - coach's id that user want to get coach payment
         *                      histories (this parameter could be optional)
         * @param userProfileId - user's profile's id that user want to get coach
         *                      payment histories (this parameter could be optional)
         * @param month         - month to view coach payment histories (this parameter
         *                      could be optional)
         * @param year          - year to view coach payment histories (this parameter
         *                      could be optional)
         * @return list of coach payment histories
         */
        private List<CoachPayment> getCoachPaymentHistoriesPaging(HttpServletResponse response, Integer coachId,
                        Integer userProfileId, String month, String year, Integer page) {
                int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
                int nCoachPaymentHistories = this.coachPaymentService.getNumberOfCoachPaymentHistories(coachId,
                                userProfileId, month, year);
                int coachPaymentHistoriesTotal = this.coachPaymentService.getCoachPaymentHistoriesTotal(coachId,
                                userProfileId, month, year);
                response.addHeader(Constants.HEADER_X_TOTAL_PAYMENT, String.valueOf(coachPaymentHistoriesTotal));
                response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nCoachPaymentHistories));
                int nPages = nCoachPaymentHistories >= Constants.NUMBER_ITEMS_PER_PAGE
                                ? nCoachPaymentHistories / Constants.NUMBER_ITEMS_PER_PAGE
                                : 1;
                response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
                return this.coachPaymentService.getCoachPaymentHistoriesPaging(coachId, userProfileId, month, year,
                                startIndex - 1);
        }

}
