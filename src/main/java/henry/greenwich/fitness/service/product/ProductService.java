package henry.greenwich.fitness.service.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    /**
     * productRepository - interact with product's repository
     */
    private ProductRepository productRepository;

    /**
     * @param productRepository - inject productRepository
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @param status - product's status
     * @return list of product
     */
    public List<Product> getProducts(int status) {
        return this.productRepository.findProductsByProductStatus(status);
    }

    /**
     * @param id     - product's id
     * @param status - product's status
     * @return selected product
     */
    public Product getProduct(Long id, int status) {
        return this.productRepository.findProductByIdAndProductStatus(id, status);
    }

    /**
     * @param product - that user want to add to the database
     * @return product that was inserted to the database
     */
    public Product addProduct(Product product) {
        return this.productRepository.saveAndFlush(product);
    }

    /**
     * @param product - that user want to update to the database
     * @return product that was updated to the database
     */
    public Product updateProduct(Product product) {
        return this.productRepository.saveAndFlush(product);
    }

    /**
     * @param id - product's id that user want to delete
     */
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    /**
     * @param top        - number of products that user want to get
     * @param categoryId - product's category's id
     * @param status     - product's status
     * @return list of products
     */
    public List<Object> getTopProducts(int top, int categoryId, int status) {
        return this.productRepository.findTopProductsByCategoryAndProductStatus(categoryId, top, status);
    }

    /**
     * @param categoryId - product's category's id
     * @param startIndex - start index
     * @param status     - product's status
     * @return list of products
     */
    public List<Object> findProductsByCategoryAndByPage(int categoryId, int startIndex, int status) {
        return this.productRepository.findProductsByCategoryAndByPage(categoryId, startIndex, status);
    }

    /**
     * @param categoryId - category's id that products belong to
     * @param status     - product's status
     * @return number of products
     */
    public List<Object> countProductsByCategoryAndByProductStatus(int categoryId, int status) {
        return this.productRepository.countProductsByCategoryAndByProductStatus(categoryId, status);
    }

    /**
     * @param categoryId          - category's id that products belong to
     * @param productMinPrice     - product's min price
     * @param productMaxPrice     - product's max price
     * @param productNameKeywords - product's name keywords
     * @return number of products
     */
    public List<Object> countSearchingProducts(Integer categoryId,
                                               Integer productMinPrice,
                                               Integer productMaxPrice,
                                               String productNameKeywords,
                                               int status) {
        return this.productRepository.countSearchingProducts(categoryId, productMinPrice, productMaxPrice, productNameKeywords, status);
    }

    /**
     * @param selectedCategoryId         - product's category's id
     * @param selectedProductMinPrice    - product's min price
     * @param selectedProductMaxPrice    - product's mac price
     * @param selectedProductNameKeyword - product's name keywords
     * @param startIndex                 - start's index
     * @param status                     - status
     * @return
     */
    public List<Object> findSearchingProductsByPage(Integer selectedCategoryId,
                                                    Integer selectedProductMinPrice,
                                                    Integer selectedProductMaxPrice,
                                                    String selectedProductNameKeyword,
                                                    int startIndex,
                                                    int status) {
        return this.productRepository.findSearchingProductsByPage(selectedCategoryId,
                selectedProductMinPrice,
                selectedProductMaxPrice,
                selectedProductNameKeyword,
                startIndex,
                status);
    }

    /**
     *
     * @param id - product's id
     * @return selected product
     */
    public Product getProduct(Long id) {
        return this.productRepository.findProductById(id);
    }
}
