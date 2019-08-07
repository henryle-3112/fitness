package henry.greenwich.fitness.model.user;


import henry.greenwich.fitness.constants.Constants;
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
@Table(name = Constants.FACEBOOK_ACCOUNT_TABLE)
public class FacebookAccount {

    @EmbeddedId
    private UserProfileKey userProfileId;

    @Column(name = Constants.FACEBOOK_ACCOUNT_FACEBOOK_ID)
    private String facebookId;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.FACEBOOK_ACCOUNT_USER_PROFILE_ID)
    public UserProfile userProfile;
}
