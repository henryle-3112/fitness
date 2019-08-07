package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

        String GET_PRODUCT_ORDERS_PAGING = "select * from " + Constants.PRODUCT_ORDER_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.PRODUCT_ORDER_TABLE + "."
                        + Constants.PRODUCT_ORDER_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:productOrderStatus is null or " + Constants.PRODUCT_ORDER_TABLE + "."
                        + Constants.PRODUCT_ORDER_PRODUCT_ORDER_STATUS + " = :productOrderStatus)"
                        + " limit :startIndex, " + Constants.NUMBER_ITEMS_PER_PAGE;

        String GET_PRODUCT_ORDERS = "select * from " + Constants.PRODUCT_ORDER_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.PRODUCT_ORDER_TABLE + "."
                        + Constants.PRODUCT_ORDER_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:productOrderStatus is null or " + Constants.PRODUCT_ORDER_TABLE + "."
                        + Constants.PRODUCT_ORDER_PRODUCT_ORDER_STATUS + " = :productOrderStatus)";

        String GET_NUMBER_OF_PRODUCT_ORDERS = "select count(*) from " + Constants.PRODUCT_ORDER_TABLE + ""
                        + " where (:userProfileId is null or " + Constants.PRODUCT_ORDER_TABLE + "."
                        + Constants.PRODUCT_ORDER_USER_PROFILE_ID + " = :userProfileId)"
                        + " and (:productOrderStatus is null or " + Constants.PRODUCT_ORDER_TABLE + "."
                        + Constants.PRODUCT_ORDER_PRODUCT_ORDER_STATUS + " = :productOrderStatus)";

        /**
         * @param id - product's order's id that user want to get selected product's
         *           order
         * @return selected product's order
         */
        ProductOrder findProductOrderById(Long id);

        /**
         * @param userProfileId      - user's profile's id that user want to get list of
         *                           product's orders (this parameter could be optional)
         * @param productOrderStatus - product's order's status that user want to get
         *                           list of product's orders (this parameter could be
         *                           optional)
         * @param startIndex         - start index (for pagination) (this parameter
         *                           could be optional)
         * @return list of product's orders
         */
        @Query(nativeQuery = true, value = GET_PRODUCT_ORDERS_PAGING)
        List<Object> getProductOrdersPaging(Integer userProfileId, Integer productOrderStatus, Integer startIndex);

        /**
         * @param userProfileId      - user's profile's id that user want to get list of
         *                           product's orders (this parameter could be optional)
         * @param productOrderStatus - product's order's status that user want to get
         *                           list of product's orders (this parameter could be
         *                           optional)
         * @return list of product's orders
         */
        @Query(nativeQuery = true, value = GET_PRODUCT_ORDERS)
        List<Object> getProductOrders(Integer userProfileId, Integer productOrderStatus);

        /**
         * @param userProfileId      - user's profile's id that user want to get number
         *                           of product's orders (this parameter could be
         *                           optional)
         * @param productOrderStatus - product's order's status that user want to get
         *                           number of product's orders (this parameter could be
         *                           optional)
         * @return number of product's orders
         */
        @Query(nativeQuery = true, value = GET_NUMBER_OF_PRODUCT_ORDERS)
        List<Object> getNumberOfProductOrders(Integer userProfileId, Integer productOrderStatus);

}
