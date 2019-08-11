package henry.greenwich.fitness.service.about;

import henry.greenwich.fitness.model.about.About;
import henry.greenwich.fitness.repository.about.AboutRepository;
import org.springframework.stereotype.Service;

@Service
public class AboutService {
    /**
     * aboutRepository - interact with about's data
     */
    private AboutRepository aboutRepository;

    /**
     * @param aboutRepository - inject aboutRepository
     */
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    /**
     * @param id - about's id
     * @return selected about
     */
    public About getAboutById(Long id) {
        return this.aboutRepository.findAboutById(id);
    }

    /**
     * @param about - about that user want to update
     * @return about - that was updated to the database
     */
    public About updateAbout(About about) {
        return this.aboutRepository.saveAndFlush(about);
    }
}
