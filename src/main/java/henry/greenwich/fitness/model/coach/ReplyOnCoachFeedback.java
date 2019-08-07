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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.REPLY_ON_COACH_FEEDBACK_TABLE)
public class ReplyOnCoachFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.REPLY_ON_COACH_FEEDBACK_ID)
    private Long id;

    @Column(name = Constants.REPLY_ON_COACH_FEEDBACK_CONTENT)
    private String replyOnCoachFeedbackContent;

    @Column(name = Constants.REPLY_ON_COACH_FEEDBACK_STATUS)
    private int replyOnCoachFeedbackStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.REPLY_ON_COACH_FEEDBACK_CREATED_DATE)
    private Date replyOnCoachFeedbackCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_COACH_FEEDBACK_COACH_FEED_BACK_ID)
    public CoachFeedback coachFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_COACH_FEEDBACK_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.REPLY_ON_COACH_FEEDBACK_NUMBER_OF_LIKES)
    private int nLikes;

    @Column(name = Constants.REPLY_ON_COACH_FEEDBACK_NUMBER_OF_DISLIKES)
    private int nDislikes;
}
