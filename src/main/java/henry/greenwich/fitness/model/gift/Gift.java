package henry.greenwich.fitness.model.gift;

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
@Table(name = Constants.GIFT_TABLE)
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.GIFT_ID)
    private Long id;

    @Column(name = Constants.GIFT_NAME)
    private String name;

    @Column(name = Constants.GIFT_POINT)
    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.GIFT_GIFT_TYPE_ID)
    public GiftType giftType;

    @Column(name = Constants.GIFT_IMAGE)
    private String image;
}
