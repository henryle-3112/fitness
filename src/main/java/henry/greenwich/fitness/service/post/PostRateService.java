package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.PostRate;
import henry.greenwich.fitness.model.product.ProductRate;
import henry.greenwich.fitness.repository.post.PostRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostRateService {
    /**
     * postRateRepository - interact with post's rate's data
     */
    private PostRateRepository postRateRepository;

    /**
     * @param postRateRepository - inject post's rate's repository
     */
    public PostRateService(PostRateRepository postRateRepository) {
        this.postRateRepository = postRateRepository;
    }

    /**
     * @return list of post's rates
     */
    public List<PostRate> getPostRates() {
        return this.postRateRepository.findAll();
    }

    /**
     * @param id - post's rate's id that user want to get
     * @return list of post's rate
     */
    public PostRate getPostRate(Long id) {
        return this.postRateRepository.findPostRateById(id);
    }

    /**
     * @param postRate - that user want to add to the database
     * @return postRate - that was inserted to the database
     */
    public PostRate addPostRate(PostRate postRate) {
        // check postRate existed in the database or not
        // if not, create new one, if yes, just update
        List<Object> postRateObjectList = this.postRateRepository.getPostRateByUserIdAndByPostId(
                postRate.getUserProfile().getId(),
                postRate.getPost().getId()
        );
        if (postRateObjectList.isEmpty()) {
            return this.postRateRepository.saveAndFlush(postRate);
        }
        Object[] postRateObjectArray = (Object[]) postRateObjectList.get(0);
        int postRateId = (int) postRateObjectArray[0];
        postRate.setId((long) postRateId);
        return this.postRateRepository.saveAndFlush(postRate);
    }

    /**
     * @param postRate - that user want to update to the database
     * @return postRate- tha was updated to the database
     */
    public PostRate updatePostRate(PostRate postRate) {
        return this.postRateRepository.saveAndFlush(postRate);
    }

    /**
     * @param id - post's rate's id that user want to delete
     */
    public void deletePostRate(Long id) {
        this.postRateRepository.deleteById(id);
    }

    /**
     * @param userId - user's id
     * @param postId - post's id
     * @return list of post's rate
     */
    public List<Object> getPostRateByUserIdAndPostId(Long userId, Long postId) {
        return this.postRateRepository.getPostRateByUserIdAndByPostId(userId, postId);
    }
}
