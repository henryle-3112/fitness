package henry.greenwich.fitness.controller.gallery;

import henry.greenwich.fitness.model.gallery.Gallery;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.gallery.GalleryService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class GalleryController {
    /**
     * galleryService - interact with gallery's data
     * userProfileService - interact with user's profile data
     */
    private GalleryService galleryService;
    private UserProfileService userProfileService;

    /**
     * @param galleryService - inject galleryService
     */
    public GalleryController(GalleryService galleryService, UserProfileService userProfileService) {
        this.galleryService = galleryService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of galleries
     */
    @GetMapping(value = "/galleries", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Gallery> getGallery(@RequestParam Optional<String> search) {
        return this.galleryService.getGalleries();
    }

    /**
     * @param id - id of gallery that user want to get
     * @return - list of gallery
     */
    @GetMapping(value = "/galleries/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Gallery getGallery(@PathVariable Long id) {
        return this.galleryService.getGallery(id);
    }

    /**
     * @param gallery - gallery that user want to add
     * @return inserted gallery
     */
    @PostMapping(value = "/galleries/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Gallery addGallery(@RequestBody Gallery gallery) {
        return this.galleryService.addGallery(gallery);
    }

    /**
     * @param gallery - gallery that user want to update
     * @return updated gallery
     */
    @PostMapping(value = "/galleries/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Gallery updateGallery(@RequestBody Gallery gallery) {
        return this.galleryService.updateGallery(gallery);
    }

    /**
     * @param id - id of gallery that user want to delete
     */
    @PostMapping(value = "/galleries/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteGallery(@PathVariable Long id) {
        this.galleryService.deleteGallery(id);
    }


    @GetMapping(value = "/galleries/paging/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Gallery> getGalleriesByPageAndKeywords(@PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Gallery> galleries = new ArrayList<>();
        List<Object> galleriesObject = this.galleryService.findGalleriesByPage(selectedKeyWord, startIndex - 1);
        for (int i = 0; i < galleriesObject.size(); i++) {
            Object[] eachGalleryObject = (Object[]) galleriesObject.get(i);
            int id = (int) eachGalleryObject[0];
            String image = (String) eachGalleryObject[1];
            String title = (String) eachGalleryObject[2];
            Date createdDate = (Date) eachGalleryObject[3];
            Date modifiedDate = (Date) eachGalleryObject[4];
            int status = (int) eachGalleryObject[5];
            String thumbnail = (String) eachGalleryObject[6];
            int userProfileId = (int) eachGalleryObject[7];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            Gallery gallery = new Gallery(
                    (long) id,
                    image,
                    title,
                    createdDate,
                    modifiedDate,
                    status,
                    thumbnail,
                    userProfile
            );
            galleries.add(gallery);
        }
        return galleries;
    }

    /**
     * @param keyword - number of galleries based on keywords
     * @return number of galleries
     */
    @GetMapping(value = "/galleries/count", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countGalleries(@RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countGalleriesObject = this.galleryService.countGalleries(selectedKeyWord);
        Object eachCountGallery = countGalleriesObject.get(0);
        return new ResponseMessage(eachCountGallery.toString());
    }

}

