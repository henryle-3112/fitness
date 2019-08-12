package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.product.ProductOrderDetail;
import henry.greenwich.fitness.repository.product.ProductOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderDetailService {
    private ProductOrderDetailRepository productOrderDetailRepository;
    private ProductOrderService productOrderService;

    /**
     * @param productOrderDetailRepository - inject productOrderDetailRepository
     */
    public ProductOrderDetailService(ProductOrderDetailRepository productOrderDetailRepository,
                                     ProductOrderService productOrderService) {
        this.productOrderDetailRepository = productOrderDetailRepository;
        this.productOrderService = productOrderService;
    }

    /**
     * @param productOrderDetails - list of product order details that user want to
     *                            add to the database
     * @return list of product order details
     */
    public List<ProductOrderDetail> addProductOrderDetail(ArrayList<ProductOrderDetail> productOrderDetails) {
        ArrayList<ProductOrderDetail> insertedProductOrderDetails = new ArrayList<>();
        for (ProductOrderDetail eachProductOrderDetail : productOrderDetails) {
            ProductOrderDetail insertedProductOrderDetail = this.productOrderDetailRepository
                    .saveAndFlush(eachProductOrderDetail);
            if (insertedProductOrderDetail != null) {
                insertedProductOrderDetails.add(insertedProductOrderDetail);
            }
        }
        if (insertedProductOrderDetails.size() == productOrderDetails.size()) {
            return insertedProductOrderDetails;
        }
        return null;
    }

    /**
     * @param productOrderId - product's order's id that user want to get list of
     *                       product's order's details
     * @return list of product's order's details
     */
    public List<ProductOrderDetail> getProductOrderDetailsByProductOrder(Integer productOrderId) {
        ProductOrder productOrder = this.getProductOrder((long) productOrderId);
        return this.productOrderDetailRepository.getProductOrderDetailsByProductOrder(productOrder);
    }

    /**
     * @param productOrderId - product's order's id that user want to get selected
     *                       product's order
     * @return selected product's order
     */
    private ProductOrder getProductOrder(Long productOrderId) {
        return this.productOrderService.getProductOrder(productOrderId);
    }
}
