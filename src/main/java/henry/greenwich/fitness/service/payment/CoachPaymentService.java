package henry.greenwich.fitness.service.payment;

import henry.greenwich.fitness.model.payment.CoachPayment;
import henry.greenwich.fitness.repository.payment.CoachPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachPaymentService {
    /**
     * coachPaymentRepository - interact with coach payment data
     */
    private CoachPaymentRepository coachPaymentRepository;

    /**
     * @param coachPaymentRepository - inject coachPaymentRepository
     */
    public CoachPaymentService(CoachPaymentRepository coachPaymentRepository) {
        this.coachPaymentRepository = coachPaymentRepository;
    }

    /**
     * @param coachPayment - coach payment
     * @return inserted coach payment
     */
    public CoachPayment addCoachPayment(CoachPayment coachPayment) {
        return this.coachPaymentRepository.saveAndFlush(coachPayment);
    }

    /**
     * @param coachId    - coach's id
     * @param month      - month
     * @param year       - year
     * @param startIndex - start's index
     * @return list of coach payments
     */
    public List<Object> findCoachPaymentsByCoachIdAndByMonthAndByYearAndByPage(
            int coachId,
            String month,
            String year,
            int startIndex
    ) {
        return this.coachPaymentRepository.findCoachPaymentsByCoachIdAndByMonthAndByYearAndByPage(
                coachId,
                month,
                year,
                startIndex
        );
    }

    /**
     * @param coachId - coach's id
     * @param month   - month
     * @param year    - year
     * @return number of coach payments
     */
    public List<Object> countCoachPaymentsByCoachIdAndByMonthAndByYear(
            int coachId,
            String month,
            String year
    ) {
        return this.coachPaymentRepository.countCoachPaymentsByCoachIdAndByMonthAndByYear(
                coachId,
                month,
                year
        );
    }

    /**
     * @param coachId - coach's id
     * @param month   - month
     * @param year    - year
     * @return total payments
     */
    public List<Object> getTotalPaymentByCoachIdByMonthAndByYear(
            int coachId,
            String month,
            String year
    ) {
        return this.coachPaymentRepository.getTotalPaymentByCoachIdByMonthAndByYear(
                coachId,
                month,
                year
        );
    }

    /**
     *
     * @param userProfileId - user profile id
     * @param month - month
     * @param year - year
     * @param startIndex - start index
     * @return list of coach payments
     */
    public List<Object> findCoachPaymentsByUserProfileIdAndByMonthAndByYearAndByPage(
            int userProfileId,
            String month,
            String year,
            int startIndex
    ) {
        return this.coachPaymentRepository.findCoachPaymentsByUserProfileIdAndByMonthAndByYearAndByPage(
                userProfileId,
                month,
                year,
                startIndex
        );
    }

    /**
     *
     * @param userProfileId - user profile id
     * @param month - month
     * @param year - year
     * @return number of coach payments
     */
    public List<Object> countCoachPaymentsByUserProfileIdAndByMonthAndByYear(
            int userProfileId,
            String month,
            String year
    ) {
        return this.coachPaymentRepository.countCoachPaymentsByCoachIdAndByMonthAndByYear(
                userProfileId,
                month,
                year
        );
    }

    /**
     *
     * @param userProfileId - user profile id
     * @param month - month
     * @param year - year
     * @return total payment
     */
    public List<Object> getTotalPaymentByUserProfileIdByMonthAndByYear(
            int userProfileId,
            String month,
            String year
    ) {
        return this.coachPaymentRepository.getTotalPaymentByCoachIdByMonthAndByYear(
                userProfileId,
                month,
                year
        );
    }
}
