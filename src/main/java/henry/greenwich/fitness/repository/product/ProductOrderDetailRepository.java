package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.product.ProductOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, Long> {

    /**
     * @param productOrder - product's order that user want to get list of product's
     *                     order's details
     * @return list of product's order's details
     */
    List<ProductOrderDetail> getProductOrderDetailsByProductOrder(ProductOrder productOrder);
}
