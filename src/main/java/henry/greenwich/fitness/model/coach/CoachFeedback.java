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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coach_feedback")
public class CoachFeedback {
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

    @Column(name = "coach_feedback_content")
    private String coachFeedbackContent;

    @Column(name = "coach_feedback_status")
    private int coachFeedbackStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "coach_feedback_created_date")
    private Date coachFeedbackCreatedDate;
}
