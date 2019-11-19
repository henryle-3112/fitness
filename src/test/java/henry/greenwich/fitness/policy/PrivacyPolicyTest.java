package henry.greenwich.fitness.policy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import henry.greenwich.fitness.model.policy.PrivacyPolicy;
import henry.greenwich.fitness.repository.policy.PrivacyPolicyRepository;
import henry.greenwich.fitness.service.policy.PrivacyPolicyService;

public class PrivacyPolicyTest {
	private PrivacyPolicy privacyPolicy;

	@Mock
	private PrivacyPolicyRepository privacyPolicyRepository;

	@InjectMocks
	private PrivacyPolicyService privacyPolicyService;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);

		this.privacyPolicy = new PrivacyPolicy(1L, "Policy", "Policy", "Policy", "Policy", "Policy", "", "");
	}

	@Test
	public void testGetPrivacyPolicyById() {
		when(this.privacyPolicyRepository.findPrivacyPolicyById(1L)).thenReturn(this.privacyPolicy);

		PrivacyPolicy selectedPrivacyPolicy = this.privacyPolicyService.getPrivacyPolicyById(1L);

		assertNotNull(selectedPrivacyPolicy);
	}

	@Test
	public void testUpdatePrivacyPolicy() {
		when(this.privacyPolicyRepository.saveAndFlush(this.privacyPolicy)).thenReturn(this.privacyPolicy);

		PrivacyPolicy updatedPrivacyPolicy = this.privacyPolicyService.updatePrivacyPolicy(this.privacyPolicy);

		assertNotNull(updatedPrivacyPolicy);
	}
}
