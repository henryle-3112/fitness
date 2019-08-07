package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductCategory;
import henry.greenwich.fitness.repository.product.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    /**
     * @param productCategoryRepository - inject productCategoryRepository
     */
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * @param productCategoryStatus - product's category's status that user want to get list of product's categories
     * @return list of product's categories
     */
    public List<ProductCategory> getProductCategories(Integer productCategoryStatus) {
        if (productCategoryStatus != null) {
            return this.productCategoryRepository.findProductCategoriesByProductCategoryStatus(productCategoryStatus);
        }
        return this.productCategoryRepository.findAll();
    }

    /**
     * @param id - product's category's id that user want to get selected product's category
     * @return list of product's categories
     */
    public ProductCategory getProductCategory(Long id) {
        return this.productCategoryRepository.findProductCategoryById(id);
    }
}
