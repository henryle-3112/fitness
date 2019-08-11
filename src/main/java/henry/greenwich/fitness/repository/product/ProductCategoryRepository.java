package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    /**
     * @param id - product's category's id that user want to get
     * @return selected product's category
     */
    ProductCategory findProductCategoryByIdAndProductCategoryStatus(Long id, int status);

    /**
     * @param status - status of categories that user want to get (normal is 1)
     * @return list of product's category
     */
    List<ProductCategory> findProductCategoriesByProductCategoryStatus(int status);

    /**
     * @param id - product's category's id
     * @return selected post's category
     */
    ProductCategory findProductCategoryById(Long id);
}
