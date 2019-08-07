package henry.greenwich.fitness.model.about;

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
@Table(name = Constants.ABOUT_TABLE)
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ABOUT_ID)
    private Long id;

    @Column(name = Constants.ABOUT_NAME)
    private String aboutName;

    @Column(name = Constants.ABOUT_CONTENT)
    private String aboutContent;

    @Column(name = Constants.ABOUT_META_TITLE)
    private String aboutMetaTitle;

    @Column(name = Constants.ABOUT_META_KEYWORDS)
    private String aboutMetaKeywords;

    @Column(name = Constants.ABOUT_META_DESCRIPTION)
    private String aboutMetaDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.ABOUT_CREATED_DATE)
    private Date aboutCreatedDate;

    @Column(name = Constants.ABOUT_STATUS)
    private int aboutStatus;
}
