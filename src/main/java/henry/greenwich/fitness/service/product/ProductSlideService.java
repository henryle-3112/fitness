package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductSlide;
import henry.greenwich.fitness.repository.product.ProductSlideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSlideService {
    /**
     * productSlideRepository - interact with product's slide's data
     */
    private ProductSlideRepository productSlideRepository;

    /**
     * @param productSlideRepository - inject product's slide's repository
     */
    public ProductSlideService(ProductSlideRepository productSlideRepository) {
        this.productSlideRepository = productSlideRepository;
    }

    /**
     * @return list of product's slides
     */
    public List<ProductSlide> getProductSlides(int status) {
        return this.productSlideRepository.findProductSlidesByProductSlideStatus(status);
    }

    /**
     * @param id - product's slide's id that user want to get
     * @return selected product's slide
     */
    public ProductSlide getProductSlide(Long id, int status) {
        return this.productSlideRepository.findProductSlideByIdAndProductSlideStatus(id, status);
    }

    /**
     * @param productSlide - that user want to add to the database
     * @return productSlide that was inserted to the database
     */
    public ProductSlide addProductSlide(ProductSlide productSlide) {
        return this.productSlideRepository.saveAndFlush(productSlide);
    }

    /**
     * @param productSlide - that user want to update to the database
     * @return productSlide tha was inserted to the database
     */
    public ProductSlide updateProductSlide(ProductSlide productSlide) {
        return this.productSlideRepository.saveAndFlush(productSlide);
    }

    /**
     * @param id - product's slide's id that user want to delete
     */
    public void deleteProductSlide(Long id) {
        this.productSlideRepository.deleteById(id);
    }
}
