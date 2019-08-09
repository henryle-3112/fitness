package henry.greenwich.fitness.model.post;

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
@Table(name = Constants.REPLY_ON_POST_COMMENT_TABLE)
public class ReplyOnPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.REPLY_ON_POST_COMMENT_ID)
    private Long id;

    @Column(name = Constants.REPLY_ON_POST_COMMENT_CONTENT)
    private String replyOnPostCommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_POST_COMMENT_POST_COMMENT_ID)
    public PostComment postComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_POST_COMMENT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.REPLY_ON_POST_COMMENT_STATUS)
    private int replyOnPostCommentStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.REPLY_ON_POST_COMMENT_CREATED_DATE)
    private Date replyOnPostCommentCreatedDate;

    @Column(name = Constants.REPLY_ON_POST_COMMENT_NUMBER_OF_LIKES)
    private int numberOfLikes;

    @Column(name = Constants.REPLY_ON_POST_COMMENT_NUMBER_OF_DISLIKES)
    private int numberOfDislikes;
}
