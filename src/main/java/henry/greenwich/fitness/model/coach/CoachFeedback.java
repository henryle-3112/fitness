package henry.greenwich.fitness.model.coach;

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
@Table(name = "coach_feedback")
public class CoachFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.COACH_FEEDBACK_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_FEEDBACK_USER_PROFILE_ID)
    public UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.COACH_FEEDBACK_COACH_ID)
    public Coach coach;

    @Column(name = Constants.COACH_FEEDBACK_CONTENT)
    private String coachFeedbackContent;

    @Column(name = Constants.COACH_FEEDBACK_STATUS)
    private int coachFeedbackStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.COACH_FEEDBACK_CREATED_DATE)
    private Date coachFeedbackCreatedDate;

    @Column(name = Constants.COACH_FEEDBACK_NUMBER_OF_LIKES)
    private int nLikes;

    @Column(name = Constants.COACH_FEEDBACK_NUMBER_OF_DISLIKES)
    private int nDislikes;

    @Column(name = Constants.COACH_FEEDBACK_NUMBER_OF_REPLIES)
    private int nReplies;
}
