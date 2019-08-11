package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductCategory;
import henry.greenwich.fitness.repository.product.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    /**
     * productCategoryRepository - interact with product's category's data
     */
    private ProductCategoryRepository productCategoryRepository;

    /**
     * @param productCategoryRepository - inject productCategoryRepository
     */
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * @return list of product's categories
     */
    public List<ProductCategory> getProductCategories(int status) {
        return this.productCategoryRepository.findProductCategoriesByProductCategoryStatus(status);
    }

    /**
     * @param id - product's category's id
     * @return selected product's category
     */
    public ProductCategory getProductCategory(Long id, int status) {
        return this.productCategoryRepository.findProductCategoryByIdAndProductCategoryStatus(id, status);
    }

    /**
     * @param id - product's category's id
     * @return selected product's category
     */
    public ProductCategory getProductCategory(Long id) {
        return this.productCategoryRepository.findProductCategoryById(id);
    }

    /**
     * @param productCategory - that user want to add to the database
     * @return productCategory that was inserted to the database
     */
    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return this.productCategoryRepository.saveAndFlush(productCategory);
    }

    /**
     * @param productCategory - that user want to update to the database
     * @return productCategory that was updated to the database
     */
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return this.productCategoryRepository.saveAndFlush(productCategory);
    }

    /**
     * @param id - id of product's category that user want to delete
     */
    public void deleteProductCategory(Long id) {
        this.productCategoryRepository.deleteById(id);
    }
}
