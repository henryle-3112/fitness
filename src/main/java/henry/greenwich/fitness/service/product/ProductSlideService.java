package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductSlide;
import henry.greenwich.fitness.repository.product.ProductSlideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSlideService {
    private ProductSlideRepository productSlideRepository;

    /**
     * @param productSlideRepository - inject product's slide's repository
     */
    public ProductSlideService(ProductSlideRepository productSlideRepository) {
        this.productSlideRepository = productSlideRepository;
    }

    /**
     * @param productSlideStatus - product's slide's status that user want to get
     *                           list of product's slides
     * @return list of product's slides
     */
    public List<ProductSlide> getProductSlides(Integer productSlideStatus) {
        if (productSlideStatus != null) {
            return this.productSlideRepository.findProductSlidesByProductSlideStatus(productSlideStatus);
        }
        return this.productSlideRepository.findAll();
    }
}
