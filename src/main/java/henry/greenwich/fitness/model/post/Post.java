package henry.greenwich.fitness.model.post;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.UserProfile;
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
@Table(name = Constants.POST_TABLE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_ID)
    private Long id;

    @Column(name = Constants.POST_CONTENT)
    private String postContent;

    @Column(name = Constants.POST_DESCRIPTION)
    private String postDescription;

    @Column(name = Constants.POST_META_KEYWORDS)
    private String postMetaKeywords;

    @Column(name = Constants.POST_TITLE)
    private String postTitle;

    @Column(name = Constants.POST_IMAGE)
    private String postImage;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_CREATED_DATE)
    private Date postCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_MODIFIED_DATE)
    private Date postModifiedDate;

    @Column(name = Constants.POST_STATUS)
    private int postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_POST_CATEGORY_ID)
    public PostCategory postCategory;

    @Column(name = Constants.POST_META_TITLE)
    private String postMetaTitle;

    @Column(name = Constants.POST_META_DESCRIPTION)
    private String postMetaDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.POST_VIEW_COUNT)
    private int postViewCount;

    @Column(name = Constants.POST_TOP_HOT)
    private int postTopHot;

    @Column(name = Constants.POST_NEW)
    private int postNew;
}
