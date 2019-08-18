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
@Table(name = Constants.GIFT_TYPE_TABLE)
public class GiftType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.GIFT_TYPE_ID)
    private Long id;

    @Column(name = Constants.GIFT_TYPE_NAME)
    private String name;
}
