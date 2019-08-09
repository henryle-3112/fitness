package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.ProductSlide;
import henry.greenwich.fitness.service.product.ProductSlideService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product-management")
public class ProductSlideController {
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
    @GetMapping(value = "/slides", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<ProductSlide> getProductSlides(@RequestParam(required = false) Integer status) {
        return this.productSlideService.getProductSlides(status);
    }
}
