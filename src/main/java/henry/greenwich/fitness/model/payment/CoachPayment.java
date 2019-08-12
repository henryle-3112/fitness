package henry.greenwich.fitness.model.payment;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.membership.Membership;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.COACH_PAYMENT_TABLE)
public class CoachPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.COACH_PAYMENT_ID)
    private Long id;

    @Column(name = Constants.COACH_PAYMENT_PAYMENT_ID)
    private String paymentId;

    @Column(name = Constants.COACH_PAYMENT_PAYER_ID)
    private String payerId;

    @Column(name = Constants.COACH_PAYMENT_TOKEN)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_PAYMENT_MEMBERSHIP_ID)
    public Membership membership;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.COACH_PAYMENT_CREATED_DATE)
    private Date createdDate;

    @Column(name = Constants.COACH_PAYMENT_SUM)
    private int sum;
}
