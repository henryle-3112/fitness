package henry.greenwich.fitness.controller.gift;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.gift.Gift;
import henry.greenwich.fitness.model.gift.UserGift;
import henry.greenwich.fitness.service.gift.UserGiftService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/gift-management")
public class UserGiftController {
    private UserGiftService userGiftService;

    /**
     * @param userGiftService - inject userGiftService
     */
    public UserGiftController(UserGiftService userGiftService) {
        this.userGiftService = userGiftService;
    }

    /**
     * @param status     - user's gift's status that user wants to get gifts (this parameter could be optional)
     * @param search     - gift's title's keywords that user wants to get gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId - gift's type's id that user wants to get gifts (this parameter could be optional)
     * @param page       - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    @GetMapping(value = "/user-gifts", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Gift> getGifts(HttpServletResponse response,
                                  @RequestParam(required = false) Integer status,
                                  @RequestParam(required = false) String search,
                                  @RequestParam(required = false) Integer userProfileId,
                                  @RequestParam(required = false) Integer giftTypeId,
                                  @RequestParam(required = false) Integer page) {
        if (page != null) {
            return this.getGiftsPaging(response, status, search, userProfileId, giftTypeId, page);
        }
        return this.userGiftService.getGifts(status, search, userProfileId, giftTypeId);
    }

    /**
     * @param status     - user's gift's status that user wants to get gifts (this parameter could be optional)
     * @param search     - gift's title's keywords that user wants to get gifts (this parameter could be optional)
     * @param userProfileId     - user's profile's id that user wants to get gifts (this parameter could be optional)
     * @param giftTypeId - gift's type's id that user wants to get gifts (this parameter could be optional)
     * @param page       - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    private List<Gift> getGiftsPaging(HttpServletResponse response,
                                      Integer status,
                                      String search,
                                      Integer userProfileId,
                                      Integer giftTypeId,
                                      Integer page) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nGifts = this.userGiftService.getNumberOfGifts(status, search, userProfileId, giftTypeId);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nGifts));
        int nPages = nGifts > 0 ? (nGifts >= Constants.NUMBER_ITEMS_PER_PAGE ? nGifts / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.userGiftService.getGiftsByPage(status, search, userProfileId, giftTypeId, startIndex - 1);
    }

    /**
     * @param userGift - user's gift that user want to add
     * @return inserted user's gift
     */
    @PostMapping(value = "/user-gifts", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserGift addUserGift(@RequestBody UserGift userGift) {
        return this.userGiftService.addUserGift(userGift);
    }

    /**
     * @param userGift - user' gift that user want to update
     * @return updated user's gift
     */
    @PutMapping(value = "/user-gifts", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public UserGift updateUserGift(@RequestBody UserGift userGift) {
        return this.userGiftService.updateUserGift(userGift);
    }
}
