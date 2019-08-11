package henry.greenwich.fitness.model.product;

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
@Table(name = "reply_on_product_feedback")
public class ReplyOnProductFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reply_on_product_feedback_content")
    private String replyOnProductFeedbackContent;

    @Column(name = "reply_on_product_feedback_status")
    private int replyOnProductFeedbackStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reply_on_product_feedback_created_date")
    private Date replyOnProductFeedbackCreateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_feedback_id")
    public ProductFeedback productFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
