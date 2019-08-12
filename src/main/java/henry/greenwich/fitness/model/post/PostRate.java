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
@Table(name = Constants.POST_RATE_TABLE)
public class PostRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_RATE_ID)
    private Long id;

    @Column(name = Constants.POST_RATE_RATE)
    private int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_RATE_POST_ID)
    public Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_RATE_USER_PROFILE_ID)
    public UserProfile userProfile;
}
