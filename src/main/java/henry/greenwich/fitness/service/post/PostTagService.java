package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostTag;
import henry.greenwich.fitness.model.post.Tag;
import henry.greenwich.fitness.repository.post.PostTagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostTagService {
    private PostTagRepository postTagRepository;
    private PostService postService;
    private TagService tagService;

    /**
     * @param postTagRepository - inject postTagRepository
     * @param postService       - inject postService
     * @param tagService        - inject tagService
     */
    public PostTagService(PostTagRepository postTagRepository, PostService postService, TagService tagService) {
        this.postTagRepository = postTagRepository;
        this.postService = postService;
        this.tagService = tagService;
    }

    /**
     * @param tagId      - tag's id that user want to get list of posts (this
     *                   parameter could be optional)
     * @param postStatus - post's status that user want to get list of posts (this
     *                   parameter could be optional)
     * @param startIndex - start index (for pagination) (this parameter could be
     *                   optional)
     * @return list of post tags
     */
    public List<PostTag> getPostTagsByTagPaging(Integer tagId, Integer postStatus, Integer startIndex) {
        List<Object> postTagsObjectList = this.postTagRepository.getPostTagsByTagPaging(tagId, postStatus, startIndex);
        return this.createPostTagsFromObjectList(postTagsObjectList);
    }

    /**
     * @param tagId      - tag's id that user want to get list of posts (this
     *                   parameter could be optional)
     * @param postStatus - post's status that user want to get list of posts (this
     *                   parameter could be optional)
     * @return list of post tags
     */
    public List<PostTag> getPostTagsByTag(Integer tagId, Integer postStatus) {
        List<Object> postTagsObjectList = this.postTagRepository.getPostTagsByTag(tagId, postStatus);
        return this.createPostTagsFromObjectList(postTagsObjectList);
    }

    /**
     * @param postId    - post's id that user want to get list of post tags (this
     *                  parameter could be optional)
     * @param tagStatus - tag's status that user want to get list of post tags (this
     *                  parameter could be optional)
     * @return list of post tags
     */
    public List<PostTag> getPostTagsByPost(Integer postId, Integer tagStatus) {
        List<Object> postTagsObjectList = this.postTagRepository.getPostTagsByPost(postId, tagStatus);
        return this.createPostTagsFromObjectList(postTagsObjectList);
    }

    /**
     * @param tagId      - tag's id that user want to get number of post tags (this
     *                   parameter could be optional)
     * @param postStatus - post's status that user want to get number of post tags
     *                   (this parameter could be optional)
     * @return numer of post tags
     */
    public int getNumberOfPostTagsByTag(Integer tagId, Integer postStatus) {
        List<Object> nPostTagsObjectList = this.postTagRepository.getNumberOfPostTagsByTag(tagId, postStatus);
        if (nPostTagsObjectList.size() > 0) {
            return Integer.valueOf(nPostTagsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param postTagsObjectList - post tags object list that user want to convert
     *                           to post tags list
     * @return list of post tags
     */
    private List<PostTag> createPostTagsFromObjectList(List<Object> postTagsObjectList) {
        List<PostTag> postTags = new ArrayList<>();
        for (Object o : postTagsObjectList) {
            Object[] postTagObjectArr = (Object[]) o;
            PostTag postTag = this.createPostTagFromObjectArr(postTagObjectArr);
            postTags.add(postTag);
        }
        return postTags;
    }

    /**
     * @param postTagObjectArr - post tag object array that user want to convert to
     *                         post tag
     * @return selected post tag
     */
    private PostTag createPostTagFromObjectArr(Object[] postTagObjectArr) {
        int selectedPostTagId = (int) postTagObjectArr[0];
        int selectedTagId = (int) postTagObjectArr[1];
        int selectedPostId = (int) postTagObjectArr[2];
        Tag selectedTag = this.getTag((long) selectedTagId);
        Post selectedPost = this.getPost((long) selectedPostId);
        return new PostTag((long) selectedPostTagId, selectedTag, selectedPost);
    }

    /**
     * @param tagId - tag's id that user want to get selected tag
     * @return selected tag
     */
    private Tag getTag(Long tagId) {
        return this.tagService.getTag(tagId);
    }

    /**
     * @param postId - post's id that user want to get selected post
     * @return selected post
     */
    private Post getPost(Long postId) {
        return this.postService.getPost(postId);
    }
}
