package henry.greenwich.fitness.model.feed;

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
@Table(name = Constants.REPLY_ON_NEW_FEED_COMMENT_TABLE)
public class ReplyOnNewFeedComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_ID)
    private Long id;

    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_CONTENT)
    private String replyOnNewFeedCommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_NEW_FEED_COMMENT_NEW_FEED_COMMENT_ID)
    public NewFeedComment newFeedComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_NEW_FEED_COMMENT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_STATUS)
    private int replyOnNewFeedCommentStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_CREATED_DATE)
    private Date replyOnNewFeedCommentCreatedDate;

    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_NUMBER_OF_LIKES)
    private int nLikes;

    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_NUMBER_OF_DISLIKES)
    private int nDislikes;
}
