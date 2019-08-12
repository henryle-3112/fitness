package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductCategory;
import henry.greenwich.fitness.service.product.ProductCategoryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductCategoryController {
    private ProductCategoryService productCategoryService;

    /**
     * @param productCategoryService - inject productCategoryService
     */
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    /**
     * @return list of product's categories
     */
    @GetMapping(value = "/categories", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductCategory> getProductCategories(@RequestParam(required = false) Integer status) {
        return this.productCategoryService.getProductCategories(status);
    }

}
