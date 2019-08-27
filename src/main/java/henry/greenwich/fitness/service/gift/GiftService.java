package henry.greenwich.fitness.service.gift;

import henry.greenwich.fitness.model.gift.Gift;
import henry.greenwich.fitness.model.gift.GiftType;
import henry.greenwich.fitness.repository.gift.GiftRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftService {
    private GiftRepository giftRepository;
    private GiftTypeService giftTypeService;

    /**
     * @param giftRepository  - inject giftRepository
     * @param giftTypeService - inject giftTypeService
     */
    public GiftService(GiftRepository giftRepository, GiftTypeService giftTypeService) {
        this.giftRepository = giftRepository;
        this.giftTypeService = giftTypeService;
    }

    /**
     * @param giftTitleKeywords - gift's title's keywords that user want to get gifts (this parameter could be optional)
     * @param startIndex        - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    public List<Gift> getGiftsByPage(String giftTitleKeywords, Integer startIndex) {
        List<Object> giftsObjectList = this.giftRepository.getGiftsByPage(giftTitleKeywords, startIndex);
        return this.getGiftsFromObjectList(giftsObjectList);
    }

    /**
     * @param giftTitleKeywords - gift's title's keywords that user want to get gifts (this parameter could be optional)
     * @return list of gifts
     */
    public List<Gift> getGifts(String giftTitleKeywords) {
        List<Object> giftsObjectList = this.giftRepository.getGifts(giftTitleKeywords);
        return this.getGiftsFromObjectList(giftsObjectList);
    }

    /**
     * @param giftTitleKeywords - gift's title's keywords that user want to count number of gifts (this parameter could be optional)
     * @return number of gifts
     */
    public int getNumberOfGifts(String giftTitleKeywords) {
        List<Object> nGiftsObjectList = this.giftRepository.getNumberOfGifts(giftTitleKeywords);
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
     *
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
