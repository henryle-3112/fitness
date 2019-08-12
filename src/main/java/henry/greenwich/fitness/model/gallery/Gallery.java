package henry.greenwich.fitness.model.gallery;


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
@Table(name = Constants.GALLERY_TABLE)
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.GALLERY_ID)
    private Long id;

    @Column(name = Constants.GALLERY_IMAGE)
    private String image;

    @Column(name = Constants.GALLERY_TITLE)
    private String title;

    @Column(name = Constants.GALLERY_CREATED_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = Constants.GALLERY_MODIFIED_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = Constants.GALLERY_STATUS)
    private int status;

    @Column(name = Constants.GALLERY_THUMBNAIL)
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.GALLERY_USER_PROFILE_ID)
    public UserProfile userProfile;
}
