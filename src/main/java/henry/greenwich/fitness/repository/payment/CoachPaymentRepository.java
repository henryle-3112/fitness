package henry.greenwich.fitness.repository.payment;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.payment.CoachPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoachPaymentRepository extends JpaRepository<CoachPayment, Long> {

    String GET_COACH_PAYMENT_HISTORIES_BY_PAGE = "select " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_MEMBERSHIP_ID + ", " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_SUM + "" +
            " from " + Constants.COACH_PAYMENT_TABLE + " inner join " + Constants.MEMBERSHIP_TABLE + "" +
            " on " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_MEMBERSHIP_ID + " = " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_ID + "" +
            " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:monthToViewCoachPaymentHistories is null or month(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :monthToViewCoachPaymentHistories)" +
            " and (:yearToViewCoachPaymentHistories is null or year(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :yearToViewCoachPaymentHistories)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_COACH_PAYMENT_HISTORIES = "select " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_MEMBERSHIP_ID + ", " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_SUM + "" +
            " from " + Constants.COACH_PAYMENT_TABLE + " inner join " + Constants.MEMBERSHIP_TABLE + "" +
            " on " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_MEMBERSHIP_ID + " = " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_ID + "" +
            " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:monthToViewCoachPaymentHistories is null or month(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :monthToViewCoachPaymentHistories)" +
            " and (:yearToViewCoachPaymentHistories is null or year(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :yearToViewCoachPaymentHistories)";

    String GET_NUMBER_OF_COACH_PAYMENT_HISTORIES = "select count(*)" +
            " from " + Constants.COACH_PAYMENT_TABLE + " inner join " + Constants.MEMBERSHIP_TABLE + "" +
            " on " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_MEMBERSHIP_ID + " = " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_ID + "" +
            " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.USER_PROFILE_ID + " = :userProfileId)" +
            " and (:monthToViewCoachPaymentHistories is null or month(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :monthToViewCoachPaymentHistories)" +
            " and (:yearToViewCoachPaymentHistories is null or year(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :yearToViewCoachPaymentHistories)";

    String GET_COACH_PAYMENT_HISTORIES_TOTAL = "select sum(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_SUM + ") as coaches_payment_sum" +
            " from " + Constants.COACH_PAYMENT_TABLE + " inner join " + Constants.MEMBERSHIP_TABLE + "" +
            " on " + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_MEMBERSHIP_ID + " = " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_ID + "" +
            " where (:coachId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_COACH_ID + " = :coachId)" +
            " and (:userProfileId is null or " + Constants.MEMBERSHIP_TABLE + "." + Constants.MEMBERSHIP_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:monthToViewCoachPaymentHistories is null or month(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :monthToViewCoachPaymentHistories)" +
            " and (:yearToViewCoachPaymentHistories is null or year(" + Constants.COACH_PAYMENT_TABLE + "." + Constants.COACH_PAYMENT_CREATED_DATE + ") = :yearToViewCoachPaymentHistories)";


    /**
     * @param coachId                          - coach's id that user want to get coach payment histories (this parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want to get coach payment histories (this parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view coach payment histories (this parameter could be optional)
     * @param yearToViewCoachPaymentHistories  - year to view coach payment histories (this parameter could be optional)
     * @param startIndex                       - start index for pagination (this parameter could be optional)
     * @return list of coach payment histories
     */
    @Query(nativeQuery = true, value = GET_COACH_PAYMENT_HISTORIES_BY_PAGE)
    List<Object> getCoachPaymentHistoriesPaging(Integer coachId,
                                                Integer userProfileId,
                                                String monthToViewCoachPaymentHistories,
                                                String yearToViewCoachPaymentHistories,
                                                Integer startIndex);

    /**
     * @param coachId                          - coach's id that user want to get coach payment histories (this parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want to get coach payment histories (this parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view coach payment histories (this parameter could be optional)
     * @param yearToViewCoachPaymentHistories  - year to view coach payment histories (this parameter could be optional)
     * @return list of coach payment histories
     */
    @Query(nativeQuery = true, value = GET_COACH_PAYMENT_HISTORIES)
    List<Object> getCoachPaymentHistories(Integer coachId,
                                          Integer userProfileId,
                                          String monthToViewCoachPaymentHistories,
                                          String yearToViewCoachPaymentHistories);

    /**
     * @param coachId                          - coach's id that user want to get number of coach payment histories (this parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want to get number of coach payment histories (this parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view number of coach payment histories (this parameter could be optional)
     * @param yearToViewCoachPaymentHistories  - year to view number of coach payment histories (this parameter could be optional)
     * @return number of coach payment histories
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_COACH_PAYMENT_HISTORIES)
    List<Object> getNumberOfCoachPaymentHistories(Integer coachId,
                                                  Integer userProfileId,
                                                  String monthToViewCoachPaymentHistories,
                                                  String yearToViewCoachPaymentHistories);

    /**
     * @param coachId                          - coach's id that user want to get coach payment histories total (this parameter could be optional)
     * @param userProfileId                    - user's profile's id that user want to get coach payment histories total (this parameter could be optional)
     * @param monthToViewCoachPaymentHistories - month to view number of coach payment histories total (this parameter could be optional)
     * @param yearToViewCoachPaymentHistories  - year to view number of coach payment histories total (this parameter could be optional)
     * @return coach payment histories total
     */
    @Query(nativeQuery = true, value = GET_COACH_PAYMENT_HISTORIES_TOTAL)
    List<Object> getCoachPaymentHistoriesTotal(Integer coachId,
                                               Integer userProfileId,
                                               String monthToViewCoachPaymentHistories,
                                               String yearToViewCoachPaymentHistories);


}
