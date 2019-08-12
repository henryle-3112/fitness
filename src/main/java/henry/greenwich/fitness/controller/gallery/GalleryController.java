package henry.greenwich.fitness.controller.gallery;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.gallery.Gallery;
import henry.greenwich.fitness.service.gallery.GalleryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("gallery-management")
public class GalleryController {
    private GalleryService galleryService;

    /**
     * @param galleryService - inject galleryService
     */
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    /**
     * @param response - response to add number of galleries and number of pages to
     *                 header
     * @param status   - gallery's status that user want to get galleries
     * @param search   - gallery's title's keywords that user want to get galleries
     * @param page     - start index (for pagination)
     * @return list of galleries
     */
    @GetMapping(value = "/galleries", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Gallery> getGalleries(HttpServletResponse response,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer status,
                                      @RequestParam(required = false) String search) {
        if (page != null) {
            return this.getGalleriesPaging(response, page, status, search);
        }
        return this.galleryService.getGalleries(status, search);
    }

    /**
     * @param response - response to add number of galleries and number of pages to
     *                 header
     * @param status   - gallery's status that user want to get galleries
     * @param search   - gallery's title's keywords that user want to get galleries
     * @param page     - start index (for pagination)
     * @return list of galleries
     */
    private List<Gallery> getGalleriesPaging(HttpServletResponse response, Integer page, Integer status,
                                             String search) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nGalleries = this.galleryService.getNumberOfGalleries(status, search);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nGalleries));
        int nPages = nGalleries > 0 ? (nGalleries >= Constants.NUMBER_ITEMS_PER_PAGE ? nGalleries / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.galleryService.getGalleriesByPage(startIndex - 1, status, search);
    }

}
