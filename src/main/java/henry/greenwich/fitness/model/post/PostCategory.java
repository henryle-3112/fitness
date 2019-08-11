package henry.greenwich.fitness.model.post;

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
@Table(name = "post_category")
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String postCategoryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date postCategoryCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date postCategoryModifiedDate;

    @Column(name = "status")
    private int postCategoryStatus;

    @Column(name = "meta_title")
    private String postCategoryMetaTitle;

    @Column(name = "parent_id")
    private int postCategoryParentId;

    @Column(name = "display_order")
    private int postCategoryDisplayOrder;

    @Column(name = "seo_title")
    private String postCategorySeoTitle;

    @Column(name = "meta_keywords")
    private String postCategoryMetaKeywords;

    @Column(name = "meta_description")
    private String postCategoryMetaDescription;

    @Column(name = "show_on_home")
    private int postCategoryShowOnHome;

    @Column(name = "image")
    private String postCategoryImage;

    @Column(name = "more_image")
    private String postCategoryMoreImage;
}
