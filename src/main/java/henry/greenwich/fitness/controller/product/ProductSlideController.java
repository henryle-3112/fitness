package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductSlide;
import henry.greenwich.fitness.service.product.ProductSlideService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductSlideController {
    /**
     * productSlideService - interact with product's slide's data
     */
    private ProductSlideService productSlideService;

    /**
     * @param productSlideService - inject productSlideService
     */
    public ProductSlideController(ProductSlideService productSlideService) {
        this.productSlideService = productSlideService;
    }

    /**
     * @return list of product's slide
     */
    @GetMapping(value = "/product/slides/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductSlide> getProductSlides(@PathVariable int status) {
        return this.productSlideService.getProductSlides(status);
    }

    /**
     * @param id - product's slide's id that user want to get
     * @return selected product's slide
     */
    @GetMapping(value = "/product/slides/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductSlide getProductSlide(@PathVariable Long id, @PathVariable int status) {
        return this.productSlideService.getProductSlide(id, status);
    }

    /**
     * @param productSlide - that user want to add to the database
     * @return productSlide - that was inserted to the database
     */
    @PostMapping(value = "/product/slides/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductSlide addProductSlide(@RequestBody ProductSlide productSlide) {
        return this.productSlideService.addProductSlide(productSlide);
    }

    /**
     * @param productSlide - that user want to update to the database
     * @return productSlide - that was updated to the database
     */
    @PostMapping(value = "/product/slides/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductSlide updateProductSlide(@RequestBody ProductSlide productSlide) {
        return this.productSlideService.updateProductSlide(productSlide);
    }

    /**
     * @param id - product's slide's id that user want to delete
     */
    @PostMapping(value = "/product/slides/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteProductSlide(@PathVariable Long id) {
        this.productSlideService.deleteProductSlide(id);
    }
}
