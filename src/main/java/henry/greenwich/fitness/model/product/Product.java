package henry.greenwich.fitness.model.product;

import henry.greenwich.fitness.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.PRODUCT_TABLE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_NAME)
    private String productName;

    @Column(name = Constants.PRODUCT_META_TITLE)
    private String productMetaTitle;

    @Column(name = Constants.PRODUCT_CODE)
    private int productCode;

    @Column(name = Constants.PRODUCT_IMAGE)
    private String productImage;

    @Column(name = Constants.PRODUCT_MORE_IMAGE)
    private String productMoreImage;

    @Column(name = Constants.PRODUCT_PRICE)
    private float productPrice;

    @Column(name = Constants.PRODUCT_PROMOTION_PRICE)
    private float productPromotionPrice;

    @Column(name = Constants.PRODUCT_INCLUDE_VAT)
    private int productIncludeVat;

    @Column(name = Constants.PRODUCT_QUANTITY)
    private int productQuantity;

    @Column(name = Constants.PRODUCT_WARANTY)
    private int waranty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_CREATED_DATE)
    private Date productCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_MODIFIED_DATE)
    private Date productModifiedDate;

    @Column(name = Constants.PRODUCT_META_KEYWORDS)
    private String productMetaKeywords;

    @Column(name = Constants.PRODUCT_META_DESCRIPTION)
    private String productMetaDescription;

    @Column(name = Constants.PRODUCT_TOP_HOT)
    private int productTopHot;

    @Column(name = Constants.PRODUCT_NEW)
    private int productNew;

    @Column(name = Constants.PRODUCT_STATUS)
    private int productStatus;

    @Column(name = Constants.PRODUCT_VIEW_COUNT)
    private int productViewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_PRODUCT_CATEGORY_ID)
    public ProductCategory productCategory;
}