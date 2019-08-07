package henry.greenwich.fitness.service.music;

import henry.greenwich.fitness.model.music.Music;
import henry.greenwich.fitness.repository.music.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MusicService {
    private MusicRepository musicRepository;

    /**
     * @param musicRepository - inject musicRepository
     */
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    /**
     * @param musicStatus        - music's status that user want to get musics (this
     *                           parameter could be optional)
     * @param musicTitleKeywords - music's title's keywords that user want to get
     *                           musics (this parameter could be optional)
     * @param startIndex         - start's index (for pagination)
     * @return list of musics
     */
    public List<Music> getMusicsByPage(Integer musicStatus, String musicTitleKeywords, int startIndex) {
        List<Object> musicsObjectList = this.musicRepository.getMusicsByPage(musicStatus, musicTitleKeywords,
                startIndex);
        return this.getMusicsFromObjectList(musicsObjectList);
    }

    /**
     * @param musicStatus        - music's status that user want to count number
     *                           (this parameter could be optional)
     * @param musicTitleKeywords - music's title's keywords that user want to count
     *                           number (this parameter could be optional)
     * @return number of musics
     */
    public int getNumberOfMusics(Integer musicStatus, String musicTitleKeywords) {
        List<Object> nMusicsObjectList = this.musicRepository.getNumberOfMusics(musicStatus, musicTitleKeywords);
        if (nMusicsObjectList.size() > 0) {
            return Integer.valueOf(nMusicsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param musicsObjectList - musics object list that user want to convert to
     *                         musics list
     * @return list of musics
     */
    private List<Music> getMusicsFromObjectList(List<Object> musicsObjectList) {
        List<Music> musics = new ArrayList<>();
        for (Object o : musicsObjectList) {
            Object[] musicObjectArr = (Object[]) o;
            Music music = this.createMusicFromObjectArray(musicObjectArr);
            musics.add(music);
        }
        return musics;
    }

    /**
     * @param musicObjectArr - music object array that user want to convert to music
     *                       object
     * @return converted music object
     */
    private Music createMusicFromObjectArray(Object[] musicObjectArr) {
        int id = (int) musicObjectArr[0];
        String musicName = (String) musicObjectArr[1];
        String musicLink = (String) musicObjectArr[2];
        String musicAuthor = (String) musicObjectArr[3];
        Date createdDate = (Date) musicObjectArr[4];
        Date modifiedDate = (Date) musicObjectArr[5];
        int status = (int) musicObjectArr[6];
        String musicImage = (String) musicObjectArr[7];
        return new Music((long) id, musicName, musicLink, musicAuthor, createdDate, modifiedDate, status, musicImage);
    }

    /**
     * @param musicStatus        - music's status that user want to get musics (this
     *                           parameter could be optional)
     * @param musicTitleKeywords - music's title's keywords that user want to get
     *                           musics (this parameter could be optional)
     * @return list of musics
     */
    public List<Music> getMusics(Integer musicStatus, String musicTitleKeywords) {
        List<Object> musicsObjectList = this.musicRepository.getMusics(musicStatus, musicTitleKeywords);
        return this.getMusicsFromObjectList(musicsObjectList);
    }
}
