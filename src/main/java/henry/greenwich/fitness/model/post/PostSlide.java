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
@Table(name = Constants.POST_SLIDE_TABLE)
public class PostSlide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_SLIDE_ID)
    private Long id;

    @Column(name = Constants.POST_SLIDE_IMAGE)
    private String postSlideImage;

    @Column(name = Constants.POST_SLIDE_LINK)
    private String postSlideLink;

    @Column(name = Constants.POST_SLIDE_DESCRIPTION)
    private String postSlideDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_SLIDE_CREATED_DATE)
    private Date postSlideCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_SLIDE_MODIFIED_DATE)
    private Date postSlideModifiedDate;

    @Column(name = Constants.POST_SLIDE_STATUS)
    private int postSlideStatus;

    @Column(name = Constants.POST_SLIDE_TITLE)
    private String postSlideTitle;
}
