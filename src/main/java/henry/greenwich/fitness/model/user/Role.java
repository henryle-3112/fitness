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
@Table(name = Constants.ROLE_TABLE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ROLE_ID)
    private Long id;

    @Column(name = Constants.ROLE_NAME)
    private String name;

    @Column(name = Constants.ROLE_STATUS)
    private int status;
}
