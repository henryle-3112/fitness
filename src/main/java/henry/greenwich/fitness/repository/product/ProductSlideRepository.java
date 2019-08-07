package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSlideRepository extends JpaRepository<ProductSlide, Long> {

    /**
     * @param productSlideStatus - product's slide's status that user want to get
     *                           list of product's slides
     * @return list of product's slides
     */
    List<ProductSlide> findProductSlidesByProductSlideStatus(Integer productSlideStatus);
}
