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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.NEW_FEED_TABLE)
public class NewFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.NEW_FEED_ID)
    private Long id;

    @Column(name = Constants.NEW_FEED_IMAGE)
    private String image;

    @Column(name = Constants.NEW_FEED_ACHIEVEMENT)
    private String achievement;

    @Column(name = Constants.NEW_FEED_ACHIEVEMENT_TIME)
    private String achievementTime;

    @Column(name = Constants.NEW_FEED_CONTENT)
    private String content;

    @Column(name = Constants.NEW_FEED_STATUS)
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.NEW_FEED_CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Column(name = Constants.NEW_FEED_NUMBER_OF_LIKES)
    private int nLikes;

    @Column(name = Constants.NEW_FEED_NUMBER_OF_DISLIKES)
    private int nDislikes;

    @Column(name = Constants.NEW_FEED_NUMBER_OF_COMMENTS)
    private int nComments;
}
