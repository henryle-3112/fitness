package henry.greenwich.fitness.controller.post;

import henry.greenwich.fitness.model.post.Tag;
import henry.greenwich.fitness.service.post.TagService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TagController {
    /**
     * tagService - interact with tag's data
     */
    private TagService tagService;

    /**
     * @param tagService - inject tag's service
     */
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    /**
     * @param status - tag's status
     * @return list of tags
     */
    @GetMapping(value = "/tags/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Tag> getTags(@PathVariable int status) {
        return this.tagService.getTags(status);
    }

    /**
     * @param id     - tag's id
     * @param status - tag's status
     * @return selected tag
     */
    @GetMapping(value = "/tags/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Tag getTag(@PathVariable Long id, @PathVariable int status) {
        return this.tagService.getTag(id, status);
    }


    /**
     * @param tag - tag that user want to create
     * @return inserted tag
     */
    @PostMapping(value = "/tags/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Tag addTag(@RequestBody Tag tag) {
        return this.tagService.addTag(tag);
    }

    /**
     * @param tag - tag that user want to update
     * @return updated tag
     */
    @PostMapping(value = "/tags/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Tag updateTag(@RequestBody Tag tag) {
        return this.tagService.updateTag(tag);
    }

    /**
     * @param id - tag's id
     */
    @PostMapping(value = "/tags/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteTag(@PathVariable Long id) {
        this.tagService.deleteTag(id);
    }

}
