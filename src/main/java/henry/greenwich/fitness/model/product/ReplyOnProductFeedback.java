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
@Table(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_TABLE)
public class ReplyOnProductFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_ID)
    private Long id;

    @Column(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_CONTENT)
    private String replyOnProductFeedbackContent;

    @Column(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_STATUS)
    private int replyOnProductFeedbackStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_CREATED_DATE)
    private Date replyOnProductFeedbackCreateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_PRODUCT_FEEDBACK_ID)
    public ProductFeedback productFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_NUMBER_OF_LIKES)
    int nLikes;

    @Column(name = Constants.REPLY_ON_PRODUCT_FEEDBACK_NUMBER_OF_DISLIKES)
    int nDislikes;
}
