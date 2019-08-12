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
@Table(name = Constants.USER_PROFILE_TABLE)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USER_PROFILE_ID)
    private Long id;

    @Column(name = Constants.USER_PROFILE_FULL_NAME)
    private String fullName;

    @Column(name = Constants.USER_PROFILE_AVATAR)
    private String avatar;

    @Column(name = Constants.USER_PROFILE_ACCEPT_TERMS_OF_SERVICE)
    private int acceptTermsOfService;

    @Column(name = Constants.USER_PROFILE_STATUS)
    private int status;
}
