package henry.greenwich.fitness.model.notification;

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
@Table(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_TABLE)
public class CoachMembershipNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_USER_PROFILE_ID)
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_COACH_ID)
    public Coach coach;

    @Column(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_STATUS)
    private int status;

    @Column(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_CONTENT)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.COACH_MEMBERSHIP_NOTIFICATION_CREATED_DATE)
    private Date createdDate;
}
