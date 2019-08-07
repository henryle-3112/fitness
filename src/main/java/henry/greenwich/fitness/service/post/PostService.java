package henry.greenwich.fitness.service.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostCategory;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.post.PostRepository;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private PostCategoryService postCategoryService;
    private UserProfileService userProfileService;

    /**
     * @param postRepository      - inject postRepository
     * @param postCategoryService - inject postCategoryService
     * @param userProfileService  - userProfileService postRepository
     */
    public PostService(PostRepository postRepository, PostCategoryService postCategoryService,
            UserProfileService userProfileService) {
        this.postRepository = postRepository;
        this.postCategoryService = postCategoryService;
        this.userProfileService = userProfileService;
    }

    /**
     * @param postCategoryId   - post's category's id that user want to get posts
     *                         (this paramter could be optional)
     * @param postStatus       - post's status that user want to get posts (this
     *                         paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get posts
     *                         (this paramter could be optional)
     * @param startIndex       - start index (for pagination) (this paramter could
     *                         be optional)
     * @return list of posts
     */
    public List<Post> getPostsPaging(Integer postCategoryId, Integer postStatus, String postNameKeywords,
            Integer startIndex) {
        List<Object> postsObjectList = this.postRepository.getPostsPaging(postCategoryId, postStatus, postNameKeywords,
                startIndex);
        return this.getPostsFromObjectList(postsObjectList);
    }

    /**
     * @param postCategoryId   - post's category's id that user want to get posts
     *                         (this paramter could be optional)
     * @param postStatus       - post's status that user want to get posts (this
     *                         paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get posts
     *                         (this paramter could be optional)
     * @return list of posts
     */
    public List<Post> getPosts(Integer postCategoryId, Integer postStatus, String postNameKeywords) {
        List<Object> postsObjectList = this.postRepository.getPosts(postCategoryId, postStatus, postNameKeywords);
        return this.getPostsFromObjectList(postsObjectList);
    }

    /**
     * @param postCategoryId   - post's category's id that user want to get number
     *                         of posts (this paramter could be optional)
     * @param postStatus       - post's status that user want to get number of posts
     *                         (this paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get number
     *                         of posts (this paramter could be optional)
     * @return number of posts
     */
    public int getNumberOfPosts(Integer postCategoryId, Integer postStatus, String postNameKeywords) {
        List<Object> nPostsObjectList = this.postRepository.getNumberOfPosts(postCategoryId, postStatus,
                postNameKeywords);
        if (nPostsObjectList.size() > 0) {
            return Integer.valueOf(nPostsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param postCategoryId   - post's category's id that user want to get posts
     *                         (this paramter could be optional)
     * @param postStatus       - post's status that user want to get posts (this
     *                         paramter could be optional)
     * @param postNameKeywords - post's name's keywords that user want to get posts
     *                         (this paramter could be optional)
     * @param topPostLimit     - number of posts that user want to get posts (this
     *                         parameter could be optional)
     * @return list of posts
     */
    public List<Post> getTopPosts(Integer postCategoryId, Integer postStatus, String postNameKeywords,
            Integer topPostLimit) {
        List<Object> topPostsObjectList = this.postRepository.getTopPosts(postCategoryId, postStatus, postNameKeywords,
                topPostLimit);
        return this.getPostsFromObjectList(topPostsObjectList);
    }

    /**
     * @param postsObjectList - posts object list that user want to convert to posts
     *                        list
     * @return list of posts
     */
    private List<Post> getPostsFromObjectList(List<Object> postsObjectList) {
        List<Post> posts = new ArrayList<>();
        for (Object o : postsObjectList) {
            Object[] postObjectArr = (Object[]) o;
            Post post = this.createPostFromObjectArray(postObjectArr);
            posts.add(post);
        }
        return posts;
    }

    /**
     * @param postObjectArr - post object array that user want to convert to post
     * @return converted post
     */
    private Post createPostFromObjectArray(Object[] postObjectArr) {
        int id = (int) postObjectArr[0];
        String postContent = (String) postObjectArr[1];
        String postDescription = (String) postObjectArr[2];
        String postMetaKeywords = (String) postObjectArr[3];
        String postTitle = (String) postObjectArr[4];
        String postImage = (String) postObjectArr[5];
        Date postCreatedDate = (Date) postObjectArr[6];
        Date postModifiedDate = (Date) postObjectArr[7];
        int postStatus = (int) postObjectArr[8];
        int postCategoryId = (int) postObjectArr[9];
        PostCategory postCategory = this.getPostCategory((long) postCategoryId);
        String postMetaTitle = (String) postObjectArr[10];
        String postMetaDescription = (String) postObjectArr[11];
        int userProfileId = (int) postObjectArr[12];
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        int postViewCount = (int) postObjectArr[13];
        int postTopHot = (int) postObjectArr[14];
        int postNew = (int) postObjectArr[15];

        return new Post((long) id, postContent, postDescription, postMetaKeywords, postTitle, postImage,
                postCreatedDate, postModifiedDate, postStatus, postCategory, postMetaTitle, postMetaDescription,
                userProfile, postViewCount, postTopHot, postNew);
    }

    /**
     * @param id - post's id that user want to get selected post
     * @return selected post
     */
    public Post getPost(Long id) {
        return this.postRepository.findPostsById(id);
    }

    /**
     * @param postCategoryId - post's category's id that user want to get selected
     *                       post's category
     * @return selected post's category
     */
    public PostCategory getPostCategory(Long postCategoryId) {
        return this.postCategoryService.getPostCategory(postCategoryId);
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    public UserProfile getUserProfile(Long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }
}
