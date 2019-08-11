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
@Table(name = "body_index")
public class BodyIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_index_id")
    private Long bodyIndexId;

    @Column(name = "body_index_weight")
    private float weight;

    @Column(name = "body_index_height")
    private float height;

    @Column(name = "body_index_date")
    private String currentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    public UserProfile userProfile;
}
