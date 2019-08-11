package henry.greenwich.fitness.model.feed;

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
@Table(name = "new_feed_comment")
public class NewFeedComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "new_feed_comment_content")
    private String newFeedCommentContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "new_feedback_comment_created_date")
    private Date newFeedCommentCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_feed_id")
    public NewFeed newFeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;

    @Column(name = "new_feedback_comment_status")
    private int newFeedCommentStatus;
}
