package henry.greenwich.fitness.repository.gift;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.gift.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    String GET_GIFTS_BY_PAGE = "select * from " + Constants.GIFT_TABLE + "" +
            " where (:giftTitleKeywords is null or lower(" + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ") like %:giftTitleKeywords%)" +
            " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_GIFTS = "select * from " + Constants.GIFT_TABLE + "" +
            " where (:giftTitleKeywords is null or lower(" + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ") like %:giftTitleKeywords%)";

    String GET_NUMBER_OF_GIFTS = "select count(*) from " + Constants.GIFT_TABLE + "" +
            " where (:giftTitleKeywords is null or lower(" + Constants.GIFT_TABLE + "." + Constants.GIFT_NAME + ") like %:giftTitleKeywords%)";


    /**
     * @param giftTitleKeywords - gift's title's keywords that user want to get gifts (this parameter could be optional)
     * @param startIndex        - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    @Query(nativeQuery = true, value = GET_GIFTS_BY_PAGE)
    List<Object> getGiftsByPage(@Param("giftTitleKeywords") String giftTitleKeywords,
                                @Param("startIndex") Integer startIndex);


    /**
     * @param giftTitleKeywords - gift's title's keywords that user want to get gifts (this parameter could be optional)
     * @return list of gifts
     */
    @Query(nativeQuery = true, value = GET_GIFTS)
    List<Object> getGifts(@Param("giftTitleKeywords") String giftTitleKeywords);

    /**
     * @param giftTitleKeywords - gift's title's keywords that user want to count number of gifts (this parameter could be optional)
     * @return number of gifts
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_GIFTS)
    List<Object> getNumberOfGifts(@Param("giftTitleKeywords") String giftTitleKeywords);
}
