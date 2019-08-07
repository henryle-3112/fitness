package henry.greenwich.fitness.controller.policy;

import henry.greenwich.fitness.model.policy.PrivacyPolicy;
import henry.greenwich.fitness.service.policy.PrivacyPolicyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("privacy-policy-management")
public class PrivacyPolicyController {
    private PrivacyPolicyService privacyPolicyService;

    /**
     *
     * @param privacyPolicyService - inject privacyPolicyService
     */
    private PrivacyPolicyController(PrivacyPolicyService privacyPolicyService) {
        this.privacyPolicyService = privacyPolicyService;
    }

    /**
     * @param id - privacy's policy's id that user want to get selected privacy
     *           policy
     * @return selected privacyPolicy
     */
    @GetMapping(value = "/policies/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PrivacyPolicy getPrivacyPolicy(@PathVariable Long id) {
        return this.privacyPolicyService.getPrivacyPolicyById(id);
    }

    /**
     * @param privacyPolicy - that user want to update to the database
     * @return privacyPolicy - that was updated to the database
     */
    @PutMapping(value = "/policies", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PrivacyPolicy updatePrivacyPolicy(@RequestBody PrivacyPolicy privacyPolicy) {
        return this.privacyPolicyService.updatePrivacyPolicy(privacyPolicy);
    }
}
