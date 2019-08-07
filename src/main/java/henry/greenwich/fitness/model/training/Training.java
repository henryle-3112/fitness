package henry.greenwich.fitness.model.training;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.coach.Coach;
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
@Table(name = Constants.TRAINING_TABLE)
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.TRAINING_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.TRAINING_USER_PROFILE_ID)
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.TRAINING_COACH_ID)
    public Coach coach;

    @Column(name = Constants.TRAINING_STATUS)
    private int status;

    @Column(name = Constants.TRAINING_NAME)
    private String name;

    @Column(name = Constants.TRAINING_DATE)
    private String trainingDate;

    @Column(name = Constants.TRAINING_NUMBER_OF_REPS)
    private int nReps;

    @Column(name = Constants.TRAINING_LOG)
    private String log;

    @Column(name = Constants.TRAINING_CURRENT_HEALTH)
    private String currentHealth;
}
