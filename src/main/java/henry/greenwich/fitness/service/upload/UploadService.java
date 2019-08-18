package henry.greenwich.fitness.service.upload;

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
     * @param file     - file that user want to upload
     * @param rootPath - location that file would be stored
     * @return the link that user could be used to accessed the file
     */
    public String store(MultipartFile file, String rootPath) {
        try {
            String savedFileName = "";
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
            Date currentDate = Calendar.getInstance().getTime();
            Path rootLocation = Paths.get(Constants.PATH_PUBLIC_IMAGE_STRING + rootPath);
            String uploadedDate = sdfDate.format(currentDate);
            String currentFileName = file.getOriginalFilename();
            if (currentFileName != null && currentFileName.contains(".jpg")) {
                savedFileName = currentFileName.substring(0, currentFileName.indexOf(".jpg")) + "_" + uploadedDate
                        + ".jpg";
            } else if (currentFileName != null && currentFileName.contains(".mp3")) {
                savedFileName = currentFileName.substring(0, currentFileName.indexOf(".mp3")) + "_" + uploadedDate
                        + ".mp3";
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(savedFileName));
            return "http://localhost:8080/greenwich-fitness/api/resources/upload/" + rootPath + "/" + savedFileName;
        } catch (Exception e) {
            throw new RuntimeException("failure");
        }
    }
}
