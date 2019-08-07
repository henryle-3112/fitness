package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductCategory;
import henry.greenwich.fitness.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductCategoryService productCategoryService;

    /**
     * @param productRepository      - inject productRepository
     * @param productCategoryService - inject productCategoryService
     */
    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
    }

    /**
     * @param productCategoryId   - product's category's id that user want to get
     *                            products (this parameter could be optional)
     * @param productMinPrice     - product's min's price that user want to get
     *                            products (this parameter could be optional)
     * @param productMaxPrice     - product's max's price that user want to get
     *                            products (this parameter could be optional)
     * @param productStatus       - product's status that user want to get products
     *                            (this parameter could be optional)
     * @param productNameKeywords - product's name's keywords that user want to get
     *                            products (this parameter could be optional)
     * @param startIndex          - start index (for pagination) (this parameter
     *                            could be optional)
     * @return list of products
     */
    public List<Product> getProductsPaging(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
            Integer productStatus, String productNameKeywords, Integer startIndex) {
        List<Object> productsObjectList = this.productRepository.getProductsPaging(productCategoryId, productMinPrice,
                productMaxPrice, productStatus, productNameKeywords, startIndex);
        return this.getProductsFromObjectList(productsObjectList);
    }

    /**
     * @param productCategoryId   - product's category's id that user want to get
     *                            products (this parameter could be optional)
     * @param productMinPrice     - product's min's price that user want to get
     *                            products (this parameter could be optional)
     * @param productMaxPrice     - product's max's price that user want to get
     *                            products (this parameter could be optional)
     * @param productStatus       - product's status that user want to get products
     *                            (this parameter could be optional)
     * @param productNameKeywords - product's name's keywords that user want to get
     *                            products (this parameter could be optional)
     * @return list of products
     */
    public List<Product> getProducts(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
            Integer productStatus, String productNameKeywords) {
        List<Object> productsObjectList = this.productRepository.getProducts(productCategoryId, productMinPrice,
                productMaxPrice, productStatus, productNameKeywords);
        return this.getProductsFromObjectList(productsObjectList);
    }

    /**
     * @param productCategoryId   - product's category's id that user want to get
     *                            number of products (this parameter could be
     *                            optional)
     * @param productMinPrice     - product's min's price that user want to get
     *                            number of products (this parameter could be
     *                            optional)
     * @param productMaxPrice     - product's max's price that user want to get
     *                            number of products (this parameter could be
     *                            optional)
     * @param productStatus       - product's status that user want to get number of
     *                            products (this parameter could be optional)
     * @param productNameKeywords - product's name's keywords that user want to get
     *                            number of products (this parameter could be
     *                            optional)
     * @return number of products
     */
    public int getNumberOfProducts(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
            Integer productStatus, String productNameKeywords) {
        List<Object> nProductsObjectList = this.productRepository.getNumberOfProducts(productCategoryId,
                productMinPrice, productMaxPrice, productStatus, productNameKeywords);
        if (nProductsObjectList.size() > 0) {
            return Integer.valueOf(nProductsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param productCategoryId   - product's category's id that user want to get
     *                            products (this parameter could be optional)
     * @param productMinPrice     - product's min's price that user want to get
     *                            products (this parameter could be optional)
     * @param productMaxPrice     - product's max's price that user want to get
     *                            products (this parameter could be optional)
     * @param productStatus       - product's status that user want to get products
     *                            (this parameter could be optional)
     * @param productNameKeywords - product's name's keywords that user want to get
     *                            products (this parameter could be optional)
     * @param topProductLimit     - number of products that user want to get (this
     *                            parameter could be optional)
     * @return list of products
     */
    public List<Product> getTopProducts(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
            Integer productStatus, String productNameKeywords, Integer topProductLimit) {
        List<Object> topProductsObjectList = this.productRepository.getTopProducts(productCategoryId, productMinPrice,
                productMaxPrice, productStatus, productNameKeywords, topProductLimit);
        return this.getProductsFromObjectList(topProductsObjectList);
    }

    /**
     * @param productsObjectList - products object list that user want to convert to
     *                           products list
     * @return list of products
     */
    private List<Product> getProductsFromObjectList(List<Object> productsObjectList) {
        List<Product> products = new ArrayList<>();
        for (Object o : productsObjectList) {
            Object[] productObjectArr = (Object[]) o;
            Product product = this.createProductFromObjectArray(productObjectArr);
            products.add(product);
        }
        return products;
    }

    /**
     * @param productObjectArr - product object array that user want to convert to
     *                         product
     * @return converted product
     */
    private Product createProductFromObjectArray(Object[] productObjectArr) {
        int id = (int) productObjectArr[0];
        String productName = (String) productObjectArr[1];
        String productMetaTitle = (String) productObjectArr[2];
        int productCode = (int) productObjectArr[3];
        String productImage = (String) productObjectArr[4];
        String productMoreImage = (String) productObjectArr[5];
        float productPrice = (float) productObjectArr[6];
        float productPromotionPrice = (float) productObjectArr[7];
        int productIncludeVat = (int) productObjectArr[8];
        int productQuantity = (int) productObjectArr[9];
        int waranty = (int) productObjectArr[10];
        Date productCreatedDate = (Date) productObjectArr[11];
        Date productModifiedDate = (Date) productObjectArr[12];
        String productMetaKeywords = (String) productObjectArr[13];
        String productMetaDescription = (String) productObjectArr[14];
        int productTopHot = (int) productObjectArr[15];
        int productNew = (int) productObjectArr[16];
        int productStatus = (int) productObjectArr[17];
        int productViewCount = (int) productObjectArr[18];
        int productCategoryId = (int) productObjectArr[19];
        ProductCategory productCategory = this.getProductCategory((long) productCategoryId);
        return new Product((long) id, productName, productMetaTitle, productCode, productImage, productMoreImage,
                productPrice, productPromotionPrice, productIncludeVat, productQuantity, waranty, productCreatedDate,
                productModifiedDate, productMetaKeywords, productMetaDescription, productTopHot, productNew,
                productStatus, productViewCount, productCategory);
    }

    /**
     * @param productCategoryId - product's category's id that user want to get
     *                          selected product's category
     * @return selected product's category
     */
    public ProductCategory getProductCategory(Long productCategoryId) {
        return this.productCategoryService.getProductCategory(productCategoryId);
    }

    /**
     * @param id - product's id
     * @return selected product
     */
    public Product getProduct(Long id) {
        return this.productRepository.findProductById(id);
    }
}
