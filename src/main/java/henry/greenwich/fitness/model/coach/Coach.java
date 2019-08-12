package henry.greenwich.fitness.model.coach;

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
@Table(name = "coach")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.COACH_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.COACH_ABOUT)
    private String about;

    @Column(name = Constants.COACH_STATUS)
    private int status;

    @Column(name = Constants.COACH_RATING_AVERAGE)
    private float ratingAverage;

    @Column(name = Constants.COACH_NUMBER_OF_MEMBERSHIPS)
    private int numberOfMemberships;
}
