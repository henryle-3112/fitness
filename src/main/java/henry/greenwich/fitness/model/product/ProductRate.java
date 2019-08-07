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
@Table(name = Constants.PRODUCT_RATE_TABLE)
public class ProductRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_RATE_RATE_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_RATE_RATE)
    private int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_RATE_PRODUCT_ID)
    public Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_RATE_USER_PROFILE_ID)
    public UserProfile userProfile;
}
