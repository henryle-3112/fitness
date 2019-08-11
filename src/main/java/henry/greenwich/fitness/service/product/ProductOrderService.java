package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.repository.product.ProductOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderService {
    /**
     * productOrderRepository - interact with product's order
     */
    private ProductOrderRepository productOrderRepository;

    /**
     * @param productOrderRepository - inject productOrderRepository
     */
    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    /**
     * @return list of product's orders
     */
    public List<ProductOrder> getProductOrders() {
        return this.productOrderRepository.findAll();
    }

    /**
     * @param id - product's order's id that user want to get
     * @return selected product's order
     */
    public ProductOrder getProductOrder(Long id) {
        return this.productOrderRepository.findProductOrderById(id);
    }

    /**
     * @param productOrder - product's order that user want to add to the database
     * @return productOrder - that was inserted to the database
     */
    public ProductOrder addProductOrder(ProductOrder productOrder) {
        return this.productOrderRepository.saveAndFlush(productOrder);
    }

    /**
     * @param productOrder - product's order that user want to update to the database
     * @return productOrder - that was inserted to the database
     */
    public ProductOrder updateProductOrder(ProductOrder productOrder) {
        return this.productOrderRepository.saveAndFlush(productOrder);
    }

    /**
     * @param id - product's id that user want to delete
     */
    public void deleteProductOrder(Long id) {
        this.productOrderRepository.deleteById(id);
    }

    /**
     * @param userProfileId      - user profile id
     * @param productOrderStatus - product order status
     * @param startIndex         - start index
     * @return list of product orders
     */
    public List<Object> findProductOrdersByUserProfileIdAndProductOrderStatus(int userProfileId, int productOrderStatus, int startIndex) {
        return this.productOrderRepository.findProductOrdersByUserProfileIdAndProductOrderStatus(
                userProfileId,
                productOrderStatus,
                startIndex
        );
    }

    /**
     * @param userProfileId      - user profile id
     * @param productOrderStatus - product order status
     * @return number of product orders
     */
    public List<Object> countNumberOfProductOrdersByUserProfileIdAndProductOrderStatus(int userProfileId, int productOrderStatus) {
        return this.productOrderRepository.countNumberOfProductOrdersByUserProfileIdAndProductOrderStatus(userProfileId, productOrderStatus);
    }
}
