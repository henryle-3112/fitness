package henry.greenwich.fitness.model.feed;

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
@Table(name = "reply_on_new_feed_comment_reaction")
public class ReplyOnNewFeedCommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reaction")
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_on_new_feed_comment_id")
    public ReplyOnNewFeedComment replyOnNewFeedComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
