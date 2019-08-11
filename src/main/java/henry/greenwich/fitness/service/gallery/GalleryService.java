package henry.greenwich.fitness.service.gallery;

import henry.greenwich.fitness.model.gallery.Gallery;
import henry.greenwich.fitness.repository.gallery.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {
    /**
     * galleryRepository - interact with gallery's data
     */
    private GalleryRepository galleryRepository;

    /**
     *
     * @param galleryRepository - inject galleryRepository
     */
    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    /**
     *
     * @return list of galleries
     */
    public List<Gallery> getGalleries() {
        return this.galleryRepository.findAll();
    }

    /**
     *
     * @param id - gallery's id
     * @return selected gallery
     */
    public Gallery getGallery(Long id) {
        return this.galleryRepository.findGalleryById(id);
    }

    /**
     *
     * @param gallery - gallery that user want to add
     * @return selected gallery
     */
    public Gallery addGallery(Gallery gallery) {
        return this.galleryRepository.saveAndFlush(gallery);
    }

    /**
     *
     * @param gallery - gallery that user want to update
     * @return - selected gallery
     */
    public Gallery updateGallery(Gallery gallery) {
        return this.galleryRepository.saveAndFlush(gallery);
    }

    /**
     *
     * @param id - id of gallery
     */
    public void deleteGallery(Long id) {
        this.galleryRepository.deleteById(id);
    }

    /**
     *
     * @param startIndex - start index that user want to view
     * @param keyword - keyword that user want to get galleries
     * @return list of galleries
     */
    public List<Object> findGalleriesByPage(String keyword, int startIndex) {
        return this.galleryRepository.findGalleriesByPage(keyword, startIndex);
    }

    public List<Object> countGalleries(String keyword) {
        return this.galleryRepository.countGalleries(keyword);
    }

}
