package henry.greenwich.fitness.controller.music;

import henry.greenwich.fitness.model.music.Music;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.music.MusicService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class MusicController {
    /**
     * musicService - interact with music's data
     */
    private MusicService musicService;

    /**
     *
     * @param musicService - inject music service
     */
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    /**
     *
     * @return list of musics
     */
    @GetMapping(value = "/musics", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Music> getMusics() {
        return this.musicService.getMusics();
    }

    /**
     *
     * @param id - if of music that user want to get
     * @return - selected music
     */
    @GetMapping(value = "/musics/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Music getMusic(@PathVariable Long id) {
        return this.musicService.getMusicById(id);
    }

    /**
     *
     * @param music - that user want to add to the database
     * @return inserted music
     */
    @PostMapping(value = "/musics/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Music addMusic(@RequestBody Music music) {
        return this.musicService.addMusic(music);
    }

    /**
     *
     * @param music - that user want to update to the database
     * @return updated music
     */
    @PostMapping(value = "/musics/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Music updateMusic(@RequestBody Music music) {
        return this.musicService.updateMusic(music);
    }

    /**
     *
     * @param id - id of music that user want to delete
     */
    @PostMapping(value = "/musics/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteMusic(@PathVariable Long id) {
        this.musicService.deleteMusic(id);
    }

    /**
     *
     * @param page - current page
     * @param keyword - pagination based on keyword
     * @return list of musics
     */
    @GetMapping(value = "/musics/paging/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Music> getMusicsByPage(@PathVariable int page, @RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        int startIndex = ((page - 1) * 8) + 1;
        List<Music> musics = new ArrayList<>();
        List<Object> musicsObject = this.musicService.findMusicsByPage(selectedKeyWord, startIndex - 1);
        for (int i = 0; i < musicsObject.size(); i++) {
            Object[] eachMusicObject = (Object[]) musicsObject.get(i);
            int id = (int) eachMusicObject[0];
            String musicName = (String) eachMusicObject[1];
            String musicLink = (String) eachMusicObject[2];
            String musicAuthor = (String) eachMusicObject[3];
            Date createdDate = (Date) eachMusicObject[4];
            Date modifiedDate = (Date) eachMusicObject[5];
            int status = (int) eachMusicObject[6];
            String musicImage = (String) eachMusicObject[7];
            Music music = new Music(
                    (long) id,
                    musicName,
                    musicLink,
                    musicAuthor,
                    createdDate,
                    modifiedDate,
                    status,
                    musicImage
            );
            musics.add(music);
        }
        return musics;
    }

    /**
     *
     * @param keyword - number of musics based on keywords
     * @return number of musics
     */
    @GetMapping(value = "/musics/count", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countMusics(@RequestParam Optional<String> keyword) {
        String paramKeywords = keyword.orElse(null);
        String selectedKeyWord = "%%";
        if (paramKeywords != null && !paramKeywords.equals("")) {
            selectedKeyWord = "%" + paramKeywords + "%";
        }
        List<Object> countMusicsObject = this.musicService.countMusics(selectedKeyWord);
        Object eachCountMusic = countMusicsObject.get(0);
        return new ResponseMessage(eachCountMusic.toString());
    }
}
