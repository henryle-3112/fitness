package henry.greenwich.fitness.controller.payment;

import henry.greenwich.fitness.model.coach.Membership;
import henry.greenwich.fitness.model.payment.CoachPayment;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.coach.MembershipService;
import henry.greenwich.fitness.service.payment.CoachPaymentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Controller
public class CoachPaymentController {
    /**
     * coachPaymentService - interact with coach payment data
     * membershipService - inject membershipService
     */
    private CoachPaymentService coachPaymentService;
    private MembershipService membershipService;

    /**
     * @param coachPaymentService - inject coachPaymentService
     * @param membershipService   - inject membershipService
     */
    public CoachPaymentController(CoachPaymentService coachPaymentService,
                                  MembershipService membershipService) {
        this.coachPaymentService = coachPaymentService;
        this.membershipService = membershipService;
    }

    /**
     * @param coachPayment - coach payment
     * @return inserted coach payment
     */
    @PostMapping(value = "/coach/payment/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CoachPayment addCoachPayment(@RequestBody CoachPayment coachPayment) {
        return this.coachPaymentService.addCoachPayment(coachPayment);
    }

    /**
     * @param coachId - coach id
     * @param month   - month
     * @param year    - year
     * @param page    - page
     * @return list of coach payments
     */
    @GetMapping(value = "/coach/payment/paging/{coachId}/{month}/{year}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CoachPayment> findCoachPaymentsByCoachIdAndByMonthAndByYearAndByPage(
            @PathVariable int coachId,
            @PathVariable String month,
            @PathVariable String year,
            @PathVariable int page
    ) {
        int startIndex = ((page - 1) * 8) + 1;
        List<CoachPayment> coachPayments = new ArrayList<>();
        List<Object> coachPaymentsObject = this.coachPaymentService.findCoachPaymentsByCoachIdAndByMonthAndByYearAndByPage(
                coachId,
                month,
                year,
                startIndex - 1
        );
        for (Object o : coachPaymentsObject) {
            Object[] eachCoachPaymentObject = (Object[]) o;
            // get selected membership
            int membershipId = (int) eachCoachPaymentObject[0];
            Membership membership = this.membershipService.findMembershipById((long) membershipId);
            int eachPaymentTotal = (int) eachCoachPaymentObject[1];
            // create coach payment object
            CoachPayment coachPayment = new CoachPayment();
            coachPayment.setMembership(membership);
            coachPayment.setSum(eachPaymentTotal);
            coachPayments.add(coachPayment);
        }
        return coachPayments;
    }

    /**
     * @param coachId - coach id
     * @param month   - month
     * @param year    - year
     * @return number of coach payments
     */
    @GetMapping(value = "/coach/payment/count/{coachId}/{month}/{year}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachPaymentsByCoachIdAndByMonthAndByYear(
            @PathVariable int coachId,
            @PathVariable String month,
            @PathVariable String year
    ) {
        List<Object> countCoachPaymentsObject = this.coachPaymentService.countCoachPaymentsByCoachIdAndByMonthAndByYear(
                coachId,
                month,
                year
        );
        Object eachCountCoachPayment = countCoachPaymentsObject.get(0);
        return new ResponseMessage(eachCountCoachPayment.toString());
    }

    /**
     * @param coachId - coach id
     * @param month   - month
     * @param year    - year
     * @return total payment
     */
    @GetMapping(value = "/coach/payment/total/{coachId}/{month}/{year}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage getTotalPaymentByCoachIdByMonthAndByYear(
            @PathVariable int coachId,
            @PathVariable String month,
            @PathVariable String year
    ) {
        List<Object> totalPaymentObject = this.coachPaymentService.getTotalPaymentByCoachIdByMonthAndByYear(
                coachId,
                month,
                year
        );
        Object eachTotalPaymentObject = totalPaymentObject.get(0);
        return new ResponseMessage(totalPaymentObject.toString());
    }

    /**
     * @param userProfileId - user profile id
     * @param month   - month
     * @param year    - year
     * @param page    - page
     * @return list of coach payments
     */
    @GetMapping(value = "/user/payment/paging/{userProfileId}/{month}/{year}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CoachPayment> findCoachPaymentsByUserProfileIdAndByMonthAndByYearAndByPage(
            @PathVariable int userProfileId,
            @PathVariable String month,
            @PathVariable String year,
            @PathVariable int page
    ) {
        int startIndex = ((page - 1) * 8) + 1;
        List<CoachPayment> coachPayments = new ArrayList<>();
        List<Object> coachPaymentsObject = this.coachPaymentService.findCoachPaymentsByUserProfileIdAndByMonthAndByYearAndByPage(
                userProfileId,
                month,
                year,
                startIndex - 1
        );
        for (Object o : coachPaymentsObject) {
            Object[] eachCoachPaymentObject = (Object[]) o;
            // get selected membership
            int membershipId = (int) eachCoachPaymentObject[0];
            Membership membership = this.membershipService.findMembershipById((long) membershipId);
            int eachPaymentTotal = (int) eachCoachPaymentObject[1];
            // create coach payment object
            CoachPayment coachPayment = new CoachPayment();
            coachPayment.setMembership(membership);
            coachPayment.setSum(eachPaymentTotal);
            coachPayments.add(coachPayment);
        }
        return coachPayments;
    }

    /**
     * @param userProfileId - user profile id
     * @param month   - month
     * @param year    - year
     * @return number of coach payments
     */
    @GetMapping(value = "/user/payment/count/{userProfileId}/{month}/{year}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoachPaymentsByUserProfileIdAndByMonthAndByYear(
            @PathVariable int userProfileId,
            @PathVariable String month,
            @PathVariable String year
    ) {
        List<Object> countCoachPaymentsObject = this.coachPaymentService.countCoachPaymentsByUserProfileIdAndByMonthAndByYear(
                userProfileId,
                month,
                year
        );
        Object eachCountCoachPayment = countCoachPaymentsObject.get(0);
        return new ResponseMessage(eachCountCoachPayment.toString());
    }

    /**
     * @param userProfileId - user profile id
     * @param month   - month
     * @param year    - year
     * @return total payment
     */
    @GetMapping(value = "/user/payment/total/{userProfileId}/{month}/{year}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage getTotalPaymentByUserProfileIdByMonthAndByYear(
            @PathVariable int userProfileId,
            @PathVariable String month,
            @PathVariable String year
    ) {
        List<Object> totalPaymentObject = this.coachPaymentService.getTotalPaymentByUserProfileIdByMonthAndByYear(
                userProfileId,
                month,
                year
        );
        Object eachTotalPaymentObject = totalPaymentObject.get(0);
        return new ResponseMessage(totalPaymentObject.toString());
    }

}
