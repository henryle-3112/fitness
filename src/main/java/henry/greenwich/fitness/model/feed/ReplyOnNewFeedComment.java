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
@Table(name = "reply_on_new_feed_comment")
public class ReplyOnNewFeedComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reply_on_new_feed_comment_content")
    private String replyOnNewFeedCommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_feed_comment_id")
    public NewFeedComment newFeedComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;

    @Column(name = "reply_on_new_feed_comment_status")
    private int replyOnNewFeedCommentStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reply_on_new_feed_comment_created_date")
    private Date replyOnNewFeedCommentCreatedDate;
}
