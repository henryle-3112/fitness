package henry.greenwich.fitness.repository.gallery;

import henry.greenwich.fitness.model.gallery.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    /**
     *
     * @param id - gallery's id that user want to get
     * @return selected gallery
     */
    Gallery findGalleryById(Long id);

    /**
     *
     * @param keyword - keyword when user want to search
     * @param startIndex - start index that user want to view galleries
     * @return list of galleries
     */
    @Query(nativeQuery = true, value = "select * from gallery where lower(gallery.title) like :keyword limit :startIndex, 8")
    List<Object> findGalleriesByPage(String keyword, int startIndex);

    /**
     *
     * @param keyword - keyword that user want to get
     * @return number of galleries
     */
    @Query(nativeQuery = true, value = "select count(*) from gallery where lower(gallery.title) like :keyword")
    List<Object> countGalleries(String keyword);

}
