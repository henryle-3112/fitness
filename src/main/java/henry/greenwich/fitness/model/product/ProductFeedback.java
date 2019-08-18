package henry.greenwich.fitness.model.product;

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
@Table(name = Constants.PRODUCT_FEEDBACK_TABLE)
public class ProductFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_FEEDBACK_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_FEEDBACK_CONTENT)
    private String feedbackContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_FEEDBACK_CREATED_DATE)
    private Date feedbackCreatedDate;

    @Column(name = Constants.PRODUCT_FEEDBACK_STATUS)
    private int feedbackStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_FEEDBACK_USER_PROFILE_ID)
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_FEEDBACK_PRODUCT_ID)
    public Product product;

    @Column(name = Constants.PRODUCT_FEEDBACK_NUMBER_OF_LIKES)
    private int numberOfLikes;

    @Column(name = Constants.PRODUCT_FEEDBACK_NUMBER_OF_DISLIKES)
    private int numberOfDislikes;

    @Column(name = Constants.PRODUCT_FEEDBACK_NUMBER_OF_REPLIES)
    private int numberOfReplies;
}
