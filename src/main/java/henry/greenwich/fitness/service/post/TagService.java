package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Tag;
import henry.greenwich.fitness.repository.post.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepository tagRepository;

    /**
     * @param tagRepository - inject tagRepository
     */
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * @param id - tag's id that user want to get selected tag
     * @return selected tag
     */
    public Tag getTag(Long id) {
        return this.tagRepository.findTagById(id);
    }
}
