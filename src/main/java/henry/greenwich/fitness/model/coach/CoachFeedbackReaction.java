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
@Table(name = Constants.COACH_FEEDBACK_REACTION_TABLE)
public class CoachFeedbackReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.COACH_FEEDBACK_REACTION_ID)
    private Long id;

    @Column(name = Constants.COACH_FEEDBACK_REACTION_REACTION)
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_FEEDBACK_REACTION_COACH_FEEDBACK_ID)
    public CoachFeedback coachFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_FEEDBACK_REACTION_USER_PROFILE_ID)
    public UserProfile userProfile;
}
