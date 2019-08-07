package henry.greenwich.fitness.model.post;

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
@Table(name = Constants.POST_CATEGORY_TABLE)
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_CATEGORY_ID)
    private Long id;

    @Column(name = Constants.POST_CATEGORY_NAME)
    private String postCategoryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_CATEGORY_CREATED_DATE)
    private Date postCategoryCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_CATEGORY_MODIFIED_DATE)
    private Date postCategoryModifiedDate;

    @Column(name = Constants.POST_CATEGORY_STATUS)
    private int postCategoryStatus;

    @Column(name = Constants.POST_CATEGORY_META_TITLE)
    private String postCategoryMetaTitle;

    @Column(name = Constants.POST_CATEGORY_PARENT_ID)
    private int postCategoryParentId;

    @Column(name = Constants.POST_CATEGORY_DISPLAY_ORDER)
    private int postCategoryDisplayOrder;

    @Column(name = Constants.POST_CATEGORY_SEO_TITLE)
    private String postCategorySeoTitle;

    @Column(name = Constants.POST_CATEGORY_META_KEYWORDS)
    private String postCategoryMetaKeywords;

    @Column(name = Constants.POST_CATEGORY_META_DESCRIPTION)
    private String postCategoryMetaDescription;

    @Column(name = Constants.POST_CATEGORY_SHOW_ON_HOME)
    private int postCategoryShowOnHome;

    @Column(name = Constants.POST_CATEGORY_IMAGE)
    private String postCategoryImage;

    @Column(name = Constants.POST_CATEGORY_MORE_IMAGE)
    private String postCategoryMoreImage;
}
