package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRateRepository extends JpaRepository<ProductRate, Long> {
    /**
     * @param id - product's rate's id that user want to get
     * @return product's rate
     */
    ProductRate findProductRateById(Long id);

    /**
     *
     * @param userId - user's id
     * @param productId - product's id
     * @return list of products
     */
    @Query(nativeQuery = true, value = "select * from product_rate where user_profile_id = :userId and product_id = :productId")
    List<Object> getProductRateByUserIdAndByProductId(Long userId, Long productId);
}
