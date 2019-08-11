package henry.greenwich.fitness.model.contact;

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
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_content")
    private String contactContent;

    @Column(name = "contact_meta_title")
    private String contactMetaTitle;

    @Column(name = "contact_meta_keywords")
    private String contactMetaKeywords;

    @Column(name = "contact_meta_description")
    private String contactMetaDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "contact_created_date")
    private Date contactCreatedDate;

    @Column(name = "contact_status")
    private int contactStatus;
}
