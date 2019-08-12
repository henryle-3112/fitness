package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.service.product.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductController {
    private ProductService productService;

    /**
     * @param productService - inject productService
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @param response   - response to add number of products and number of pages to
     *                   header
     * @param categoryId - product's category's id that user want to get products
     *                   (this parameter could be optional)
     * @param minPrice   - product's min price that user want to get products (this
     *                   parameter could be optional)
     * @param maxPrice   - product's max price that user want to get products (this
     *                   parameter could be optional)
     * @param status     - product's status that user want to get products
     * @param search     - product's name's keywords that user want to get products
     *                   (this parameter could be optional)
     * @param page       - start index to get products (for pagination) (this
     *                   parameter could be optional)
     * @param top        - top limit products that user want to get products (this
     *                   parameter could be optional)
     */
    @GetMapping(value = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getProducts(HttpServletResponse response,
                                     @RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false) Integer minPrice,
                                     @RequestParam(required = false) Integer maxPrice,
                                     @RequestParam(required = false) Integer status,
                                     @RequestParam(required = false) String search,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer top) {
        if (page != null) {
            return this.getProductsPaging(response, categoryId, minPrice, maxPrice, status, search, page);
        } else if (top != null) {
            return this.productService.getTopProducts(categoryId, minPrice, maxPrice, status, search, top);
        }
        return this.productService.getProducts(categoryId, minPrice, maxPrice, status, search);
    }

    /**
     * @param response   - response to add number of products and number of pages to
     *                   header
     * @param categoryId - product's category's id that user want to get products
     *                   (this parameter could be optional)
     * @param minPrice   - product's min price that user want to get products (this
     *                   parameter could be optional)
     * @param maxPrice   - product's max price that user want to get products (this
     *                   parameter could be optional)
     * @param status     - product's status that user want to get products
     * @param search     - product's name's keywords that user want to get products
     *                   (this parameter could be optional)
     * @param page       - start index to get products (for pagination) (this
     *                   parameter could be optional)
     */
    private List<Product> getProductsPaging(HttpServletResponse response,
                                            Integer categoryId,
                                            Integer minPrice,
                                            Integer maxPrice,
                                            Integer status,
                                            String search,
                                            Integer page) {
        int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
        int nProducts = this.productService.getNumberOfProducts(categoryId, minPrice, maxPrice, status, search);
        response.addHeader(Constants.HEADER_X_TOTAL_COUNT, String.valueOf(nProducts));
        int nPages = nProducts > 0 ? (nProducts >= Constants.NUMBER_ITEMS_PER_PAGE ? nProducts / Constants.NUMBER_ITEMS_PER_PAGE : 1) : 0;
        response.addHeader(Constants.HEADER_X_TOTAL_PAGE, String.valueOf(nPages));
        return this.productService.getProductsPaging(categoryId, minPrice, maxPrice, status, search, startIndex - 1);
    }
}
