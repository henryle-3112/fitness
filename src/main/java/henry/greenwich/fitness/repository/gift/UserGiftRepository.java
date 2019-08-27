package henry.greenwich.fitness.repository.gift;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.gift.UserGift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGiftRepository extends JpaRepository<UserGift, Long> {

    String GET_GIFTS_BY_PAGE = "select " + Constants.GIFT_TABLE + "." + Constants.GIFT_ID + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_POINT + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_GIFT_TYPE_ID + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_IMAGE
            + " from " + Constants.USER_GIFT_TABLE + "" +
            " inner join " + Constants.GIFT_TABLE + " on " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_GIFT_ID + " = " + Constants.GIFT_TABLE + "." + Constants.GIFT_ID + "" +
            " where (:userGiftStatus is null or " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_STATUS + " = :userGiftStatus)" +
            " and (:giftTitleKeywords is null or lower(" + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ") like %:giftTitleKeywords%)" +
            " and (:userProfileId is null or " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:giftTypeId is null or " + Constants.GIFT_TABLE + "." + Constants.GIFT_GIFT_TYPE_ID + " = :giftTypeId)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NUMBER_OF_GIFTS = "select count(*) from " + Constants.USER_GIFT_TABLE + "" +
            " inner join " + Constants.GIFT_TABLE + " on " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_GIFT_ID + " = " + Constants.GIFT_TABLE + "." + Constants.GIFT_ID + "" +
            " where (:userGiftStatus is null or " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_STATUS + " = :userGiftStatus)" +
            " and (:giftTitleKeywords is null or lower(" + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ") like %:giftTitleKeywords%)" +
            " and (:userProfileId is null or " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:giftTypeId is null or " + Constants.GIFT_TABLE + "." + Constants.GIFT_GIFT_TYPE_ID + " = :giftTypeId)";

    String GET_GIFTS = "select " + Constants.GIFT_TABLE + "." + Constants.GIFT_ID + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_POINT + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_GIFT_TYPE_ID + ", "
            + Constants.GIFT_TABLE + "." + Constants.GIFT_IMAGE
            + " from " + Constants.USER_GIFT_TABLE + "" +
            " inner join " + Constants.GIFT_TABLE + " on " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_GIFT_ID + " = " + Constants.GIFT_TABLE + "." + Constants.GIFT_ID + "" +
            " where (:userGiftStatus is null or " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_STATUS + " = :userGiftStatus)" +
            " and (:giftTitleKeywords is null or lower(" + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ") like %:giftTitleKeywords%)" +
            " and (:userProfileId is null or " + Constants.USER_GIFT_TABLE + "." + Constants.USER_GIFT_USER_PROFILE_ID + " = :userProfileId)" +
            " and (:giftTypeId is null or " + Constants.GIFT_TABLE + "." + Constants.GIFT_GIFT_TYPE_ID + " = :giftTypeId)";

    /**
     * @param userGiftStatus    - user's gift's status that user wants to get gifts (this parameter could be optional)
     * @param giftTitleKeywords - gift's title's keywords that user wants to get gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId        - gift's type's id that user wants to get gifts (this parameter could be optional)
     * @param startIndex        - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    @Query(nativeQuery = true, value = GET_GIFTS_BY_PAGE)
    List<Object> getGiftsByPage(@Param("userGiftStatus") Integer userGiftStatus,
                                @Param("giftTitleKeywords") String giftTitleKeywords,
                                @Param("userProfileId") Integer userProfileId,
                                @Param("giftTypeId") Integer giftTypeId,
                                @Param("startIndex") Integer startIndex);

    /**
     * @param userGiftStatus    - user's gift's status that user wants to get gifts (this parameter could be optional)
     * @param giftTitleKeywords - gift's title's keywords that user wants to get gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId        - gift's type's id that user wants to get gifts (this parameter could be optional)
     * @return list of gifts
     */
    @Query(nativeQuery = true, value = GET_GIFTS)
    List<Object> getGifts(@Param("userGiftStatus") Integer userGiftStatus,
                          @Param("giftTitleKeywords") String giftTitleKeywords,
                          @Param("userProfileId") Integer userProfileId,
                          @Param("giftTypeId") Integer giftTypeId);

    /**
     * @param userGiftStatus    - user's gift's status that user wants to count number of gifts (this parameter could be optional)
     * @param giftTitleKeywords - gift's title's keywords that user wants to count number of gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId        - gift's type's id that user wants to count number of gifts (this parameter could be optional)
     * @return number of gifts
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_GIFTS)
    List<Object> getNumberOfGifts(@Param("userGiftStatus") Integer userGiftStatus,
                                  @Param("giftTitleKeywords") String giftTitleKeywords,
                                  @Param("userProfileId") Integer userProfileId,
                                  @Param("giftTypeId") Integer giftTypeId);
}
