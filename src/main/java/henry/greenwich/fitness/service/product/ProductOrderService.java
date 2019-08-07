package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.ProductOrder;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.product.ProductOrderRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductOrderService {
    private ProductOrderRepository productOrderRepository;
    private UserProfileService userProfileService;

    /**
     * @param productOrderRepository - inject productOrderRepository
     * @param userProfileService     - inject userProfileService
     */
    public ProductOrderService(ProductOrderRepository productOrderRepository, UserProfileService userProfileService) {
        this.productOrderRepository = productOrderRepository;
        this.userProfileService = userProfileService;
    }

    /**
     * @param productOrder - product's order that user want to add to the database
     * @return productOrder - that was inserted to the database
     */
    public ProductOrder addProductOrder(ProductOrder productOrder) {
        return this.productOrderRepository.saveAndFlush(productOrder);
    }

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
    public List<ProductOrder> getProductOrdersPaging(Integer userProfileId, Integer productOrderStatus,
            Integer startIndex) {
        List<Object> productOrdersObjectList = this.productOrderRepository.getProductOrdersPaging(userProfileId,
                productOrderStatus, startIndex);
        return this.getProductOrdersFromObjectList(productOrdersObjectList);
    }

    /**
     * @param userProfileId      - user's profile's id that user want to get list of
     *                           product's orders (this parameter could be optional)
     * @param productOrderStatus - product's order's status that user want to get
     *                           list of product's orders (this parameter could be
     *                           optional)
     * @return list of product's orders
     */
    public List<ProductOrder> getProductOrders(Integer userProfileId, Integer productOrderStatus) {
        List<Object> productOrdersObjectList = this.productOrderRepository.getProductOrders(userProfileId,
                productOrderStatus);
        return this.getProductOrdersFromObjectList(productOrdersObjectList);

    }

    /**
     * @param userProfileId      - user's profile's id that user want to get number
     *                           of product's orders (this parameter could be
     *                           optional)
     * @param productOrderStatus - product's order's status that user want to get
     *                           number of product's orders (this parameter could be
     *                           optional)
     * @return number of product's orders
     */
    public int getNumberOfProductOrders(Integer userProfileId, Integer productOrderStatus) {
        List<Object> nProductOrdersObjectList = this.productOrderRepository.getNumberOfProductOrders(userProfileId,
                productOrderStatus);
        if (nProductOrdersObjectList.size() > 0) {
            return Integer.valueOf(nProductOrdersObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param productOrdersObjectList - product's orders object list that user want
     *                                to convert to product's orders list
     * @return list of product's orders
     */
    private List<ProductOrder> getProductOrdersFromObjectList(List<Object> productOrdersObjectList) {
        List<ProductOrder> productOrders = new ArrayList<>();
        for (Object o : productOrdersObjectList) {
            Object[] productOrderObjectArr = (Object[]) o;
            ProductOrder productOrder = this.createProductOrderFromObjectArray(productOrderObjectArr);
            productOrders.add(productOrder);
        }
        return productOrders;
    }

    /**
     * @param productOrderObjectArr - product order object array that user want to
     *                              convert to product order
     * @return converted product order
     */
    private ProductOrder createProductOrderFromObjectArray(Object[] productOrderObjectArr) {
        int productOrderId = (int) productOrderObjectArr[0];
        int productOrderStatus = (int) productOrderObjectArr[1];
        Date productOrderDate = (Date) productOrderObjectArr[2];
        int userProfileId = (int) productOrderObjectArr[3];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        return new ProductOrder((long) productOrderId, productOrderStatus, productOrderDate, userProfile);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param productOrderId - product's order's id that user want to get selected
     *                       product's order
     * @return selected product's order
     */
    public ProductOrder getProductOrder(Long productOrderId) {
        return this.productOrderRepository.findProductOrderById(productOrderId);
    }

}
