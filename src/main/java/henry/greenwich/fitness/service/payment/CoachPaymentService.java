package henry.greenwich.fitness.service.payment;

import henry.greenwich.fitness.model.membership.Membership;
import henry.greenwich.fitness.model.payment.CoachPayment;
import henry.greenwich.fitness.repository.payment.CoachPaymentRepository;
import henry.greenwich.fitness.service.membership.MembershipService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachPaymentService {
    private CoachPaymentRepository coachPaymentRepository;
    private MembershipService membershipService;

    /**
     * @param coachPaymentRepository - inject coachPaymentRepository
     * @param membershipService      - inject membershipService
     */
    public CoachPaymentService(CoachPaymentRepository coachPaymentRepository, MembershipService membershipService) {
        this.coachPaymentRepository = coachPaymentRepository;
        this.membershipService = membershipService;
    }

    /**
     * @param coachPayment - coach payment
     * @return inserted coach payment
     */
    public CoachPayment addCoachPayment(CoachPayment coachPayment) {
        return this.coachPaymentRepository.saveAndFlush(coachPayment);
    }

    /**
     * @param coachId                          - coach's id that user want to get
     *                                         coach payment histories (this
     *                                         parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want
     *                                         to get coach payment histories (this
     *                                         parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view coach payment
     *                                         histories (this parameter could be
     *                                         optional)
     * @param yearToViewCoachPaymentHistories  - year to view coach payment
     *                                         histories (this parameter could be
     *                                         optional)
     * @param startIndex                       - start index for pagination (this
     *                                         parameter could be optional)
     * @return list of coach payment histories
     */
    public List<CoachPayment> getCoachPaymentHistoriesPaging(Integer coachId, Integer userProfileId,
            String monthToViewCoachPaymentHistories, String yearToViewCoachPaymentHistories, Integer startIndex) {
        List<Object> coachPaymentHistoriesObjectList = this.coachPaymentRepository.getCoachPaymentHistoriesPaging(
                coachId, userProfileId, monthToViewCoachPaymentHistories, yearToViewCoachPaymentHistories, startIndex);
        return this.getCoachPaymentHistoriesFromObjectList(coachPaymentHistoriesObjectList);
    }

    /**
     * @param coachId                          - coach's id that user want to get
     *                                         coach payment histories (this
     *                                         parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want
     *                                         to get coach payment histories (this
     *                                         parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view coach payment
     *                                         histories (this parameter could be
     *                                         optional)
     * @param yearToViewCoachPaymentHistories  - year to view coach payment
     *                                         histories (this parameter could be
     *                                         optional)
     * @return list of coach payment histories
     */
    public List<CoachPayment> getCoachPaymentHistories(Integer coachId, Integer userProfileId,
            String monthToViewCoachPaymentHistories, String yearToViewCoachPaymentHistories) {
        List<Object> coachPaymentHistoriesObjectList = this.coachPaymentRepository.getCoachPaymentHistories(coachId,
                userProfileId, monthToViewCoachPaymentHistories, yearToViewCoachPaymentHistories);
        return this.getCoachPaymentHistoriesFromObjectList(coachPaymentHistoriesObjectList);
    }

    /**
     * @param coachId                          - coach's id that user want to get
     *                                         number of coach payment histories
     *                                         (this parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want
     *                                         to get number of coach payment
     *                                         histories (this parameter could be
     *                                         optional)
     * @param monthToViewCoachPaymentHistories - month to view number of coach
     *                                         payment histories (this parameter
     *                                         could be optional)
     * @param yearToViewCoachPaymentHistories  - year to view number of coach
     *                                         payment histories (this parameter
     *                                         could be optional)
     * @return number of coach payment histories
     */
    public int getNumberOfCoachPaymentHistories(Integer coachId, Integer userProfileId,
            String monthToViewCoachPaymentHistories, String yearToViewCoachPaymentHistories) {
        List<Object> nCoachPaymentHistories = this.coachPaymentRepository.getNumberOfCoachPaymentHistories(coachId,
                userProfileId, monthToViewCoachPaymentHistories, yearToViewCoachPaymentHistories);
        if (nCoachPaymentHistories.size() > 0) {
            return Integer.valueOf(nCoachPaymentHistories.get(0).toString());
        }
        return 0;
    }

    /**
     * @param coachId                          - coach's id that user want to get
     *                                         coach payment histories total (this
     *                                         parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want
     *                                         to get coach payment histories total
     *                                         (this parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view number of coach
     *                                         payment histories total (this
     *                                         parameter could be optional)
     * @param yearToViewCoachPaymentHistories  - year to view number of coach
     *                                         payment histories total (this
     *                                         parameter could be optional)
     * @return coach payment histories total
     */
    public int getCoachPaymentHistoriesTotal(Integer coachId, Integer userProfileId,
            String monthToViewCoachPaymentHistories, String yearToViewCoachPaymentHistories) {
        List<Object> nCoachPaymentHistoriesTotal = this.coachPaymentRepository.getCoachPaymentHistoriesTotal(coachId,
                userProfileId, monthToViewCoachPaymentHistories, yearToViewCoachPaymentHistories);
        if (nCoachPaymentHistoriesTotal.size() > 0 && nCoachPaymentHistoriesTotal.get(0) != null) {
            return Integer.valueOf(nCoachPaymentHistoriesTotal.get(0).toString());
        }
        return 0;
    }

    /**
     * @param coachPaymentHistoriesObjectList - coach payment histories object list
     *                                        that user want to convert to coach
     *                                        payment histories list
     * @return list of coach payment histories
     */
    public List<CoachPayment> getCoachPaymentHistoriesFromObjectList(List<Object> coachPaymentHistoriesObjectList) {
        List<CoachPayment> coachPaymentHistories = new ArrayList<>();
        for (Object o : coachPaymentHistoriesObjectList) {
            Object[] coachPaymentHistoriesObjectArr = (Object[]) o;
            CoachPayment coachPayment = this.createCoachPaymentHistoriesFromObjectArray(coachPaymentHistoriesObjectArr);
            coachPaymentHistories.add(coachPayment);
        }
        return coachPaymentHistories;
    }

    /**
     * @param coachPaymentHistoriesObjectArr - coach payment histories object array
     *                                       that user want to convert to coach
     *                                       payment histories
     * @return selected coach payment histories
     */
    private CoachPayment createCoachPaymentHistoriesFromObjectArray(Object[] coachPaymentHistoriesObjectArr) {
        int membershipId = (int) coachPaymentHistoriesObjectArr[0];
        Membership membership = this.getMembership((long) membershipId);
        int coachPaymentSum = (int) coachPaymentHistoriesObjectArr[1];
        CoachPayment coachPayment = new CoachPayment();
        coachPayment.setMembership(membership);
        coachPayment.setSum(coachPaymentSum);
        return coachPayment;
    }

    /**
     * @param membershipId - membership's id that user want to get selected
     *                     membership
     * @return selected membership
     */
    private Membership getMembership(Long membershipId) {
        return this.membershipService.findMembershipById(membershipId);
    }
}
