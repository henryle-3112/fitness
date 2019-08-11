package henry.greenwich.fitness.controller.product;

import henry.greenwich.fitness.model.product.Product;
import henry.greenwich.fitness.model.product.ProductCategory;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.product.ProductCategoryService;
import henry.greenwich.fitness.service.product.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class ProductController {
    /**
     * productService - interact with product's data
     * productCategoryService - interact with product's category's data
     */
    private ProductService productService;
    private ProductCategoryService productCategoryService;

    /**
     * @param productService - inject productService
     */
    public ProductController(ProductService productService,
                             ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    /**
     * @return list of products
     */
    @GetMapping(value = "/products/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getProducts(@PathVariable int status) {
        return this.productService.getProducts(status);
    }

    /**
     * @return list of products
     */
    @GetMapping(value = "/products/{top}/{categoryId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getTopProducts(@PathVariable int top, @PathVariable int categoryId, @PathVariable int status) {
        List<Product> products = new ArrayList<>();
        List<Object> topProducts = this.productService.getTopProducts(top, categoryId, status);
        for (Object eachTopProductObject : topProducts) {
            Object[] eachTopProduct = (Object[]) eachTopProductObject;
            int id = (int) eachTopProduct[0];
            String productName = (String) eachTopProduct[1];
            String productMetaTitle = (String) eachTopProduct[2];
            int productCode = (int) eachTopProduct[3];
            String productImage = (String) eachTopProduct[4];
            String productMoreImage = (String) eachTopProduct[5];
            float productPrice = (float) eachTopProduct[6];
            float productPromotionPrice = (float) eachTopProduct[7];
            int productIncludeVat = (int) eachTopProduct[8];
            int productQuantity = (int) eachTopProduct[9];
            int waranty = (int) eachTopProduct[10];
            Date productCreatedDate = (Date) eachTopProduct[11];
            Date productModifiedDate = (Date) eachTopProduct[12];
            String productMetaKeywords = (String) eachTopProduct[13];
            String productMetaDescription = (String) eachTopProduct[14];
            int productTopHot = (int) eachTopProduct[15];
            int productNew = (int) eachTopProduct[16];
            int productStatus = (int) eachTopProduct[17];
            int productViewCount = (int) eachTopProduct[18];
            int productCategoryId = (int) eachTopProduct[19];
            ProductCategory productCategory = this.productCategoryService.getProductCategory((long) productCategoryId);
            Product product = new Product(
                    (long) id,
                    productName,
                    productMetaTitle,
                    productCode,
                    productImage,
                    productMoreImage,
                    productPrice,
                    productPromotionPrice,
                    productIncludeVat,
                    productQuantity,
                    waranty,
                    productCreatedDate,
                    productModifiedDate,
                    productMetaKeywords,
                    productMetaDescription,
                    productTopHot,
                    productNew,
                    productStatus,
                    productViewCount,
                    productCategory
            );
            products.add(product);
        }
        return products;
    }

    /**
     * @param id - product's id that user want to get
     * @return selected product
     */
    @GetMapping(value = "/products/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product getProduct(@PathVariable Long id, @PathVariable int status) {
        return this.productService.getProduct(id, status);
    }

    /**
     * @param product - that user want to add to the database
     * @return product - that was inserted to the database
     */
    @PostMapping(value = "/products/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    /**
     * @param product - that user want to update to the database
     * @return product - that was updated to the database
     */
    @PostMapping(value = "/products/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    /**
     * @param id - product's id that user want to delete
     */
    @PostMapping(value = "/products/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }

    /**
     * @param categoryId - category's id that products belong to
     * @param page       - current page
     * @return list of products
     */
    @GetMapping(value = "/products/paging/{categoryId}/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getProductsByCategoryAndByPage(@PathVariable int categoryId, @PathVariable int page, @PathVariable int status) {
        int startIndex = ((page - 1) * 8) + 1;
        List<Product> products = new ArrayList<>();
        List<Object> productsObject = this.productService.findProductsByCategoryAndByPage(categoryId, startIndex - 1, status);
        for (Object eachTopProductObject : productsObject) {
            Object[] eachTopProduct = (Object[]) eachTopProductObject;
            int id = (int) eachTopProduct[0];
            String productName = (String) eachTopProduct[1];
            String productMetaTitle = (String) eachTopProduct[2];
            int productCode = (int) eachTopProduct[3];
            String productImage = (String) eachTopProduct[4];
            String productMoreImage = (String) eachTopProduct[5];
            float productPrice = (float) eachTopProduct[6];
            float productPromotionPrice = (float) eachTopProduct[7];
            int productIncludeVat = (int) eachTopProduct[8];
            int productQuantity = (int) eachTopProduct[9];
            int waranty = (int) eachTopProduct[10];
            Date productCreatedDate = (Date) eachTopProduct[11];
            Date productModifiedDate = (Date) eachTopProduct[12];
            String productMetaKeywords = (String) eachTopProduct[13];
            String productMetaDescription = (String) eachTopProduct[14];
            int productTopHot = (int) eachTopProduct[15];
            int productNew = (int) eachTopProduct[16];
            int productStatus = (int) eachTopProduct[17];
            int productViewCount = (int) eachTopProduct[18];
            int productCategoryId = (int) eachTopProduct[19];
            ProductCategory productCategory = this.productCategoryService.getProductCategory((long) productCategoryId);
            Product product = new Product(
                    (long) id,
                    productName,
                    productMetaTitle,
                    productCode,
                    productImage,
                    productMoreImage,
                    productPrice,
                    productPromotionPrice,
                    productIncludeVat,
                    productQuantity,
                    waranty,
                    productCreatedDate,
                    productModifiedDate,
                    productMetaKeywords,
                    productMetaDescription,
                    productTopHot,
                    productNew,
                    productStatus,
                    productViewCount,
                    productCategory
            );
            products.add(product);
        }
        return products;
    }

    /**
     * @param categoryId - category's id that products belong to
     * @return number of products
     */
    @GetMapping(value = "/products/count/{categoryId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countProducts(@PathVariable int categoryId, @PathVariable int status) {
        List<Object> countProducts = this.productService.countProductsByCategoryAndByProductStatus(categoryId, status);
        Object eachCountProduct = countProducts.get(0);
        return new ResponseMessage(eachCountProduct.toString());
    }


    /**
     * @param categoryId          - category's id that products belong to
     * @param productMinPrice     - product's min price
     * @param productMaxPrice     - product's max price
     * @param productNameKeywords - product's name keywords
     * @return number of products
     */
    @GetMapping(value = "/products/searching/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countSearchingPosts(@RequestParam Optional<String> categoryId,
                                       @RequestParam Optional<String> productMinPrice,
                                       @RequestParam Optional<String> productMaxPrice,
                                       @RequestParam Optional<String> productNameKeywords,
                                       @PathVariable int status) {
        Integer selectedCategoryId = null;
        Integer selectedProductMinPrice = null;
        Integer selectedProductMaxPrice = null;
        String selectedProductNameKeyword = "%%";

        // get selected category's id
        String paramCategoryId = categoryId.orElse(null);
        if (paramCategoryId != null && !paramCategoryId.equals("") && paramCategoryId.matches("[0-9]+")) {
            selectedCategoryId = Integer.valueOf(paramCategoryId);
        }

        // get selected product's min price
        String paramProductMinPrice = productMinPrice.orElse(null);
        if (paramProductMinPrice != null && !paramProductMinPrice.equals("") && paramProductMinPrice.matches("[0-9]+")) {
            selectedProductMinPrice = Integer.valueOf(paramProductMinPrice);
        }

        // get selected product's max price
        String paramProductMaxPrice = productMaxPrice.orElse(null);
        if (paramProductMaxPrice != null && !paramProductMaxPrice.equals("") && paramProductMaxPrice.matches("[0-9]+")) {
            selectedProductMaxPrice = Integer.valueOf(paramProductMaxPrice);
        }

        // get selected product's name keywords
        String paramProductNameKeywords = productNameKeywords.orElse(null);
        if (paramProductNameKeywords != null && !paramProductNameKeywords.equals("")) {
            selectedProductNameKeyword = "%" + paramProductNameKeywords + "%";
        }
        List<Object> countProductsObject = this.productService.countSearchingProducts(
                selectedCategoryId,
                selectedProductMinPrice,
                selectedProductMaxPrice,
                selectedProductNameKeyword,
                status);
        Object eachCountMusic = countProductsObject.get(0);
        return new ResponseMessage(eachCountMusic.toString());
    }

    /**
     * @param categoryId - category's id that products belong to
     * @param page       - current page
     * @return list of products
     */
    @GetMapping(value = "/products/searching/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getSearchingProductsByPage(@PathVariable int page,
                                                    @RequestParam Optional<String> categoryId,
                                                    @RequestParam Optional<String> productMinPrice,
                                                    @RequestParam Optional<String> productMaxPrice,
                                                    @RequestParam Optional<String> productNameKeywords,
                                                    @PathVariable int status) {
        Integer selectedCategoryId = null;
        Integer selectedProductMinPrice = null;
        Integer selectedProductMaxPrice = null;
        String selectedProductNameKeyword = "%%";

        // get selected category's id
        String paramCategoryId = categoryId.orElse(null);
        if (paramCategoryId != null && !paramCategoryId.equals("") && paramCategoryId.matches("[0-9]+")) {
            selectedCategoryId = Integer.valueOf(paramCategoryId);
        }

        // get selected product's min price
        String paramProductMinPrice = productMinPrice.orElse(null);
        if (paramProductMinPrice != null && !paramProductMinPrice.equals("") && paramProductMinPrice.matches("[0-9]+")) {
            selectedProductMinPrice = Integer.valueOf(paramProductMinPrice);
        }

        // get selected product's max price
        String paramProductMaxPrice = productMaxPrice.orElse(null);
        if (paramProductMaxPrice != null && !paramProductMaxPrice.equals("") && paramProductMaxPrice.matches("[0-9]+")) {
            selectedProductMaxPrice = Integer.valueOf(paramProductMaxPrice);
        }

        // get selected product's name keywords
        String paramProductNameKeywords = productNameKeywords.orElse(null);
        if (paramProductNameKeywords != null && !paramProductNameKeywords.equals("")) {
            selectedProductNameKeyword = "%" + paramProductNameKeywords + "%";
        }

        int startIndex = ((page - 1) * 8) + 1;
        List<Product> products = new ArrayList<>();

        List<Object> productsObject = this.productService.findSearchingProductsByPage(
                selectedCategoryId,
                selectedProductMinPrice,
                selectedProductMaxPrice,
                selectedProductNameKeyword,
                startIndex - 1,
                status);

        for (Object eachTopProductObject : productsObject) {
            Object[] eachTopProduct = (Object[]) eachTopProductObject;
            int id = (int) eachTopProduct[0];
            String productName = (String) eachTopProduct[1];
            String productMetaTitle = (String) eachTopProduct[2];
            int productCode = (int) eachTopProduct[3];
            String productImage = (String) eachTopProduct[4];
            String productMoreImage = (String) eachTopProduct[5];
            float productPrice = (float) eachTopProduct[6];
            float productPromotionPrice = (float) eachTopProduct[7];
            int productIncludeVat = (int) eachTopProduct[8];
            int productQuantity = (int) eachTopProduct[9];
            int waranty = (int) eachTopProduct[10];
            Date productCreatedDate = (Date) eachTopProduct[11];
            Date productModifiedDate = (Date) eachTopProduct[12];
            String productMetaKeywords = (String) eachTopProduct[13];
            String productMetaDescription = (String) eachTopProduct[14];
            int productTopHot = (int) eachTopProduct[15];
            int productNew = (int) eachTopProduct[16];
            int productStatus = (int) eachTopProduct[17];
            int productViewCount = (int) eachTopProduct[18];
            int productCategoryId = (int) eachTopProduct[19];
            ProductCategory productCategory = this.productCategoryService.getProductCategory((long) productCategoryId);
            Product product = new Product(
                    (long) id,
                    productName,
                    productMetaTitle,
                    productCode,
                    productImage,
                    productMoreImage,
                    productPrice,
                    productPromotionPrice,
                    productIncludeVat,
                    productQuantity,
                    waranty,
                    productCreatedDate,
                    productModifiedDate,
                    productMetaKeywords,
                    productMetaDescription,
                    productTopHot,
                    productNew,
                    productStatus,
                    productViewCount,
                    productCategory
            );
            products.add(product);
        }
        return products;
    }
}
