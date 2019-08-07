package henry.greenwich.fitness.model.product;

import henry.greenwich.fitness.constants.Constants;
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
@Table(name = Constants.PRODUCT_FEEDBACK_REACTION_TABLE)
public class ProductFeedbackReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_FEEDBACK_REACTION_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_FEEDBACK_REACTION_REACTION)
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_FEEDBACK_REACTION_PRODUCT_FEEDBACK_ID)
    public ProductFeedback productFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID)
    public UserProfile userProfile;
}
