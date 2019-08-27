package henry.greenwich.fitness.service.gift;

import henry.greenwich.fitness.model.gift.Gift;
import henry.greenwich.fitness.model.gift.GiftType;
import henry.greenwich.fitness.model.gift.UserGift;
import henry.greenwich.fitness.repository.gift.UserGiftRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGiftService {
    private UserGiftRepository userGiftRepository;
    private GiftTypeService giftTypeService;

    /**
     * @param userGiftRepository - inject userGiftRepository
     * @param giftTypeService    - inject giftTypeService
     */
    public UserGiftService(UserGiftRepository userGiftRepository,
                           GiftTypeService giftTypeService) {
        this.userGiftRepository = userGiftRepository;
        this.giftTypeService = giftTypeService;
    }

    /**
     * @param userGift - user's gift that user want to add
     * @return inserted user's gift
     */
    public UserGift addUserGift(UserGift userGift) {
        return this.userGiftRepository.saveAndFlush(userGift);
    }

    /**
     * @param userGift - user's gift that user want to update
     * @return updated user's gift
     */
    public UserGift updateUserGift(UserGift userGift) {
        return this.userGiftRepository.saveAndFlush(userGift);
    }

    /**
     * @param userGiftStatus    - user's gift's status that user wants to get gifts (this parameter could be optional)
     * @param giftTitleKeywords - gift's title's keywords that user wants to get gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId        - gift's type's id that user wants to get gifts (this parameter could be optional)
     * @param startIndex        - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    public List<Gift> getGiftsByPage(Integer userGiftStatus,
                                     String giftTitleKeywords,
                                     Integer userProfileId,
                                     Integer giftTypeId,
                                     Integer startIndex) {
        List<Object> giftsObjectList = this.userGiftRepository.getGiftsByPage(
                userGiftStatus,
                giftTitleKeywords,
                userProfileId,
                giftTypeId,
                startIndex
        );
        return this.getGiftsFromObjectList(giftsObjectList);
    }

    /**
     * @param userGiftStatus    - user's gift's status that user wants to get gifts (this parameter could be optional)
     * @param giftTitleKeywords - gift's title's keywords that user wants to get gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId        - gift's type's id that user wants to get gifts (this parameter could be optional)
     * @return list of gifts
     */
    public List<Gift> getGifts(Integer userGiftStatus,
                               String giftTitleKeywords,
                               Integer userProfileId,
                               Integer giftTypeId) {
        List<Object> giftsObjectList = this.userGiftRepository.getGifts(
                userGiftStatus,
                giftTitleKeywords,
                userProfileId,
                giftTypeId
        );
        return this.getGiftsFromObjectList(giftsObjectList);
    }

    /**
     * @param userGiftStatus    - user's gift's status that user wants to count number of gifts (this parameter could be optional)
     * @param giftTitleKeywords - gift's title's keywords that user wants to count number of gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId        - gift's type's id that user wants to count number of gifts (this parameter could be optional)
     * @return number of gifts
     */
    public int getNumberOfGifts(Integer userGiftStatus,
                                String giftTitleKeywords,
                                Integer userProfileId,
                                Integer giftTypeId) {
        List<Object> nGiftsObjectList = this.userGiftRepository.getNumberOfGifts(
                userGiftStatus,
                giftTitleKeywords,
                userProfileId,
                giftTypeId
        );
        return Integer.valueOf(nGiftsObjectList.get(0).toString());
    }

    /**
     * @param giftsObjectList - gifts object list that will be converted to list of gifts
     * @return list of gifts
     */
    private List<Gift> getGiftsFromObjectList(List<Object> giftsObjectList) {
        List<Gift> gifts = new ArrayList<>();
        for (Object o : giftsObjectList) {
            Object[] giftObjectArray = (Object[]) o;
            Gift gift = this.createGiftFromObjectArray(giftObjectArray);
            gifts.add(gift);
        }
        return gifts;
    }

    /**
     * @param giftObjectArray - gift object array that will be converted to gift object
     * @return selected gift object
     */
    private Gift createGiftFromObjectArray(Object[] giftObjectArray) {
        int giftId = (int) giftObjectArray[0];
        String giftName = (String) giftObjectArray[1];
        int giftPoint = (int) giftObjectArray[2];
        int giftTypeId = (int) giftObjectArray[3];
        GiftType giftType = this.getGiftType((long) giftTypeId);
        String giftImage = (String) giftObjectArray[4];
        return new Gift((long) giftId, giftName, giftPoint, giftType, giftImage);
    }

    /**
     * @param id - gift's type's id that user want to get selected gift type
     * @return selected gift type
     */
    private GiftType getGiftType(Long id) {
        return this.giftTypeService.getGiftType(id);
    }


}
