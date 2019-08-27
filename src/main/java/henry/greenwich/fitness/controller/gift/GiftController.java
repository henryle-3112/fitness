package henry.greenwich.fitness.controller.gift;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.gift.Gift;
import henry.greenwich.fitness.service.gift.GiftService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/gift-management")
public class GiftController {
    private GiftService giftService;

    /**
     * @param giftService - inject giftService
     */
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    /**
     * @param response - response to add number of pages and number of gifts to header
     * @param search   - gift's title's keywords that user want to get gifts (this parameter could be optional)
     * @param page     - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    @GetMapping(value = "/gifts", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Gift> getNewFeeds(HttpServletResponse response,
                                  @RequestParam(required = false) String search,
                                  @RequestParam(required = false) Integer page) {
        if (page != null) {
            return this.getGiftsPaging(response, search, page);
        }
        return this.giftService.getGifts(search);
    }

    /**
     * @param response - response to add number of pages and number of gifts to header
     * @param search   - gift's title's keywords that user want to get gifts (this parameter could be optional)
     * @param page     - start index (for pagination) (this parameter could be optional)
     * @return list of gifts
     */
    private List<Gift> getGiftsPaging(HttpServletResponse response, String search, Integer page) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nGifts = this.giftService.getNumberOfGifts(search);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nGifts));
        int nPages = nGifts > 0 ? (nGifts >= Constants.NUMBER_ITEMS_PER_PAGE ? nGifts / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.giftService.getGiftsByPage(search, startIndex - 1);
    }
}
