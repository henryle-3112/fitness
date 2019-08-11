package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostTag;
import henry.greenwich.fitness.model.post.Tag;
import henry.greenwich.fitness.repository.post.PostTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagService {
    /**
     * postTagRepository - interact with post's data's tag
     */
    private PostTagRepository postTagRepository;

    /**
     * @param postTagRepository - inject postTagRepository
     */
    public PostTagService(PostTagRepository postTagRepository) {
        this.postTagRepository = postTagRepository;
    }

    /**
     * @return list of post tags
     */
    public List<PostTag> getPostTags() {
        return this.postTagRepository.findAll();
    }

    /**
     * @param id - post's tag's id
     * @return selected post's tag
     */
    public PostTag getPostTag(Long id) {
        return this.postTagRepository.findPostTagById(id);
    }

    /**
     * @param postTag - post's tag that user want to add
     * @return inserted post's tag
     */
    public PostTag addPostTag(PostTag postTag) {
        return this.postTagRepository.saveAndFlush(postTag);
    }

    /**
     * @param postTag - post's tag that user want to update
     * @return updated post's tag
     */
    public PostTag updatePostTag(PostTag postTag) {
        return this.postTagRepository.saveAndFlush(postTag);
    }

    /**
     * @param id - post's tag's id that user want to delete
     */
    public void deletePostTag(Long id) {
        this.postTagRepository.deleteById(id);
    }

    /**
     * @param tagId - selected tag's id
     * @param startIndex - selected start index
     * @param status - selected status
     * @return list of post tag
     */
    public List<Object> findPostTagsByTagIdAndPostStatus(int tagId, int startIndex, int status) {
        return this.postTagRepository.findPostTagsByTagIdAndPostStatus(tagId, startIndex, status);
    }

    /**
     * @param tagId - selected tag's id
     * @param status - selected status
     * @return list of post tag
     */
    public List<Object> countPostTagsByTagIdAndPostStatus(int tagId, int status) {
        return this.postTagRepository.countPostTagsByTagIdAndPostStatus(tagId, status);
    }

    /**
     * @param post - selected post
     * @return list of post tag
     */
    public List<PostTag> getPostTagsByPost(Post post) {
        return this.postTagRepository.findPostTagsByPost(post);
    }
}

