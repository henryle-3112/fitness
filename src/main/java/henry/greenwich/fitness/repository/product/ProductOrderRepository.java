package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    /**
     * @param id - product's order's id that user want to get
     * @return selected product's order
     */
    ProductOrder findProductOrderById(Long id);

    /**
     *
     * @param userProfileId - user profile id
     * @param productOrderStatus - product order staus
     * @return list of product orders by user profile and product order staus
     */
    @Query(nativeQuery = true, value = "select * from product_order where user_profile_id = :userProfileId and product_order_status = :productOrderStatus limit :startIndex, 8")
    List<Object> findProductOrdersByUserProfileIdAndProductOrderStatus(int userProfileId, int productOrderStatus, int startIndex);

    /**
     *
     * @param userProfileId - user profile id
     * @param productOrderStatus - product order status
     * @return number of orders
     */
    @Query(nativeQuery = true, value = "select count(*) from product_order where user_profile_id = :userProfileId and product_order_status = :productOrderStatus")
    List<Object> countNumberOfProductOrdersByUserProfileIdAndProductOrderStatus(int userProfileId, int productOrderStatus);
}
