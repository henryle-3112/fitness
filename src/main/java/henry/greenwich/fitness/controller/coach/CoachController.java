package henry.greenwich.fitness.controller.coach;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Controller
public class CoachController {
    /**
     * coachService - interact with coach's data
     * userProfileService - interact with user's profile's data
     */
    private CoachService coachService;
    private UserProfileService userProfileService;

    /**
     * @param coachService - inject coachService
     */
    public CoachController(CoachService coachService,
                           UserProfileService userProfileService) {
        this.coachService = coachService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param status - coach's status
     * @return list of coaches
     */
    @GetMapping(value = "/coaches/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Coach> getCoaches(@PathVariable int status) {
        return this.coachService.getCoaches(status);
    }


    /**
     * @param id     - coach's id
     * @param status - coach's status
     * @return selected coach
     */
    @GetMapping(value = "/coaches/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Coach getCoach(@PathVariable Long id, @PathVariable int status) {
        return this.coachService.getCoach(id, status);
    }

    /**
     * @param coach - coach
     * @return inserted coach
     */
    @PostMapping(value = "/coaches/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Coach addCoach(@RequestBody Coach coach) {
        return this.coachService.addCoach(coach);
    }

    /**
     * @param userProfile - user's profile
     * @return selected coach
     */
    @PostMapping(value = "/coaches/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Coach getCoachByUserProfile(@RequestBody UserProfile userProfile, @PathVariable int status) {
        return this.coachService.getCoachByUserProfile(userProfile, status);
    }

    /**
     * @param coach - coach
     * @return update coach
     */
    @PostMapping(value = "/coaches/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Coach updateCoach(@RequestBody Coach coach) {
        return this.coachService.updateCoach(coach);
    }

    /**
     * @param id - coach's id
     */
    @PostMapping(value = "/coaches/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void updateCoach(@PathVariable Long id) {
        this.coachService.deleteCoach(id);
    }

    /**
     * @param page    - current page
     * @param keyword - keyword that user want to search
     * @return list of coaches
     */
    @GetMapping(value = "/coaches/paging/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Coach> getCoachesByPage(@PathVariable int page, @PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Coach> coaches = new ArrayList<>();
        List<Object> coachesObject = this.coachService.findCoachesByPage(selectedKeyWord, startIndex - 1, status);
        for (Object o : coachesObject) {
            Object[] eachCoachObject = (Object[]) o;
            int coachId = (int) eachCoachObject[0];
            int userProfileId = (int) eachCoachObject[1];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            String coachAbout = (String) eachCoachObject[2];
            int coachStatus = (int) eachCoachObject[3];
            Coach coach = new Coach(
                    (long) coachId,
                    userProfile,
                    coachAbout,
                    coachStatus
            );
            coaches.add(coach);
        }
        return coaches;
    }

    /**
     * @param keyword - keyword
     * @return number of coaches
     */
    @GetMapping(value = "/coaches/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countCoaches(@PathVariable int status, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countCoachesObject = this.coachService.countCoaches(selectedKeyWord, status);
        Object eachCountCoachesObject = countCoachesObject.get(0);
        return new ResponseMessage(eachCountCoachesObject.toString());
    }
}
