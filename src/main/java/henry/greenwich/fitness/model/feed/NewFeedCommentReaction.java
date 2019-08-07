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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.NEW_FEED_COMMENT_REACTION_TABLE)
public class NewFeedCommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.NEW_FEED_COMMENT_REACTION_ID)
    private Long id;

    @Column(name = Constants.NEW_FEED_COMMENT_REACTION_REACTION)
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_COMMENT_REACTION_NEW_FEED_COMMENT_ID)
    public NewFeedComment newFeedComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID)
    public UserProfile userProfile;
}
