package henry.greenwich.fitness.repository.music;

import henry.greenwich.fitness.constants.Constants;
import henry.greenwich.fitness.model.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
        String GET_MUSIC_BY_PAGE = "select * from " + Constants.MUSIC_TABLE + "" + " where (:musicStatus is null or "
                        + Constants.MUSIC_TABLE + "." + Constants.MUSIC_STATUS + " = :musicStatus)"
                        + " and (:musicTitleKeywords is null or lower(" + Constants.MUSIC_TABLE + "."
                        + Constants.MUSIC_NAME + ") like %:musicTitleKeywords%) limit :startIndex, "
                        + Constants.NUMBER_ITEMS_PER_PAGE;

        String GET_NUMBER_MUSICS = "select count(*) from " + Constants.MUSIC_TABLE + ""
                        + " where (:musicStatus is null or " + Constants.MUSIC_TABLE + "." + Constants.MUSIC_STATUS
                        + " = :musicStatus)" + " and (:musicTitleKeywords is null or lower(" + Constants.MUSIC_TABLE
                        + "." + Constants.MUSIC_NAME + ") like %:musicTitleKeywords%)";

        String GET_MUSICS = "select * from " + Constants.MUSIC_TABLE + "" + " where (:musicStatus is null or "
                        + Constants.MUSIC_TABLE + "." + Constants.MUSIC_STATUS + " = :musicStatus)"
                        + " and (:musicTitleKeywords is null or lower(" + Constants.MUSIC_TABLE + "."
                        + Constants.MUSIC_NAME + ") like %:musicTitleKeywords%)";

        /**
         * @param musicStatus        - music's status that user want to get musics (this
         *                           parameter could be optional)
         * @param musicTitleKeywords - music's title's keywords that user want to get
         *                           musics (this parameter could be optional)
         * @param startIndex         - start's index (for pagination) (this parameter
         *                           could be optional)
         * @return list of musics
         */
        @Query(nativeQuery = true, value = GET_MUSIC_BY_PAGE)
        List<Object> getMusicsByPage(@RequestParam("musicStatus") Integer musicStatus,
                        @RequestParam("musicTitleKeywords") String musicTitleKeywords,
                        @RequestParam("startIndex") Integer startIndex);

        /**
         * @param musicStatus        - music's status that user want to count number of
         *                           musics (this parameter could be optional)
         * @param musicTitleKeywords - music's title's keywords that user want to count
         *                           number of musics (this parameter could be optional)
         * @return number of musics
         */
        @Query(nativeQuery = true, value = GET_NUMBER_MUSICS)
        List<Object> getNumberOfMusics(@RequestParam("musicStatus") Integer musicStatus,
                        @RequestParam("musicTitleKeywords") String musicTitleKeywords);

        /**
         * @param musicStatus        - music's status that user want to get musics (this
         *                           parameter could be optional)
         * @param musicTitleKeywords - music's title's keywords that user want to get
         *                           musics (this parameter could be optional)
         * @return list of musics
         */
        @Query(nativeQuery = true, value = GET_MUSICS)
        List<Object> getMusics(@RequestParam("musicStatus") Integer musicStatus,
                        @RequestParam("musicTitleKeywords") String musicTitleKeywords);
}
