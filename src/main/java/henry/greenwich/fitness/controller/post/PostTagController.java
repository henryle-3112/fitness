package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.*;
import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.post.PostService;
import henry.greenwich.fitness.service.post.PostTagService;
import henry.greenwich.fitness.service.post.TagService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostTagController {
    /**
     * postTagService - interact with post's tag data
     * tagService - interact with tag's data
     * postService - interact with post's data
     */
    private PostTagService postTagService;
    private TagService tagService;
    private PostService postService;

    /**
     * @param postTagService - inject postTagService
     * @param tagService - inject postTagService
     * @param postService - inject postTagService
     */
    public PostTagController(PostTagService postTagService,
                             TagService tagService,
                             PostService postService) {
        this.postTagService = postTagService;
        this.tagService = tagService;
        this.postService = postService;
    }

    /**
     * @return list of post's tags
     */
    @GetMapping(value = "/post/tags", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostTag> getPostTags() {
        List<PostTag> postTagResults = new ArrayList<>();
        List<PostTag> postTags = this.postTagService.getPostTags();
        for (PostTag eachPostTag : postTags) {
            if (eachPostTag.tag.getTagStatus() == 1) {
                postTagResults.add(eachPostTag);
            }
        }
        return postTagResults;
    }

    /**
     * @param id - post's tag's id
     * @return selected post's tag
     */
    @GetMapping(value = "/post/tags/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostTag getPostTag(@PathVariable Long id) {
        PostTag selectedPostTag = this.postTagService.getPostTag(id);
        if (selectedPostTag != null && selectedPostTag.tag.getTagStatus() == 1) {
            return selectedPostTag;
        }
        return null;
    }


    /**
     * @param postTag - post's tag that user want to add
     * @return inserted post's tag
     */
    @PostMapping(value = "/post/tags/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostTag addPostTag(@RequestBody PostTag postTag) {
        return this.postTagService.addPostTag(postTag);
    }

    /**
     * @param postTag - post's tag that user want to add
     * @return updated post's tag
     */
    @PostMapping(value = "/post/tags/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public PostTag updateTag(@RequestBody PostTag postTag) {
        return this.postTagService.updatePostTag(postTag);
    }

    /**
     * @param id - post's tag's id that user want to delete
     */
    @PostMapping(value = "/post/tags/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deletePostTag(@PathVariable Long id) {
        this.postTagService.deletePostTag(id);
    }

    /**
     *
     * @param tagId - tag's id
     * @param page - current's page
     * @param status - post's status
     * @return list of post tag
     */
    @GetMapping(value = "/post/tags/paging/{tagId}/{page}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostTag> getPostTagsByTag(@PathVariable int tagId,
                                          @PathVariable int page,
                                          @PathVariable int status) {
        int startIndex = ((page - 1) * 8) + 1;
        List<PostTag> postTags = new ArrayList<>();
        List<Object> postTagObjectList = this.postTagService.findPostTagsByTagIdAndPostStatus(tagId, startIndex - 1, status);
        for (Object eachPostTagObject : postTagObjectList) {
            Object[] eachPostTagArrayObject = (Object[]) eachPostTagObject;
            int selectedPostTagId = (int) eachPostTagArrayObject[0];
            int selectedTagId = (int) eachPostTagArrayObject[1];
            int selectedPostId = (int) eachPostTagArrayObject[2];
            Tag selectedTag = this.tagService.getTag((long) selectedTagId, 1);
            Post selectedPost = this.postService.getPost((long) selectedPostId, 1);
            PostTag postTag = new PostTag(
                    (long) selectedPostTagId,
                    selectedTag,
                    selectedPost
            );
            postTags.add(postTag);
        }
        return postTags;
    }


    @GetMapping(value = "/post/tags/count/{tagId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage countPosts(@PathVariable int tagId, @PathVariable int status) {
        List<Object> countPostTags = this.postTagService.countPostTagsByTagIdAndPostStatus(tagId, status);
        Object eachCountPostTag = countPostTags.get(0);
        return new ResponseMessage(eachCountPostTag.toString());
    }


    @PostMapping(value = "/post/tags/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PostTag> getPostTagsByPost(@RequestBody Post post) {
        List<PostTag> results = new ArrayList<>();
        List<PostTag> postTags = this.postTagService.getPostTagsByPost(post);
        for (PostTag eachPostTag : postTags) {
            if (eachPostTag.tag.getTagStatus() == 1) {
                results.add(eachPostTag);
            }
        }
        return results;
    }
}
