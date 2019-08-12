package henry.greenwich.fitness.model.policy;

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
@Table(name = Constants.PRIVACY_POLICY_TABLE)
public class PrivacyPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.PRIVACY_POLICY_ID)
    private Long id;

    @Column(name = Constants.PRIVACY_POLICY_NAME)
    private String privacyPolicyName;

    @Column(name = Constants.PRIVACY_POLICY_CONTENT)
    private String privacyPolicyContent;

    @Column(name = Constants.PRIVACY_POLICY_META_TITLE)
    private String privacyPolicyMetaTitle;

    @Column(name = Constants.PRIVACY_POLICY_META_KEYWORDS)
    private String privacyPolicyMetaKeywords;

    @Column(name = Constants.PRIVACY_POLICY_META_DESCRIPTION)
    private String privacyPolicyMetaDescription;

    @Column(name = Constants.PRIVACY_POLICY_CREATED_DATE)
    private String privacyPolicyCreatedDate;

    @Column(name = Constants.PRIVACY_POLICY_STATUS)
    private String privacyPolicyStatus;
}
