package henry.greenwich.fitness.service.policy;

import henry.greenwich.fitness.model.contact.Contact;
import henry.greenwich.fitness.model.policy.PrivacyPolicy;
import henry.greenwich.fitness.repository.policy.PrivacyPolicyRepository;
import org.springframework.stereotype.Service;

@Service
public class PrivacyPolicyService {
    /**
     * policyRepository - interact with policy's data
     */
    private PrivacyPolicyRepository privacyPolicyRepository;

    /**
     * @param privacyPolicyRepository - inject policyRepository
     */
    public PrivacyPolicyService(PrivacyPolicyRepository privacyPolicyRepository) {
        this.privacyPolicyRepository = privacyPolicyRepository;
    }

    /**
     * @param id - privacy's policy's id
     * @return selected privacy policy
     */
    public PrivacyPolicy getPrivacyPolicyById(Long id) {
        return this.privacyPolicyRepository.findPrivacyPolicyById(id);
    }

    /**
     * @param privacyPolicy -  that user want to update
     * @return privacyPolicy - that was updated to the database
     */
    public PrivacyPolicy updatePrivacyPolicy(PrivacyPolicy privacyPolicy) {
        return this.privacyPolicyRepository.saveAndFlush(privacyPolicy);
    }
}
