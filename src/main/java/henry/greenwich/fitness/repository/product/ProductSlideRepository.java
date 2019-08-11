package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSlideRepository extends JpaRepository<ProductSlide, Long> {

    /**
     * @param id - product's slide that user want to get
     * @param status - product's slide's status that user want to get
     * @return selected product's slide
     */
    ProductSlide findProductSlideByIdAndProductSlideStatus(Long id, int status);

    /**
     *
     * @param status - product's slide's status that user want to get
     * @return selected product's slide
     */
    List<ProductSlide> findProductSlidesByProductSlideStatus(int status);
}
