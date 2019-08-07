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
@Table(name = Constants.USER_ACCOUNT_STATUS_TABLE)
public class UserAccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.USER_ACCOUNT_STATUS_ID)
    private Long id;

    @Column(name = Constants.USER_ACCOUNT_STATUS_NAME)
    private String name;
}
