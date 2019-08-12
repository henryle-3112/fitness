package henry.greenwich.fitness.service.gallery;

import henry.greenwich.fitness.model.gallery.Gallery;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.gallery.GalleryRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GalleryService {
    private GalleryRepository galleryRepository;
    private UserProfileService userProfileService;

    /**
     * @param galleryRepository  - inject galleryRepository
     * @param userProfileService - inject userProfileService
     */
    public GalleryService(GalleryRepository galleryRepository, UserProfileService userProfileService) {
        this.galleryRepository = galleryRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param galleryStatus        - gallery's status that user want to get
     *                             galleries
     * @param galleryTitleKeywords - gallery's title's keywords that user want to
     *                             get galleries
     * @param startIndex           - start index (for pagination)
     * @return list of galleries
     */
    public List<Gallery> getGalleriesByPage(Integer startIndex, Integer galleryStatus, String galleryTitleKeywords) {
        List<Object> galleriesObjectList = this.galleryRepository.getGalleriesByPage(galleryStatus,
                galleryTitleKeywords, startIndex);
        return this.getGalleriesFromObjectList(galleriesObjectList);
    }

    /**
     * @param galleryStatus        - gallery's status that user want to get
     *                             galleries
     * @param galleryTitleKeywords - gallery's title's keywords that user want to
     *                             get galleries
     * @return list of galleries
     */
    public List<Gallery> getGalleries(Integer galleryStatus, String galleryTitleKeywords) {
        List<Object> galleriesObjectList = this.galleryRepository.getGalleries(galleryStatus, galleryTitleKeywords);
        return this.getGalleriesFromObjectList(galleriesObjectList);
    }

    /**
     * @param galleriesObjectList - galleries object list that user want to convert
     *                            to galleries list
     * @return list of galleries
     */
    private List<Gallery> getGalleriesFromObjectList(List<Object> galleriesObjectList) {
        List<Gallery> galleries = new ArrayList<>();
        for (Object o : galleriesObjectList) {
            Object[] galleryObjectArr = (Object[]) o;
            Gallery gallery = this.createGalleryFromObjectList(galleryObjectArr);
            galleries.add(gallery);
        }
        return galleries;
    }

    /**
     * @param galleryObjectArr - gallery object array that user want to convert to
     *                         gallery object
     * @return selected gallery
     */
    private Gallery createGalleryFromObjectList(Object[] galleryObjectArr) {
        int id = (int) galleryObjectArr[0];
        String image = (String) galleryObjectArr[1];
        String title = (String) galleryObjectArr[2];
        Date createdDate = (Date) galleryObjectArr[3];
        Date modifiedDate = (Date) galleryObjectArr[4];
        int status = (int) galleryObjectArr[5];
        String thumbnail = (String) galleryObjectArr[6];
        int userProfileId = (int) galleryObjectArr[7];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new Gallery((long) id, image, title, createdDate, modifiedDate, status, thumbnail, userProfile);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get user's
     *                      profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param galleryStatus        - gallery's status that user want to get
     *                             galleries
     * @param galleryTitleKeywords - gallery's title's keywords that user want to
     *                             get galleries
     * @return number of galleries
     */
    public int getNumberOfGalleries(Integer galleryStatus, String galleryTitleKeywords) {
        List<Object> nGalleriesObjectList = this.galleryRepository.getNumberOfGalleries(galleryStatus,
                galleryTitleKeywords);
        if (nGalleriesObjectList.size() > 0) {
            return Integer.valueOf(nGalleriesObjectList.get(0).toString());
        }
        return 0;
    }

}
