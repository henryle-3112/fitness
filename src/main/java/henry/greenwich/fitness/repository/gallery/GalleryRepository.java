package henry.greenwich.fitness.repository.gallery;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.gallery.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    String GET_GALLERIES_BY_PAGE = "select * from " + Constants.GALLERY_TABLE + "" +
            " where (:galleryStatus is null or " + Constants.GALLERY_TABLE + "." + Constants.GALLERY_STATUS + " = :galleryStatus)" +
            " and (:galleryTitleKeywords is null or lower(" + Constants.GALLERY_TABLE + "." + Constants.GALLERY_TITLE + ") like %:galleryTitleKeywords%) limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

    String GET_NUMBER_OF_GALLERIES = "select count(*) from " + Constants.GALLERY_TABLE + "" +
            " where (:galleryStatus is null or " + Constants.GALLERY_TABLE + "." + Constants.GALLERY_STATUS + " = :galleryStatus)" +
            " and (:galleryTitleKeywords is null or lower(" + Constants.GALLERY_TABLE + "." + Constants.GALLERY_TITLE + ") like %:galleryTitleKeywords%)";

    String GET_GALLERIES = "select * from " + Constants.GALLERY_TABLE + "" +
            " where (:galleryStatus is null or " + Constants.GALLERY_TABLE + "." + Constants.GALLERY_STATUS + " = :galleryStatus)" +
            " and (:galleryTitleKeywords is null or lower(" + Constants.GALLERY_TABLE + "." + Constants.GALLERY_TITLE + ") like %:galleryTitleKeywords%)";

    /**
     * @param galleryStatus        - gallery's status that user want to get galleries
     * @param galleryTitleKeywords - gallery's title's keywords that user want to get galleries
     * @param startIndex           - start index (for pagination)
     * @return list of galleries
     */
    @Query(nativeQuery = true, value = GET_GALLERIES_BY_PAGE)
    List<Object> getGalleriesByPage(Integer galleryStatus, String galleryTitleKeywords, Integer startIndex);

    /**
     * @param galleryStatus        - gallery's status that user want to get galleries
     * @param galleryTitleKeywords - gallery's title's keywords that user want to get galleries
     * @return number of galleries
     */
    @Query(nativeQuery = true, value = GET_NUMBER_OF_GALLERIES)
    List<Object> getNumberOfGalleries(Integer galleryStatus, String galleryTitleKeywords);

    /**
     * @param galleryStatus        - gallery's status that user want to get galleries
     * @param galleryTitleKeywords - gallery's title's keywords that user want to get galleries
     * @return list of galleries
     */
    @Query(nativeQuery = true, value = GET_GALLERIES)
    List<Object> getGalleries(Integer galleryStatus, String galleryTitleKeywords);

}
