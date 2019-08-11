package henry.greenwich.fitness.model.about;

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
@Table(name = "about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "about_name")
    private String aboutName;

    @Column(name = "about_content")
    private String aboutContent;

    @Column(name = "about_meta_title")
    private String aboutMetaTitle;

    @Column(name = "about_meta_keywords")
    private String aboutMetaKeywords;

    @Column(name = "about_meta_description")
    private String aboutMetaDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "about_created_date")
    private Date aboutCreatedDate;

    @Column(name = "about_status")
    private int aboutStatus;
}
