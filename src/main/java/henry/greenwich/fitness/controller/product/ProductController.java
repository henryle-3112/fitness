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

    @GetMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Product> getProducts(HttpServletResponse response,
            @RequestParam(required = false) Integer productCategoryId,
            @RequestParam(required = false) Integer productMinPrice,
            @RequestParam(required = false) Integer productMaxPrice,
            @RequestParam(required = false) Integer productStatus,
            @RequestParam(required = false) String productNameKeywords, @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer top) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nProducts = this.productService.getNumberOfProducts(productCategoryId, productMinPrice, productMaxPrice,
                    productStatus, productNameKeywords);
            response.addHeader("X-Total-Count", String.valueOf(nProducts));
            response.addHeader("X-Total-Page", String.valueOf(nProducts / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.productService.getProductsPaging(productCategoryId, productMinPrice, productMaxPrice,
                    productStatus, productNameKeywords, startIndex);
        } else if (top != null) {
            return this.productService.getTopProducts(productCategoryId, productMinPrice, productMaxPrice,
                    productStatus, productNameKeywords, top);
        }
        return this.productService.getProducts(productCategoryId, productMinPrice, productMaxPrice, productStatus,
                productNameKeywords);
    }
}
