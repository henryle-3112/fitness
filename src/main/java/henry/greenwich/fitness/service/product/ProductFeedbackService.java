package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductFeedback;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ProductFeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductFeedbackService {
    /**
     * productFeedbackRepository - interact with product feedback's data
     */
    private ProductFeedbackRepository productFeedbackRepository;

    /**
     * @param productFeedbackRepository - inject productFeedbackRepository
     */
    public ProductFeedbackService(ProductFeedbackRepository productFeedbackRepository) {
        this.productFeedbackRepository = productFeedbackRepository;
    }

    /**
     * @param product - that user wants to get feedback
     * @return list of feedbacks
     */
    public List<ProductFeedback> getProductFeedbacksByProduct(Product product, int status) {
        return this.productFeedbackRepository.findProductFeedbacksByProductAndFeedbackStatus(product, status);
    }

    /**
     * @param productFeedback - that user wants to add to the database
     * @return productFeedback - that was inserted to the database
     */
    public ProductFeedback addProductFeedback(ProductFeedback productFeedback) {
        return this.productFeedbackRepository.saveAndFlush(productFeedback);
    }


    /**
     * @param id - product's feedback's id that user want to delete
     */
    public void deleteProductFeedback(Long id) {
        this.productFeedbackRepository.deleteById(id);
    }
}
