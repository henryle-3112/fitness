package henry.greenwich.fitness.model.policy;

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
@Table(name = "privacy_policy")
public class PrivacyPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "privacy_policy_name")
    private String privacyPolicyName;

    @Column(name = "privacy_policy_content")
    private String privacyPolicyContent;

    @Column(name = "privacy_policy_meta_title")
    private String privacyPolicyMetaTitle;

    @Column(name = "privacy_policy_meta_keywords")
    private String privacyPolicyMetaKeywords;

    @Column(name = "privacy_policy_meta_description")
    private String privacyPolicyMetaDescription;

    @Column(name = "privacy_policy_created_date")
    private String privacyPolicyCreatedDate;

    @Column(name = "privacy_policy_status")
    private String privacyPolicyStatus;
}
