package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.product.ProductOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, Long> {
    /**
     * @param id - product's order's detail's id that user want to get
     * @return list of product's order's details
     */
    ProductOrderDetail findProductOrderDetailById(Long id);

    /**
     * @param productOrder - product's order
     * @return list of order's details
     */
    List<ProductOrderDetail> findProductOrderDetailsByProductOrder(ProductOrder productOrder);
}
