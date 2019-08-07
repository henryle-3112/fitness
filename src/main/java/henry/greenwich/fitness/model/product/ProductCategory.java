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
@Table(name = Constants.PRODUCT_CATEGORY_TABLE)
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_CATEGORY_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_CATEGORY_NAME)
    private String productCategoryName;

    @Column(name = Constants.PRODUCT_CATEGORY_META_TITLE)
    private String productCategoryMetaTitle;

    @Column(name = Constants.PRODUCT_CATEGORY_PARENT_ID)
    private int productCategoryParentId;

    @Column(name = Constants.PRODUCT_CATEGORY_DISPLAY_ORDER)
    private int productCategoryDisplayOrder;

    @Column(name = Constants.PRODUCT_CATEGORY_SEO_TITLE)
    private String productCategorySeoTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_CATEGORY_CREATED_DATE)
    private Date productCategoryCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_CATEGORY_MODIFIED_DATE)
    private Date productCategoryModifiedDate;

    @Column(name = Constants.PRODUCT_CATEGORY_META_KEYWORDS)
    private String productCategoryMetaKeywords;

    @Column(name = Constants.PRODUCT_CATEGORY_META_DESCRIPTION)
    private String productCategoryMetaDescription;

    @Column(name = Constants.PRODUCT_CATEGORY_STATUS)
    private int productCategoryStatus;

    @Column(name = Constants.PRODUCT_CATEGORY_SHOW_ON_HOME)
    private String productCategoryShowOnHome;

    @Column(name = Constants.PRODUCT_CATEGORY_IMAGE)
    private String productCategoryImage;

    @Column(name = Constants.PRODUCT_CATEGORY_MORE_IMAGE)
    private String productCategoryMoreImage;
}
