package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostRate;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.PostRateRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class PostRateService {
    private PostRateRepository postRateRepository;
    private UserProfileService userProfileService;
    private PostService postService;

    /**
     * @param postRateRepository - inject postRateRepository
     * @param userProfileService - inject userProfileService
     * @param postService        - inject postService
     */
    public PostRateService(PostRateRepository postRateRepository, UserProfileService userProfileService,
            PostService postService) {
        this.postRateRepository = postRateRepository;
        this.userProfileService = userProfileService;
        this.postService = postService;
    }

    /**
     * @param postRate - that user want to add to the database
     * @return postRate - that was inserted to the database
     */
    public PostRate addPostRate(PostRate postRate) {
        // check postRate existed in the database or not
        // if not, create new one, if yes, just update
        PostRate selectedPostRate = this.getPostRateByUserProfileIsAndPostId(postRate.getUserProfile().getId(),
                postRate.getPost().getId());
        if (selectedPostRate != null) {
            selectedPostRate.setRate(postRate.getRate());
            return this.updatePostRate(selectedPostRate);
        }
        return this.postRateRepository.saveAndFlush(postRate);
    }

    /**
     * @param postRate - that user want to update to the database
     * @return updated post's rate
     */
    private PostRate updatePostRate(PostRate postRate) {
        return this.postRateRepository.saveAndFlush(postRate);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      post's rate
     * @param postId        - post's id that user want to get selected post's rate
     * @return selected post's rate
     */
    public PostRate getPostRateByUserProfileIsAndPostId(Long userProfileId, Long postId) {
        UserProfile userProfile = this.userProfileService.getUserProfile(userProfileId);
        Post post = this.postService.getPost(postId);
        return this.postRateRepository.findPostRateByUserProfileAndPost(userProfile, post);
    }
}
