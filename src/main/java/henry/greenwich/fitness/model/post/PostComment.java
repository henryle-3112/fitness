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
@Table(name = Constants.POST_COMMENT_TABLE)
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_COMMENT_ID)
    private Long id;

    @Column(name = Constants.POST_COMMENT_CONTENT)
    private String postCommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_COMMENT_POST_ID)
    public Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_COMMENT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.POST_COMMENT_STATUS)
    private int postCommentStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.POST_COMMENT_CREATED_DATE)
    private Date postCommentCreatedDate;

    @Column(name = Constants.POST_COMMENT_NUMBER_OF_LIKES)
    private int numberOfLikes;

    @Column(name = Constants.POST_COMMENT_NUMBER_OF_DISLIKES)
    private int numberOfDislikes;

    @Column(name = Constants.POST_COMMENT_NUMBER_OF_REPLIES)
    private int numberOfReplies;
}
