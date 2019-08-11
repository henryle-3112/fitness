package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.Post;
import henry.greenwich.fitness.model.post.PostCategory;
import henry.greenwich.fitness.service.post.PostService;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.service.post.PostCategoryService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Controller
public class PostController {
    /**
     * postService - inject postService
     * postCategoryService - inject postCategoryService
     * userProfileService - inject userProfileService
     */
    private PostService postService;
    private PostCategoryService postCategoryService;
    private UserProfileService userProfileService;

    /**
     * postService - inject postService
     * postCategoryService - inject postCategoryService
     * userProfileService - inject userProfileService
     */
    public PostController(PostService postService,
                          PostCategoryService postCategoryService,
                          UserProfileService userProfileService) {
        this.postService = postService;
        this.postCategoryService = postCategoryService;
        this.userProfileService = userProfileService;
    }

    /**
     * @return list of posts
     */
    @GetMapping(value = "/posts/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Post> getPosts(@PathVariable int status) {
        return this.postService.getPosts(status);
    }

    /**
     * @return list of posts
     */
    @GetMapping(value = "/posts/{top}/{categoryId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Post> getTopPosts(@PathVariable int top, @PathVariable int categoryId, @PathVariable int status) {
        List<Post> posts = new ArrayList<>();
        List<Object> topPosts = this.postService.getTopPosts(top, categoryId, status);
        for (Object eachTopPostObject : topPosts) {
            Object[] eachTopPost = (Object[]) eachTopPostObject;
            int id = (int) eachTopPost[0];
            String postContent = (String) eachTopPost[1];
            String postDescription = (String) eachTopPost[2];
            String postMetaKeywords = (String) eachTopPost[3];
            String postTitle = (String) eachTopPost[4];
            String postImage = (String) eachTopPost[5];
            Date postCreatedDate = (Date) eachTopPost[6];
            Date postModifiedDate = (Date) eachTopPost[7];
            int postStatus = (int) eachTopPost[8];
            int postCategoryId = (int) eachTopPost[9];
            PostCategory postCategory = this.postCategoryService.getPostCategory((long) postCategoryId);
            String postMetaTitle = (String) eachTopPost[10];
            String postMetaDescription = (String) eachTopPost[11];
            int userProfileId = (int) eachTopPost[12];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            int postViewCount = (int) eachTopPost[13];
            int postTopHot = (int) eachTopPost[14];
            int postNew = (int) eachTopPost[15];

            Post post = new Post(
                    (long) id,
                    postContent,
                    postDescription,
                    postMetaKeywords,
                    postTitle,
                    postImage,
                    postCreatedDate,
                    postModifiedDate,
                    postStatus,
                    postCategory,
                    postMetaTitle,
                    postMetaDescription,
                    userProfile,
                    postViewCount,
                    postTopHot,
                    postNew
            );
            posts.add(post);
        }
        return posts;
    }

    /**
     * @param id - post's id
     * @return selected post
     */
    @GetMapping(value = "/posts/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Post getPost(@PathVariable Long id, @PathVariable int status) {
        return this.postService.getPost(id, status);
    }

    /**
     * @param post - that user want to add to the database
     * @return post - that was inserted to the database
     */
    @PostMapping(value = "/posts/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Post addPost(@RequestBody Post post) {
        return this.postService.addPost(post);
    }

    /**
     * @param post - that user want to update to the database
     * @return post - that was updated to the database
     */
    @PostMapping(value = "/posts/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Post updatePost(@RequestBody Post post) {
        return this.postService.updatePost(post);
    }

    /**
     * @param id - post's id that user want to delete
     */
    @PostMapping(value = "/posts/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletePost(@PathVariable Long id) {
        this.postService.deletePost(id);
    }

    /**
     * @param categoryId - category's id that posts belong to
     * @param page       - current page
     * @return list of posts
     */
    @GetMapping(value = "/posts/paging/{categoryId}/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Post> getPostsByCategoryAndByPage(@PathVariable int categoryId, @PathVariable int page, @PathVariable int status) {
        int startIndex = ((page - 1) * 8) + 1;
        List<Post> posts = new ArrayList<>();
        List<Object> topPosts = this.postService.findPostsByCategoryAndByPage(categoryId, startIndex - 1, status);
        for (Object eachTopPostObject : topPosts) {
            Object[] eachTopPost = (Object[]) eachTopPostObject;
            int id = (int) eachTopPost[0];
            String postContent = (String) eachTopPost[1];
            String postDescription = (String) eachTopPost[2];
            String postMetaKeywords = (String) eachTopPost[3];
            String postTitle = (String) eachTopPost[4];
            String postImage = (String) eachTopPost[5];
            Date postCreatedDate = (Date) eachTopPost[6];
            Date postModifiedDate = (Date) eachTopPost[7];
            int postStatus = (int) eachTopPost[8];
            int postCategoryId = (int) eachTopPost[9];
            PostCategory postCategory = this.postCategoryService.getPostCategory((long) postCategoryId);
            String postMetaTitle = (String) eachTopPost[10];
            String postMetaDescription = (String) eachTopPost[11];
            int userProfileId = (int) eachTopPost[12];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            int postViewCount = (int) eachTopPost[13];
            int postTopHot = (int) eachTopPost[14];
            int postNew = (int) eachTopPost[15];

            Post post = new Post(
                    (long) id,
                    postContent,
                    postDescription,
                    postMetaKeywords,
                    postTitle,
                    postImage,
                    postCreatedDate,
                    postModifiedDate,
                    postStatus,
                    postCategory,
                    postMetaTitle,
                    postMetaDescription,
                    userProfile,
                    postViewCount,
                    postTopHot,
                    postNew
            );
            posts.add(post);
        }
        return posts;
    }

