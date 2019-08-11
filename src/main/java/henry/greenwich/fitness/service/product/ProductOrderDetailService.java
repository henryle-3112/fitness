package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.product.ProductOrderDetail;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.repository.product.ProductOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderDetailService {
    /**
     * productOrderDetailRepository - interact with product's order's detail
     */
    private ProductOrderDetailRepository productOrderDetailRepository;

    /**
     * @param productOrderDetailRepository - inject productOrderDetailRepository
     */
    public ProductOrderDetailService(ProductOrderDetailRepository productOrderDetailRepository) {
        this.productOrderDetailRepository = productOrderDetailRepository;
    }

    /**
     * @return list of product's order's details
     */
    public List<ProductOrderDetail> getProductOrderDetails() {
        return this.productOrderDetailRepository.findAll();
    }

    /**
     * @param id - product's order's detail's id that user want to get
     * @return selected product's order's detail
     */
    public ProductOrderDetail getProductOrderDetail(Long id) {
        return this.productOrderDetailRepository.findProductOrderDetailById(id);
    }

    /**
     * @param productOrderDetails - list of product order details that user want to add to the database
     * @return list of product order details
     */
    public List<ProductOrderDetail> addProductOrderDetail(ArrayList<ProductOrderDetail> productOrderDetails) {
        // list of product order details that were inserted to the database
        ArrayList<ProductOrderDetail> insertedProductOrderDetails = new ArrayList<>();
        for (ProductOrderDetail eachProductOrderDetail : productOrderDetails) {
            // add each product order detail to the database
            ProductOrderDetail insertedProductOrderDetail = this.productOrderDetailRepository.saveAndFlush(eachProductOrderDetail);
            if (insertedProductOrderDetail != null) {
                // add each result to list
                insertedProductOrderDetails.add(insertedProductOrderDetail);
            }
        }
        // return result
        if (insertedProductOrderDetails.size() == productOrderDetails.size()) {
            return insertedProductOrderDetails;
        }
        return null;
    }

    /**
     * @param productOrderDetail - product's order's detail that user want to update to the database
     * @return product's order's detail that was updated to the database
     */
    public ProductOrderDetail updateProductOrderDetail(ProductOrderDetail productOrderDetail) {
        return this.productOrderDetailRepository.saveAndFlush(productOrderDetail);
    }

    /**
     * @param id - product's order's detail's id that user want to delete
     */
    public void deleteProductOrderDetail(Long id) {
        this.productOrderDetailRepository.deleteById(id);
    }

    /**
     * @param productOrder - product's order
     * @return list of product's order's details
     */
    public List<ProductOrderDetail> findProductOrderDetailsByProductOrder(ProductOrder productOrder) {
        return this.productOrderDetailRepository.findProductOrderDetailsByProductOrder(productOrder);
    }
}
