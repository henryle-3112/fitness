package henry.greenwich.fitness.model.user;


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
@Table(name = "facebook_account")
public class FacebookAccount {

    @EmbeddedId
    private UserProfileKey userProfileId;

    @Column(name = "facebook_id")
    private String facebookId;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
