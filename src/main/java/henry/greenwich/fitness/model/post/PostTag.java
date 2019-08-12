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
@Table(name = Constants.POST_TAG_TABLE)
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.POST_TAG_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_TAG_TAG_ID)
    public Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Constants.POST_TAG_POST_ID)
    public Post post;
}
