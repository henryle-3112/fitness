package henry.greenwich.fitness.model.post;

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
@Table(name = "reply_on_post_comment_reaction")
public class ReplyOnPostCommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reaction")
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_on_post_comment_id")
    public ReplyOnPostComment replyOnPostComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
