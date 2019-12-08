package henry.greenwich.fitness.repository.product;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

        String GET_PRODUCTS_PAGING = "select * from " + Constants.PRODUCT_TABLE + ""
                        + " where (:productCategoryId is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_PRODUCT_CATEGORY_ID + " = :productCategoryId)"
                        + " and (:productMinPrice is null or " + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_PRICE
                        + " >= :productMinPrice)" + " and (:productMaxPrice is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_PRICE + " <= :productMaxPrice)" + " and (:productStatus is null or "
                        + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_STATUS + " = :productStatus)"
                        + " and (:productNameKeywords is null or lower(" + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_NAME + ") like %:productNameKeywords%)" + " and "
                        + Constants.PRODUCT_QUANTITY + " > 0" + " limit :startIndex, "
                        + Constants.NUMBER_ITEMS_PER_PAGE;

        String GET_PRODUCTS = "select * from " + Constants.PRODUCT_TABLE + "" + " where (:productCategoryId is null or "
                        + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_PRODUCT_CATEGORY_ID
                        + " = :productCategoryId)" + " and (:productMinPrice is null or " + Constants.PRODUCT_TABLE
                        + "." + Constants.PRODUCT_PRICE + " >= :productMinPrice)" + " and (:productMaxPrice is null or "
                        + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_PRICE + " <= :productMaxPrice)"
                        + " and (:productStatus is null or " + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_STATUS
                        + " = :productStatus)" + " and (:productNameKeywords is null or " + Constants.PRODUCT_TABLE
                        + "." + Constants.PRODUCT_NAME + " like %:productNameKeywords%)" + " and "
                        + Constants.PRODUCT_QUANTITY + " > 0";

        String GET_NUMBER_OF_PRODUCTS = "select count(*) from " + Constants.PRODUCT_TABLE + ""
                        + " where (:productCategoryId is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_PRODUCT_CATEGORY_ID + " = :productCategoryId)"
                        + " and (:productMinPrice is null or " + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_PRICE
                        + " >= :productMinPrice)" + " and (:productMaxPrice is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_PRICE + " <= :productMaxPrice)" + " and (:productStatus is null or "
                        + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_STATUS + " = :productStatus)"
                        + " and (:productNameKeywords is null or lower(" + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_NAME + ") like %:productNameKeywords%)" + " and "
                        + Constants.PRODUCT_QUANTITY + " > 0";

        String GET_TOP_PRODUCTS = "select * from " + Constants.PRODUCT_TABLE + ""
                        + " where (:productCategoryId is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_PRODUCT_CATEGORY_ID + " = :productCategoryId)"
                        + " and (:productMinPrice is null or " + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_PRICE
                        + " >= :productMinPrice)" + " and (:productMaxPrice is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_PRICE + " <= :productMaxPrice)" + " and (:productStatus is null or "
                        + Constants.PRODUCT_TABLE + "." + Constants.PRODUCT_STATUS + " = :productStatus)"
                        + " and (:productNameKeywords is null or " + Constants.PRODUCT_TABLE + "."
                        + Constants.PRODUCT_NAME + " like %:productNameKeywords%)" + " and "
                        + Constants.PRODUCT_QUANTITY + " > 0" + " limit :topProductLimit";

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
        @Query(nativeQuery = true, value = GET_PRODUCTS_PAGING)
        List<Object> getProductsPaging(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
                        Integer productStatus, String productNameKeywords, Integer startIndex);

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
        @Query(nativeQuery = true, value = GET_PRODUCTS)
        List<Object> getProducts(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
                        Integer productStatus, String productNameKeywords);

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
        @Query(nativeQuery = true, value = GET_NUMBER_OF_PRODUCTS)
        List<Object> getNumberOfProducts(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
                        Integer productStatus, String productNameKeywords);

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
        @Query(nativeQuery = true, value = GET_TOP_PRODUCTS)
        List<Object> getTopProducts(Integer productCategoryId, Integer productMinPrice, Integer productMaxPrice,
                        Integer productStatus, String productNameKeywords, Integer topProductLimit);

        /**
         * @param id - product's id that user want to get selected product
         * @return selected product
         */
        Product findProductById(Long id);
}
