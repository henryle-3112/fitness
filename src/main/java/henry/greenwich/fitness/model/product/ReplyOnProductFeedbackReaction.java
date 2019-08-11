package henry.greenwich.fitness.model.product;

import henry.greenwich.fitness.model.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reply_on_product_feedback_reaction")
public class ReplyOnProductFeedbackReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reaction")
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_on_product_feedback_id")
    public ReplyOnProductFeedback replyOnProductFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
