package henry.greenwich.fitness.model.user;

import henry.greenwich.fitness.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.USER_ROLE_TABLE)
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRoleKey userRoleKey;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.USER_ROLE_USER_PROFILE_ID)
    private UserProfile userProfile;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.USER_ROLE_ROLE_ID)
    private Role role;
}
