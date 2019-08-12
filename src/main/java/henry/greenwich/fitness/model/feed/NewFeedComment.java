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
@Table(name = Constants.NEW_FEED_COMMENT_TABLE)
public class NewFeedComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.NEW_FEED_COMMENT_ID)
    private Long id;

    @Column(name = Constants.NEW_FEED_COMMENT_CONTENT)
    private String newFeedCommentContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.NEW_FEED_COMMENT_CREATED_DATE)
    private Date newFeedCommentCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_COMMENT_NEW_FEED_ID)
    public NewFeed newFeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_COMMENT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.NEW_FEED_COMMENT_STATUS)
    private int newFeedCommentStatus;

    @Column(name = Constants.NEW_FEED_COMMENT_NUMBER_OF_LIKES)
    private int numberOfLikes;

    @Column(name = Constants.NEW_FEED_COMMENT_NUMBER_OF_DISLIKES)
    private int numberOfDislikes;

    @Column(name = Constants.NEW_FEED_COMMENT_NUMBER_OF_REPLIES)
    private int numberOfReplies;
}
