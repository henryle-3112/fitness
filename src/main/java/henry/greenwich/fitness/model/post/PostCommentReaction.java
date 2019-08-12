package henry.greenwich.fitness.model.post;

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
@Table(name = Constants.POST_COMMENT_REACTION_TABLE)
public class PostCommentReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_COMMENT_REACTION_ID)
    private Long id;

    @Column(name = Constants.POST_COMMENT_REACTION_REACTION)
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_COMMENT_REACTION_POST_COMMENT_ID)
    public PostComment postComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_COMMENT_REACTION_USER_PROFILE_ID)
    public UserProfile userProfile;
}
