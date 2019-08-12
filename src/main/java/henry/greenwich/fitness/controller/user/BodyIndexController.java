package henry.greenwich.fitness.controller.user;

import henry.greenwich.fitness.model.user.BodyIndex;
import henry.greenwich.fitness.service.user.BodyIndexService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user-management")
public class BodyIndexController {
    private BodyIndexService bodyIndexService;

    /**
     * @param bodyIndexService - inject bodyIndexService
     */
    public BodyIndexController(BodyIndexService bodyIndexService) {
        this.bodyIndexService = bodyIndexService;
    }

    /**
     * @param userProfileId - userId that user want body's indexes
     * @return list of body indexes
     */
    @GetMapping(value = "/indexes", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<BodyIndex> getBodyIndexById(@RequestParam(required = false) Integer userProfileId) {
        return this.bodyIndexService.getBodyIndexes(userProfileId);
    }

    /**
     * @param bodyIndex - that user want to add to the database
     * @return inserted body index
     */
    @PostMapping(value = "/indexes", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BodyIndex addBodyIndex(@RequestBody BodyIndex bodyIndex) {
        return this.bodyIndexService.addBodyIndex(bodyIndex);
    }

    /**
     * @param bodyIndex - that user want to update to the database
     * @return updated body index
     */
    @PutMapping(value = "/indexes", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BodyIndex updateBodyIndex(@RequestBody BodyIndex bodyIndex) {
        return this.bodyIndexService.updateBodyIndex(bodyIndex);
    }
}
