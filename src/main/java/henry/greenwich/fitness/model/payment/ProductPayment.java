package henry.greenwich.fitness.model.payment;


import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.product.ProductOrder;
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
@Table(name = Constants.PRODUCT_PAYMENT_TABLE)
public class ProductPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_PAYMENT_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_PAYMENT_PAYMENT_ID)
    private String paymentId;

    @Column(name = Constants.PRODUCT_PAYMENT_PAYER_ID)
    private String payerId;

    @Column(name = Constants.PRODUCT_PAYMENT_TOKEN)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_PAYMENT_PRODUCT_ORDER_PRODUCT_ORDER_ID)
    public ProductOrder productOrder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.PRODUCT_PAYMENT_CREATED_DATE)
    private Date createdDate;
}
