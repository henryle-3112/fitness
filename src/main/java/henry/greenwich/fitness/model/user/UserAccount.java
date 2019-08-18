package henry.greenwich.fitness.model.user;

import henry.greenwich.fitness.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.USER_ACCOUNT_TABLE)
public class UserAccount implements Serializable {

    @EmbeddedId
    private UserProfileKey userProfileId;

    @Column(name = Constants.USER_ACCOUNT_USER_NAME)
    private String userName;

    @Column(name = Constants.USER_ACCOUNT_PASSWORD)
    private String password;

    @Column(name = Constants.USER_ACCOUNT_PASSWORD_REMINDER_TOKEN)
    private String passwordReminderToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.USER_ACCOUNT_PASSWORD_REMINDER_EXPIRED)
    private Date passwordReminderExpired;

    @Column(name = Constants.USER_ACCOUNT_EMAIL_CONFIRMATION_TOKEN)
    private String emailConfirmationToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Constants.USER_ACCOUNT_USER_ACCOUNT_STATUS_ID)
    private UserAccountStatus userAccountStatus;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Constants.USER_ACCOUNT_USER_PROFILE_ID)
    public UserProfile userProfile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.USER_ACCOUNT_REGISTRATION_TIME)
    private Date registrationTime;
}
