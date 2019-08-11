package henry.greenwich.fitness.service.music;

import henry.greenwich.fitness.model.music.Music;
import henry.greenwich.fitness.repository.music.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    /**
     * musicRepository - interact with music's data
     */
    private MusicRepository musicRepository;

    /**
     * @param musicRepository - inject musicRepository
     */
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    /**
     * @return list of musics
     */
    public List<Music> getMusics() {
        return this.musicRepository.findAll();
    }

    /**
     * @param id - id of music that user want to get
     * @return selected music
     */
    public Music getMusicById(Long id) {
        return this.musicRepository.findMusicById(id);
    }

    /**
     * @param music - music that user want to add to the database
     * @return music that was inserted to the database
     */
    public Music addMusic(Music music) {
        return this.musicRepository.saveAndFlush(music);
    }

    /**
     * @param music - music that user want to update to the database
     * @return music that was updated to the database
     */
    public Music updateMusic(Music music) {
        return this.musicRepository.saveAndFlush(music);
    }

    /**
     * @param id - music's id that user want to delete from the database
     */
    public void deleteMusic(Long id) {
        this.musicRepository.deleteById(id);
    }

    /**
     * @param startIndex      - start index that user want to view
     * @param selectedKeyWord - keyword that user want to get musics
     * @return list of musics
     */
    public List<Object> findMusicsByPage(String selectedKeyWord, int startIndex) {
        return this.musicRepository.findMusicsByPage(selectedKeyWord, startIndex);
    }

    /**
     * @param selectedKeyWord - keyword that user want to get musics
     * @return number of musics
     */
    public List<Object> countMusics(String selectedKeyWord) {
        return this.musicRepository.countMusics(selectedKeyWord);
    }
}
