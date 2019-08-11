package henry.greenwich.fitness.repository.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostTag;
import henry.greenwich.fitness.model.post.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    /**
     * @param id - post's tag's id
     * @return selected post's tag
     */
    PostTag findPostTagById(Long id);

    /**
     *
     * @param tagId - tag's id
     * @param startIndex - start index
     * @param status - post's status
     * @return list of post tag
     */
    @Query(nativeQuery = true, value = "select post_tag.id, post_tag.tag_id, post_tag.post_id from post_tag inner join post on post_tag.post_id = post.id where post_tag.tag_id = :tagId and post.status = :status limit :startIndex, 8")
    List<Object> findPostTagsByTagIdAndPostStatus(int tagId, int startIndex, int status);

    /**
     *
     * @param tagId - tag's id
     * @param status - start index
     * @return number of post tag
     */
    @Query(nativeQuery = true, value = "select count(*) from post_tag inner join post on post_tag.post_id = post.id where post_tag.tag_id = :tagId and post.status = :status")
    List<Object> countPostTagsByTagIdAndPostStatus(int tagId, int status);

    /**
     *
     * @param post - selected post
     * @return list of post tag
     */
    List<PostTag> findPostTagsByPost(Post post);
}
