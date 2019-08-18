package henry.greenwich.fitness.model.gift;

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
@Table(name = Constants.USER_GIFT_TABLE)
public class UserGift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USER_GIFT_ID)
    private Long id;

    @Column(name = Constants.USER_GIFT_STATUS)
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.USER_GIFT_GIFT_ID)
    public Gift gift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.USER_GIFT_USER_PROFILE_ID)
    public UserProfile userProfile;

}
