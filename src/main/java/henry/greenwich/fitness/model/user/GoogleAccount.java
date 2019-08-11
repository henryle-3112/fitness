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
@Table(name = "google_account")
public class GoogleAccount {
    @EmbeddedId
    private UserProfileKey userProfileId;

    @Column(name = "google_id")
    private String googleId;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
