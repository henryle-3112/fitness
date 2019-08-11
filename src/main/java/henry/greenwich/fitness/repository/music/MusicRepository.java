package henry.greenwich.fitness.repository.music;

import henry.greenwich.fitness.model.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    /**
     *
     * @param id - music's id that user want to get
     * @return selected music
     */
    Music findMusicById(Long id);

    /**
     *
     * @param keyword - keyword when user want to search
     * @param startIndex - start index that user want to view musics
     * @return list of musics
     */
    @Query(nativeQuery = true, value = "select * from music where lower(music.music_name) like :keyword limit :startIndex, 8")
    List<Object> findMusicsByPage(String keyword, int startIndex);

    /**
     *
     * @param keyword - keyword that user want to get
     * @return number of musics
     */
    @Query(nativeQuery = true, value = "select count(*) from music where lower(music.music_name) like :keyword")
    List<Object> countMusics(String keyword);
}
