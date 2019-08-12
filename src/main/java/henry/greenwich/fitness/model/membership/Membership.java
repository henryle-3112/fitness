package henry.greenwich.fitness.model.membership;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.user.UserProfile;
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
@Table(name = Constants.MEMBERSHIP_TABLE)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.MEMBERSHIP_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.MEMBERSHIP_USER_PROFILE_ID)
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.MEMBERSHIP_COACH_ID)
    public Coach coach;

    @Column(name = Constants.MEMBERSHIP_STATUS)
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.MEMBERSHIP_START_DATE)
    private Date startDate;
}
