package henry.greenwich.fitness.service.about;

import henry.greenwich.fitness.model.about.About;
import henry.greenwich.fitness.repository.about.AboutRepository;

import org.springframework.stereotype.Service;

@Service
public class AboutService {
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
}
