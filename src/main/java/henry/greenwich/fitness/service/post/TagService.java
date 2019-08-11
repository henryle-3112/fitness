package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Tag;
import henry.greenwich.fitness.repository.post.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    /**
     * tagRepository - interact with tag's data
     */
    private TagRepository tagRepository;

    /**
     * @param tagRepository - inject tagRepository
     */
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * @param status - tag's status
     * @return list of tags
     */
    public List<Tag> getTags(int status) {
        return this.tagRepository.findTagsByTagStatus(status);
    }

    /**
     * @param id     - tag's id
     * @param status - tag's status
     * @return list of tags
     */
    public Tag getTag(Long id, int status) {
        return this.tagRepository.findTagByIdAndTagStatus(id, status);
    }

    /**
     * @param tag - tag that user want to add to the database
     * @return inserted tag
     */
    public Tag addTag(Tag tag) {
        return this.tagRepository.saveAndFlush(tag);
    }

    /**
     * @param tag - tag that user want to update
     * @return updated tag
     */
    public Tag updateTag(Tag tag) {
        return this.tagRepository.saveAndFlush(tag);
    }

    /**
     * @param id - tag's id
     */
    public void deleteTag(Long id) {
        this.tagRepository.deleteById(id);
    }
}
