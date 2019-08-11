package henry.greenwich.fitness.model.user;

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
@Table(name = "user_account")
public class UserAccount implements Serializable{

    @EmbeddedId
    private UserProfileKey userProfileId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "password_reminder_token")
    private String passwordReminderToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "password_reminder_expired")
    private Date passwordReminderExpired;

    @Column(name = "email_confirmation_token")
    private String emailConfirmationToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_status_id")
    private UserAccountStatus userAccountStatus;

    @MapsId("userProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_time")
    private Date registrationTime;
}
