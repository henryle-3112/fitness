package henry.greenwich.fitness.controller.about;

import henry.greenwich.fitness.model.about.About;
import henry.greenwich.fitness.service.about.AboutService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AboutController {
    /**
     * aboutService - interact with about's data
     */
    private AboutService aboutService;

    /**
     * @param aboutService - inject aboutService
     */
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    /**
     * @param id - about's id that user want to get
     * @return selected about
     */
    @GetMapping(value = "/abouts/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public About getAbout(@PathVariable Long id) {
        return this.aboutService.getAboutById(id);
    }

    /**
     * @param about - that user want to update to the database
     * @return about - that was updated to the database
     */
    @PostMapping(value = "/abouts/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public About updateAbout(@RequestBody About about) {
        return this.aboutService.updateAbout(about);
    }

}
