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
@Table(name = Constants.GOOGLE_ACCOUNT_TABLE)
public class GoogleAccount {
    @EmbeddedId
    private UserProfileKey userProfileId;

    @Column(name = Constants.GOOGLE_ACCOUNT_GOOGLE_ID)
    private String googleId;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.GOOGLE_ACCOUNT_USER_PROFILE_ID)
    public UserProfile userProfile;
}
