package henry.greenwich.fitness.model.feed;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE)
public class ReplyOnNewFeedCommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_ID)
    private Long id;

    @Column(name = Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_REACTION)
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_REPLY_ON_NEW_FEED_COMMENT_ID)
    public ReplyOnNewFeedComment replyOnNewFeedComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.REPLY_ON_NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID)
    public UserProfile userProfile;
}
