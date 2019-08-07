package henry.greenwich.fitness.model.product;

import henry.greenwich.fitness.constants.Constants;
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
@Table(name = Constants.PRODUCT_ORDER_DETAIL_TABLE)
public class ProductOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRODUCT_ORDER_DETAIL_ID)
    private Long id;

    @Column(name = Constants.PRODUCT_ORDER_DETAIL_QUANTITY)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_ORDER_DETAIL_PRODUCT_ID)
    public Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.PRODUCT_ORDER_DETAIL_PRODUCT_ORDER_ID)
    public ProductOrder productOrder;
}
