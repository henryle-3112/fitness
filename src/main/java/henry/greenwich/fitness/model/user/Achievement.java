package henry.greenwich.fitness.model.user;


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
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Long achievementId;

    @Column(name = "achievement_title")
    private String title;

    @Column(name = "achievement_time")
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;

    @Column(name = "number_of_reps")
    private int nReps;

    @Column(name = "log")
    private String log;

    @Column(name = "current_health")
    private String currentHealth;
}
