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
@Table(name = Constants.ACHIEVEMENT_TABLE)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ACHIEVEMENT_ID)
    private Long achievementId;

    @Column(name = Constants.ACHIEVEMENT_TITLE)
    private String title;

    @Column(name = Constants.ACHIEVEMENT_TIME)
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.ACHIEVEMENT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.ACHIEVEMENT_NUMBER_OF_REPS)
    private int nReps;

    @Column(name = Constants.ACHIEVEMENT_LOG)
    private String log;

    @Column(name = Constants.ACHIEVEMENT_CURRENT_HEALTH)
    private String currentHealth;
}
