package henry.greenwich.fitness.model.music;

import henry.greenwich.fitness.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Constants.MUSIC_TABLE)
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.MUSIC_ID)
    private Long id;

    @Column(name = Constants.MUSIC_NAME)
    private String musicName;

    @Column(name = Constants.MUSIC_LINK)
    private String musicLink;

    @Column(name = Constants.MUSIC_AUTHOR)
    private String musicAuthor;

    @Column(name = Constants.MUSIC_CREATED_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = Constants.MUSIC_MODIFIED_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = Constants.MUSIC_STATUS)
    private int status;

    @Column(name = Constants.MUSIC_IMAGE)
    private String musicImage;
}
