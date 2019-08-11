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
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_category_name")
    private String productCategoryName;

    @Column(name = "product_category_meta_title")
    private String productCategoryMetaTitle;

    @Column(name = "product_category_parent_id")
    private int productCategoryParentId;

    @Column(name = "product_category_display_order")
    private int productCategoryDisplayOrder;

    @Column(name = "product_category_seo_title")
    private String productCategorySeoTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_category_created_date")
    private Date productCategoryCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_category_modified_date")
    private Date productCategoryModifiedDate;

    @Column(name = "product_category_meta_keywords")
    private String productCategoryMetaKeywords;

    @Column(name = "product_category_meta_desccription")
    private String productCategoryMetaDescription;

    @Column(name = "product_category_status")
    private int productCategoryStatus;

    @Column(name = "product_category_show_on_home")
    private String productCategoryShowOnHome;

    @Column(name = "product_category_image")
    private String productCategoryImage;

    @Column(name = "product_category_more_image")
    private String productCategoryMoreImage;
}
