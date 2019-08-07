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
@Table(name = Constants.NEW_FEED_REACTION_TABLE)
public class NewFeedReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.NEW_FEED_REACTION_ID)
    private Long id;

    @Column(name = Constants.NEW_FEED_REACTION_REACTION)
    private int reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_REACTION_NEW_FEED_ID)
    public NewFeed newFeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.NEW_FEED_REACTION_USER_PROFILE_ID)
    public UserProfile userProfile;
}
