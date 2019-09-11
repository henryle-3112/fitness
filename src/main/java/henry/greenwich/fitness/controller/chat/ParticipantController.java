package henry.greenwich.fitness.controller.chat;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import henry.greenwich.fitness.model.chat.Participant;
import henry.greenwich.fitness.service.chat.ParticipantService;

@Controller
@RequestMapping("chat-management")
public class ParticipantController {
    private ParticipantService participantService;

    /**
     * @param participantService - inject participantService
     */
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    /**
     * 
     * @param participant - participant that will be added to the database
     * @return inserted participant
     */
    @PostMapping(value = "/participants", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Participant addParticipant(@RequestBody Participant participant) {
        return this.participantService.addParticipant(participant);
    }

    /**
     * 
     * @param userProfileId - user's profile's id that will be used to get selected
     *                      participant
     * @param coachId       - coach's id that will be used to get selected
     *                      participant
     * @return selected participant
     */
    @GetMapping(value = "/participants", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Participant getParticipant(@RequestParam(required = false) Long userProfileId,
            @RequestParam(required = false) Integer coachId) {
        return this.participantService.getParticipant(userProfileId, coachId);
    }

}
