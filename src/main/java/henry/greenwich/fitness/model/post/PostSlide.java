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
@Table(name = "post_slide")
public class PostSlide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_slide_image")
    private String postSlideImage;

    @Column(name = "post_slide_link")
    private String postSlideLink;

    @Column(name = "post_slide_description")
    private String postSlideDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_slide_created_date")
    private Date postSlideCreatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_slide_modified_date")
    private Date postSlideModifiedDate;

    @Column(name = "post_slide_status")
    private int postSlideStatus;

    @Column(name = "post_slide_title")
    private String postSlideTitle;
}
