package henry.greenwich.fitness.model.user;


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
@Table(name = Constants.BODY_INDEX_TABLE)
public class BodyIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.BODY_INDEX_ID)
    private Long bodyIndexId;

    @Column(name = Constants.BODY_INDEX_WEIGHT)
    private float weight;

    @Column(name = Constants.BODY_INDEX_HEIGHT)
    private float height;

    @Column(name = Constants.BODY_INDEX_DATE)
    private String currentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.BODY_INDEX_USER_PROFILE_ID)
    public UserProfile userProfile;
}
