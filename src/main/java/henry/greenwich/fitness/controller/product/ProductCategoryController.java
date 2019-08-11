package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductCategory;
import henry.greenwich.fitness.service.product.ProductCategoryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductCategoryController {
    /**
     * productCategoryService - interact with product's category's service
     */
    private ProductCategoryService productCategoryService;

    /**
     *
     * @param productCategoryService - inject productCategoryService
     */
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    /**
     *
     * @return list of product's categories
     */
    @GetMapping(value = "/product/categories/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductCategory> getProductCateogries(@PathVariable int status) {
        return this.productCategoryService.getProductCategories(status);
    }

    /**
     *
     * @param id - product's category's that user want to get
     * @return selected product's category
     */
    @GetMapping(value = "/product/categories/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductCategory getProductCategory(@PathVariable Long id, @PathVariable int status) {
        return this.productCategoryService.getProductCategory(id, status);
    }

    /**
     *
     * @param productCategory - that user want to add to the database
     * @return productCategory - that was inserted to the database
     */
    @PostMapping(value = "/product/categories/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory) {
        return this.productCategoryService.addProductCategory(productCategory);
    }

    /**
     *
     * @param productCategory - that user want to update to the database
     * @return productCategory - that was updated to the database
     */
    @PostMapping(value = "/product/categories/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductCategory updateProductCategory(@RequestBody ProductCategory productCategory) {
        return this.productCategoryService.updateProductCategory(productCategory);
    }

    /**
     *
     * @param id - product's category's id that user want to delete
     */
    @PostMapping(value = "/product/categories/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteProductCategory(@PathVariable Long id) {
        this.productCategoryService.deleteProductCategory(id);
    }

}
