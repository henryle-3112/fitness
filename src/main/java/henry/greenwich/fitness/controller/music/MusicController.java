package henry.greenwich.fitness.controller.music;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.music.Music;
import henry.greenwich.fitness.service.music.MusicService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MusicController {
    private MusicService musicService;

    /**
     * @param musicService - inject music service
     */
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    /**
     * @param status - music's status that user want to get musics (this parameter
     *               could be optional)
     * @param search - music's title's keywords that user want to get (this
     *               parameter could be optional)
     * @param page   - start's index (for pagination) (this parameter could be
     *               optional)
     * @return list of musics
     */
    @GetMapping(value = "/musics", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Music> getMusics(HttpServletResponse response, @RequestParam("status") Integer status,
            @RequestParam("search") String search, @RequestParam("page") Integer page) {
        if (page != null) {
            int startIndex = ((page - 1) * Constants.NUMBER_ITEMS_PER_PAGE) + 1;
            int nMusics = this.musicService.getNumberOfMusics(status, search);
            response.addHeader("X-Total-Count", String.valueOf(nMusics));
            response.addHeader("X-Total-Page", String.valueOf(nMusics / Constants.NUMBER_ITEMS_PER_PAGE));
            return this.musicService.getMusicsByPage(status, search, startIndex);
        }
        return this.musicService.getMusics(status, search);
    }
}
