package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductRate;
import henry.greenwich.fitness.repository.product.ProductRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRateService {
    /**
     * productRateRepository - interact with product's rate
     */
    private ProductRateRepository productRateRepository;

    /**
     * @param productRateRepository - inject productRateRepository
     */
    public ProductRateService(ProductRateRepository productRateRepository) {
        this.productRateRepository = productRateRepository;
    }

    /**
     * @return list of product's rates
     */
    public List<ProductRate> getProductRates() {
        return this.productRateRepository.findAll();
    }

    /**
     * @param id - product's rate's id that user want to get
     * @return list of product's rate
     */
    public ProductRate getProductRate(Long id) {
        return this.productRateRepository.findProductRateById(id);
    }

    /**
     * @param productRate - that user want to add to the database
     * @return productRate - that was inserted to the database
     */
    public ProductRate addProductRate(ProductRate productRate) {
        // check productRate existed in the database or not
        // if not, create new one, if yes, just update
        List<Object> productRateObjectList = this.productRateRepository.getProductRateByUserIdAndByProductId(
                productRate.getUserProfile().getId(),
                productRate.getProduct().getId()
        );
        if (productRateObjectList.isEmpty()) {
            return this.productRateRepository.saveAndFlush(productRate);
        }
        Object[] productRateObjectArray = (Object[]) productRateObjectList.get(0);
        int productRateId = (int) productRateObjectArray[0];
        productRate.setId((long) productRateId);
        return this.productRateRepository.saveAndFlush(productRate);
    }

    /**
     * @param productRate - that user want to update to the database
     * @return productRate- tha was updated to the database
     */
    public ProductRate updateProductRate(ProductRate productRate) {
        return this.productRateRepository.saveAndFlush(productRate);
    }

    /**
     * @param id - product's rate's id that user want to delete
     */
    public void deleteProductRate(Long id) {
        this.productRateRepository.deleteById(id);
    }

    /**
     * @param userId    - user's id
     * @param productId - product's id
     * @return list of product's rates
     */
    public List<Object> getProductRateByUserIdAndProductId(Long userId, Long productId) {
        return this.productRateRepository.getProductRateByUserIdAndByProductId(userId, productId);
    }
}
