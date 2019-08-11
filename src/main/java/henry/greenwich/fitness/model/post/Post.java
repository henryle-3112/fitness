package henry.greenwich.fitness.model.post;

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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String postContent;

    @Column(name = "description")
    private String postDescription;

    @Column(name = "meta_keywords")
    private String postMetaKeywords;

    @Column(name = "title")
    private String postTitle;

    @Column(name = "image")
    private String postImage;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date postCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date postModifiedDate;

    @Column(name = "status")
    private int postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_category_id")
    public PostCategory postCategory;

    @Column(name = "meta_title")
    private String postMetaTitle;

    @Column(name = "meta_description")
    private String postMetaDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;

    @Column(name = "view_count")
    private int postViewCount;

    @Column(name = "top_hot")
    private int postTopHot;

    @Column(name = "new")
    private int postNew;
}
