package henry.greenwich.fitness.repository.payment;

import henry.greenwich.fitness.model.payment.CoachPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoachPaymentRepository extends JpaRepository<CoachPayment, Long> {

    /**
     * @param coachId    - coach's id
     * @param month      - month
     * @param year       - year
     * @param startIndex - start index
     * @return list of coach payments
     */
    @Query(nativeQuery = true, value =
            "select coach_payment.membership_id, coach_payment.sum " +
                    "from coach_payment inner join membership " +
                    "on coach_payment.membership_id = membership.id " +
                    "where membership.coach_id = :coachId and month(coach_payment.created_date) = :month " +
                    "and year(coach_payment.created_date) = :year limit :startIndex, 8")
    List<Object> findCoachPaymentsByCoachIdAndByMonthAndByYearAndByPage(
            int coachId,
            String month,
            String year,
            int startIndex
    );

    /**
     * @param coachId - coach id
     * @param month   - month
     * @param year    - year
     * @return number of coach payments
     */
    @Query(nativeQuery = true, value =
            "select count(*) " +
                    "from coach_payment inner join membership " +
                    "on coach_payment.membership_id = membership.id " +
                    "where membership.coach_id = :coachId and month(coach_payment.created_date) = :month " +
                    "and year(coach_payment.created_date) = :year")
    List<Object> countCoachPaymentsByCoachIdAndByMonthAndByYear(
            int coachId,
            String month,
            String year
    );

    /**
     * @param coachId - coach's id
     * @param month   - month
     * @param year    - year
     * @return total payment
     */
    @Query(nativeQuery = true, value =
            "select sum(coach_payment.sum) " +
                    "from coach_payment inner join membership " +
                    "on coach_payment.membership_id = membership.id " +
                    "where membership.coach_id = :coachId and month(coach_payment.created_date) = :month " +
                    "and year(coach_payment.created_date) = :year")
    List<Object> getTotalPaymentByCoachIdByMonthAndByYear(
            int coachId,
            String month,
            String year
    );


    /**
     * @param userProfileId - user profile id
     * @param month         - month
     * @param year          - year
     * @param startIndex    - start index
     * @return list of coach payments
     */
    @Query(nativeQuery = true, value =
            "select coach_payment.membership_id, coach_payment.sum " +
                    "from coach_payment inner join membership " +
                    "on coach_payment.membership_id = membership.id " +
                    "where membership.user_profile_id = :userProfileId and month(coach_payment.created_date) = :month " +
                    "and year(coach_payment.created_date) = :year limit :startIndex, 8")
    List<Object> findCoachPaymentsByUserProfileIdAndByMonthAndByYearAndByPage(
            int userProfileId,
            String month,
            String year,
            int startIndex
    );

    /**
     * @param userProfileId - user profile id
     * @param month         - month
     * @param year          - year
     * @return number of coach payments
     */
    @Query(nativeQuery = true, value =
            "select count(*) " +
                    "from coach_payment inner join membership " +
                    "on coach_payment.membership_id = membership.id " +
                    "where membership.user_profile_id = :userProfileId and month(coach_payment.created_date) = :month " +
                    "and year(coach_payment.created_date) = :year")
    List<Object> countCoachPaymentsByUserProfileIdAndByMonthAndByYear(
            int userProfileId,
            String month,
            String year
    );

    /**
     * @param userProfileId - user profile id
     * @param month         - month
     * @param year          - year
     * @return total payment
     */
    @Query(nativeQuery = true, value =
            "select sum(coach_payment.sum) " +
                    "from coach_payment inner join membership " +
                    "on coach_payment.membership_id = membership.id " +
                    "where membership.user_profile_id = :userProfileId and month(coach_payment.created_date) = :month " +
                    "and year(coach_payment.created_date) = :year")
    List<Object> getTotalPaymentByUserProfileIdByMonthAndByYear(
            int userProfileId,
            String month,
            String year
    );
}
