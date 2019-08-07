package henry.greenwich.fitness.model.notification;

import henry.greenwich.fitness.constants.Constants;
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
@Table(name = Constants.NOTIFICATION_TABLE)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.NOTIFICATION_ID)
    private Long id;

    @Column(name = Constants.NOTIFICATION_CONTENT)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NOTIFICATION_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.NOTIFICATION_CREATED_DATE)
    private Date createdDate;

    @Column(name = Constants.NOTIFICATION_STATUS)
    private int status;
}
