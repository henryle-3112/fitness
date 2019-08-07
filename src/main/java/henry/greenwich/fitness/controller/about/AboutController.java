package henry.greenwich.fitness.controller.about;

import henry.greenwich.fitness.model.about.About;
import henry.greenwich.fitness.service.about.AboutService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("about-management")
public class AboutController {
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
    @GetMapping(value = "/abouts/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public About getAbout(@PathVariable Long id) {
        return this.aboutService.getAboutById(id);
    }

}
