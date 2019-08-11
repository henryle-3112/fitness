package henry.greenwich.fitness.model.training;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    public Coach coach;

    @Column(name = "status")
    private int status;

    @Column(name = "name")
    private String name;

    @Column(name = "training_date")
    private String trainingDate;

    @Column(name = "number_of_reps")
    private int nReps;

    @Column(name = "log")
    private String log;

    @Column(name = "current_health")
    private String currentHealth;
}
