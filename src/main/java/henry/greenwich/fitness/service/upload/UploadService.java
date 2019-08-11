package henry.greenwich.fitness.service.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import henry.greenwich.fitness.constants.Constants;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class UploadService {
    /**
     * rootLocation - location that uploaded file would be stored
     */
    private Path rootLocation;

    /**
     * @param file     - file that user want to upload
     * @param rootPath - location that file would be stored
     * @return the link that user could be used to accessed the file
     */
    public String store(MultipartFile file, String rootPath) {
        try {
            // saved file name
            String savedFileName = "";
            // simple date format to get current date as yyyy_mm_dd
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
            // get current date
            Date currentDate = Calendar.getInstance().getTime();
            // get root location to upload the image
            rootLocation = Paths.get(Constants.PATH_PUBLIC_IMAGE_STRING + rootPath);
            // create upload date
            String uploadedDate = sdfDate.format(currentDate);
            // get current file name
            String currentFileName = file.getOriginalFilename();
            if (currentFileName != null && currentFileName.contains(".jpg")) {
                // change file's name to file_name_uploadedDate.jpg
                savedFileName = currentFileName.substring(0, currentFileName.indexOf(".jpg")) + "_" + uploadedDate + ".jpg";
            } else if (currentFileName != null && currentFileName.contains(".mp3")) {
                // change file's name to file_name_uploadedDate.mp3
                savedFileName = currentFileName.substring(0, currentFileName.indexOf(".mp3")) + "_" + uploadedDate + ".mp3";
            }
            // upload file
            Files.copy(file.getInputStream(), this.rootLocation.resolve(savedFileName));
            // return path of upload image
            return "http://localhost:8080/resources/upload/" + rootPath + "/" + savedFileName;
        } catch (Exception e) {
            throw new RuntimeException("failure");
        }
    }
}
