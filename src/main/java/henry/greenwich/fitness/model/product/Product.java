package henry.greenwich.fitness.model.product;

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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_meta_title")
    private String productMetaTitle;

    @Column(name = "product_code")
    private int productCode;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_more_image")
    private String productMoreImage;

    @Column(name = "product_price")
    private float productPrice;

    @Column(name = "product_promotion_price")
    private float productPromotionPrice;

    @Column(name = "product_include_vat")
    private int productIncludeVat;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "product_waranty")
    private int waranty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_created_date")
    private Date productCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_modified_date")
    private Date productModifiedDate;

    @Column(name = "product_meta_keywords")
    private String productMetaKeywords;

    @Column(name = "product_meta_description")
    private String productMetaDescription;

    @Column(name = "product_top_hot")
    private int productTopHot;

    @Column(name = "product_new")
    private int productNew;

    @Column(name = "product_status")
    private int productStatus;

    @Column(name = "product_view_count")
    private int productViewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    public ProductCategory productCategory;
}