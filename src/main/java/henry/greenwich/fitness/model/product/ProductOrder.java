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
@Table(name = Constants.PRODUCT_ORDER_TABLE)
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_ORDER_PRODUCT_ORDER_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_ORDER_PRODUCT_ORDER_STATUS)
    private int productOrderStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_ORDER_PRODUCT_ORDER_DATE)
    private Date productOrderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_ORDER_USER_PROFILE_ID)
    public UserProfile userProfile;
}
