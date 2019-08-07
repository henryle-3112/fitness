package henry.greenwich.fitness.model.post;

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
@Table(name = Constants.TAG_TABLE)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.TAG_ID)
    private Long id;

    @Column(name = Constants.TAG_NAME)
    private String tagName;

    @Column(name = Constants.TAG_STATUS)
    private int tagStatus;
}
