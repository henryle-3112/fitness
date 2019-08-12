package henry.greenwich.fitness.model.contact;

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
@Table(name = Constants.CONTACT_TABLE)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CONTACT_ID)
    private Long id;

    @Column(name = Constants.CONTACT_NAME)
    private String contactName;

    @Column(name = Constants.CONTACT_CONTENT)
    private String contactContent;

    @Column(name = Constants.CONTACT_META_TITLE)
    private String contactMetaTitle;

    @Column(name = Constants.CONTACT_META_KEYWORDS)
    private String contactMetaKeywords;

    @Column(name = Constants.CONTACT_META_DESCRIPTION)
    private String contactMetaDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.CONTACT_CREATED_DATE)
    private Date contactCreatedDate;

    @Column(name = Constants.CONTACT_STATUS)
    private int contactStatus;
}
