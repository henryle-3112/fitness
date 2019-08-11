package henry.greenwich.fitness.service.user;

import henry.greenwich.fitness.model.user.BodyIndex;
import henry.greenwich.fitness.repository.user.BodyIndexRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyIndexService {
    /**
     * bodyIndexRepository - interact with body index's data
     */
    private BodyIndexRepository bodyIndexRepository;

    /**
     *
     * @param bodyIndexRepository - inject bodyIndexRepository
     */
    public BodyIndexService(BodyIndexRepository bodyIndexRepository) {
        this.bodyIndexRepository = bodyIndexRepository;
    }

    /**
     *
     * @param userId - userId that user want to get list of body index
     * @return list of body index
     */
    public List<Object> getBodyIndexById(Long userId) {
        return this.bodyIndexRepository.getBodyIndexById(userId);
    }

    /**
     *
     * @param bodyIndex - bodyIndex that user want to add to the database
     * @return bodyIndex - that was inserted to the database
     */
    public BodyIndex addBodyIndex(BodyIndex bodyIndex) {
        return this.bodyIndexRepository.saveAndFlush(bodyIndex);
    }


    /**
     *
     * @param bodyIndex - bodyIndex that user want to update to the database
     * @return selected bodyIndex
     */
    public BodyIndex updateBodyIndex(BodyIndex bodyIndex) {
        return this.bodyIndexRepository.saveAndFlush(bodyIndex);
    }

    public BodyIndex getBodyIndexByCurrentDate(String currentDate) {
        return this.bodyIndexRepository.getBodyIndexByCurrentDate(currentDate);
    }
}
