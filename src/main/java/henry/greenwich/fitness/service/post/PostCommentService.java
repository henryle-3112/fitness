package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostComment;
import henry.greenwich.fitness.repository.post.PostCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {

    /**
     * postCommentRepostitory - interact with post's comment repository
     */
    private PostCommentRepository postCommentRepository;

    /**
     * @param postCommentRepository - inject postCommentRepository
     */
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    /**
     * @param post - that user wants to get comments
     * @return list of post's comments
     */
    public List<PostComment> getPostCommentsByPostAndPostCommentStatus(Post post, int status) {
        return this.postCommentRepository.findPostCommentsByPostAndPostCommentStatus(post, status);
    }

    /**
     * @param postComment - that user wants to add to the database
     * @return postComment - that was inserted to the database
     */
    public PostComment addPostComment(PostComment postComment) {
        return this.postCommentRepository.saveAndFlush(postComment);
    }


    /**
     * @param id - post's comment's id that user want to delete
     */
    public void deletePostComment(Long id) {
        this.postCommentRepository.deleteById(id);
    }
}
