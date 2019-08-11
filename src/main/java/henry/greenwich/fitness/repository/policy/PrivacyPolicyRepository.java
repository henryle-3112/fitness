package henry.greenwich.fitness.repository.policy;

import henry.greenwich.fitness.model.policy.PrivacyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyPolicyRepository extends JpaRepository<PrivacyPolicy, Long> {
    /**
     *
     * @param id - privacy's policy's id
     * @return selected PrivacyPolicy
     */
    PrivacyPolicy findPrivacyPolicyById(Long id);
}
