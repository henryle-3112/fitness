package henry.greenwich.fitness.controller.upload;

import henry.greenwich.fitness.model.response.ResponseMessage;
import henry.greenwich.fitness.service.upload.UploadService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    /**
     * uploadImageService - upload image to the server
     */
    private UploadService uploadService;

    /**
     *
     * @param uploadService - inject uploadImageService
     */
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     *
     * @param file - file that would be uploaded to the server
     * @param rootLocation - the location that the file would be added
     * @return message that the file was uploaded successfully or not
     */
    @PostMapping(value = "/upload/{rootLocation}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseMessage uploadImage(@RequestBody MultipartFile file, @PathVariable String rootLocation) {
        try {
            String pathUploadedFileName = this.uploadService.store(file, rootLocation);
            return new ResponseMessage(pathUploadedFileName);
        } catch (Exception e) {
            return new ResponseMessage("failure");
        }
    }
}
