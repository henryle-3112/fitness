package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.repository.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    /**
     * postRepository - interact with post's data
     */
    private PostRepository postRepository;

    /**
     * @param postRepository - inject postRepository
     */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * @param status - post's status
     * @return list of posts
     */
    public List<Post> getPosts(int status) {
        return this.postRepository.findPostsByPostStatus(status);
    }

    /**
     * @param id     - post's id
     * @param status - post's status
     * @return selected post
     */
    public Post getPost(Long id, int status) {
        return this.postRepository.findPostByIdAndPostStatus(id, status);
    }

    /**
     * @param post - that user want to add to the database
     * @return post that was inserted to the database
     */
    public Post addPost(Post post) {
        return this.postRepository.saveAndFlush(post);
    }

    /**
     * @param post - that user want to update to the database
     * @return post that was updated to the database
     */
    public Post updatePost(Post post) {
        return this.postRepository.saveAndFlush(post);
    }

    /**
     * @param id - post's id that user want to delete
     */
    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }

    /**
     * @param top        - number of posts that user want to get
     * @param categoryId - category's id that posts belong to
     * @param status     - post's status
     * @return list of posts
     */
    public List<Object> getTopPosts(int top, int categoryId, int status) {
        return this.postRepository.findTopPostsByCategoryAndPostStatus(categoryId, top, status);
    }

    /**
     * @param categoryId - category's id that posts belong to
     * @param startIndex - start index
     * @return list of posts
     */
    public List<Object> findPostsByCategoryAndByPage(int categoryId, int startIndex, int status) {
        return this.postRepository.findPostsByCategoryAndByPage(categoryId, startIndex, status);
    }

    /**
     * @param categoryId - category's id that posts belong to
     * @param status     - post's status
     * @return number of posts
     */
    public List<Object> countPostsByCategoryAndByPostStatus(int categoryId, int status) {
        return this.postRepository.countPostsByCategoryAndByPostStatus(categoryId, status);
    }

    /**
     * @param categoryId       - post's category's id
     * @param postNameKeywords - post's name's keywords
     * @param status           - post's status
     * @return list of posts
     */
    public List<Object> countSearchingPosts(Integer categoryId,
                                            String postNameKeywords,
                                            int status) {
        return this.postRepository.countSearchingPosts(categoryId,
                postNameKeywords,
                status);
    }

    /**
     * @param selectedCategoryId       - selected post's category's id
     * @param selectedPostNameKeywords - selected post's name's keywords
     * @param startIndex               - start index
     * @param status                   - post's status
     * @return searching posts
     */
    public List<Object> findSearchingPostsByPage(
            Integer selectedCategoryId,
            String selectedPostNameKeywords,
            int startIndex,
            int status) {
        return this.postRepository.findSearchingPostsByPage(
                selectedCategoryId,
                selectedPostNameKeywords,
                startIndex,
                status);
    }

    /**
     *
     * @param id - post's id
     * @return selected post
     */
    public Post getPost(Long id) {
        return this.postRepository.findPostsById(id);
    }
}