    /**
     * @param categoryId - category's id that posts belong to
     * @return number of posts
     */
    @GetMapping(value = "/posts/count/{categoryId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countPosts(@PathVariable int categoryId, @PathVariable int status) {
        List<Object> countPosts = this.postService.countPostsByCategoryAndByPostStatus(categoryId, status);
        Object eachCountPost = countPosts.get(0);
        return new ResponseMessage(eachCountPost.toString());
    }


    /**
     * @param categoryId       - category's id that posts belong to
     * @param postNameKeywords - post's name keywords
     * @return number of posts
     */
    @GetMapping(value = "/posts/searching/count/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countSearchingPosts(@RequestParam Optional<String> categoryId,
                                               @RequestParam Optional<String> postNameKeywords,
                                               @PathVariable int status) {
        Integer selectedCategoryId = null;
        String selectedPostNameKeywords = "%%";

        // get selected category's id
        String paramCategoryId = categoryId.orElse(null);
        if (paramCategoryId != null && !paramCategoryId.equals("") && paramCategoryId.matches("[0-9]+")) {
            selectedCategoryId = Integer.valueOf(paramCategoryId);
        }

        // get selected post's name keywords
        String paramPostNameKeywords = postNameKeywords.orElse(null);
        if (paramPostNameKeywords != null && !paramPostNameKeywords.equals("")) {
            selectedPostNameKeywords = "%" + paramPostNameKeywords + "%";
        }
        List<Object> countPostsObject = this.postService.countSearchingPosts(
                selectedCategoryId,
                selectedPostNameKeywords,
                status);
        Object eachCountMusic = countPostsObject.get(0);
        return new ResponseMessage(eachCountMusic.toString());
    }

    /**
     * @param categoryId - category's id that posts belong to
     * @param page       - current page
     * @return list of posts
     */
    @GetMapping(value = "/posts/searching/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Post> getSearchingPostsByPage(@PathVariable int page,
                                              @RequestParam Optional<String> categoryId,
                                              @RequestParam Optional<String> postNameKeywords,
                                              @PathVariable int status) {
        Integer selectedCategoryId = null;
        String selectedPostNameKeywords = "%%";

        // get selected category's id
        String paramCategoryId = categoryId.orElse(null);
        if (paramCategoryId != null && !paramCategoryId.equals("") && paramCategoryId.matches("[0-9]+")) {
            selectedCategoryId = Integer.valueOf(paramCategoryId);
        }

        // get selected post's name keywords
        String paramPostNameKeywords = postNameKeywords.orElse(null);
        if (paramPostNameKeywords != null && !paramPostNameKeywords.equals("")) {
            selectedPostNameKeywords = "%" + paramPostNameKeywords + "%";
        }

        int startIndex = ((page - 1) * 8) + 1;
        List<Post> posts = new ArrayList<>();
        List<Object> topPosts = this.postService.findSearchingPostsByPage(selectedCategoryId, selectedPostNameKeywords, startIndex - 1, status);
        for (Object eachTopPostObject : topPosts) {
            Object[] eachTopPost = (Object[]) eachTopPostObject;
            int id = (int) eachTopPost[0];
            String postContent = (String) eachTopPost[1];
            String postDescription = (String) eachTopPost[2];
            String postMetaKeywords = (String) eachTopPost[3];
            String postTitle = (String) eachTopPost[4];
            String postImage = (String) eachTopPost[5];
            Date postCreatedDate = (Date) eachTopPost[6];
            Date postModifiedDate = (Date) eachTopPost[7];
            int postStatus = (int) eachTopPost[8];
            int postCategoryId = (int) eachTopPost[9];
            PostCategory postCategory = this.postCategoryService.getPostCategory((long) postCategoryId);
            String postMetaTitle = (String) eachTopPost[10];
            String postMetaDescription = (String) eachTopPost[11];
            int userProfileId = (int) eachTopPost[12];
            UserProfile userProfile = this.userProfileService.getUserProfile((long) userProfileId);
            int postViewCount = (int) eachTopPost[13];
            int postTopHot = (int) eachTopPost[14];
            int postNew = (int) eachTopPost[15];

            Post post = new Post(
                    (long) id,
                    postContent,
                    postDescription,
                    postMetaKeywords,
                    postTitle,
                    postImage,
                    postCreatedDate,
                    postModifiedDate,
                    postStatus,
                    postCategory,
                    postMetaTitle,
                    postMetaDescription,
                    userProfile,
                    postViewCount,
                    postTopHot,
                    postNew
            );
            posts.add(post);
        }
        return posts;
    }
}
