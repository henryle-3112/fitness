package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * @param id     - product's id that user want to get
     * @param status - product's status that user want to get
     * @return selected product
     */
    Product findProductByIdAndProductStatus(Long id, int status);

    /**
     * @param status - product's status that user want to get
     * @return list of products
     */
    List<Product> findProductsByProductStatus(int status);

    /**
     * @param categoryId - product's category's id
     * @return list of products
     */
    @Query(nativeQuery = true, value = "select * from product where product_status = :status and product_category_id = :categoryId and product_quantity > 0 limit :top")
    List<Object> findTopProductsByCategoryAndProductStatus(int categoryId, int top, int status);

    /**
     * @param categoryId - category's id that product belong to
     * @param startIndex - start index
     * @param status     - status
     * @return list of products
     */
    @Query(nativeQuery = true, value = "select * from product where product_category_id = :categoryId and product_status = :status and product_quantity > 0 limit :startIndex, 8")
    List<Object> findProductsByCategoryAndByPage(int categoryId, int startIndex, int status);

    /**
     * @param categoryId - category's id that products belong to
     * @param status     - product's status
     * @return number of products
     */
    @Query(nativeQuery = true, value = "select count(*) from product where product_category_id = :categoryId and product_status = :status and product_quantity > 0")
    List<Object> countProductsByCategoryAndByProductStatus(int categoryId, int status);


    /**
     * @param categoryId          - category's id that products belong to
     * @param productMinPrice     - product's min price
     * @param productMaxPrice     - product's max price
     * @param productNameKeywords - products' name keywords
     * @param status              - status
     * @return number of products
     */
    @Query(nativeQuery = true, value = "select count(*) from product where (:categoryId is null or product_category_id = :categoryId) and (:productMinPrice is null or product_price >= :productMinPrice) and (:productMaxPrice is null or product_price <= :productMaxPrice) and product_status = :status and (:productNameKeywords is null or product_name like :productNameKeywords) and product_quantity > 0")
    List<Object> countSearchingProducts(@Param("categoryId") Integer categoryId,
                                        @Param("productMinPrice") Integer productMinPrice,
                                        @Param("productMaxPrice") Integer productMaxPrice,
                                        @Param("productNameKeywords") String productNameKeywords,
                                        @Param("status") int status);

    /**
     * @param selectedCategoryId         - category's id that products belong to
     * @param selectedProductMinPrice    - product's min price
     * @param selectedProductMaxPrice    - product's max price
     * @param selectedProductNameKeyword - product's name keywords
     * @param startIndex                 - start index
     * @param status                     - status
     * @return list of products
     */
    @Query(nativeQuery = true, value = "select * from product where (:selectedCategoryId is null or product_category_id = :selectedCategoryId) and (:selectedProductMinPrice is null or product_price >= :selectedProductMinPrice) and (:selectedProductMaxPrice is null or product_price <= :selectedProductMaxPrice) and product_status = :status and (:selectedProductNameKeyword is null or product_name like :selectedProductNameKeyword) and product_quantity > 0 limit :startIndex, 8")
    List<Object> findSearchingProductsByPage(@Param("selectedCategoryId") Integer selectedCategoryId,
                                             @Param("selectedProductMinPrice") Integer selectedProductMinPrice,
                                             @Param("selectedProductMaxPrice") Integer selectedProductMaxPrice,
                                             @Param("selectedProductNameKeyword") String selectedProductNameKeyword,
                                             @Param("startIndex") int startIndex,
                                             @Param("status") int status);

    /**
     * @param id - product's id
     * @return selected product
     */
    Product findProductById(Long id);
}
