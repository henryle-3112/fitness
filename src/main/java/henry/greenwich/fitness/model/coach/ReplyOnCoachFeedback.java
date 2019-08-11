package henry.greenwich.fitness.model.coach;

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
@Table(name = "reply_on_coach_feedback")
public class ReplyOnCoachFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reply_on_coach_feedback_content")
    private String replyOnCoachFeedbackContent;

    @Column(name = "reply_on_coach_feedback_status")
    private int replyOnCoachFeedbackStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reply_on_coach_feedback_created_date")
    private Date replyOnCoachFeedbackCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_feedback_id")
    public CoachFeedback coachFeedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
