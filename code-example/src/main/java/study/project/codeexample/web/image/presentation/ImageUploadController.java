package study.project.codeexample.web.image.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import study.project.codeexample.web.image.application.FileUploadService;

@RestController
@RequiredArgsConstructor
public class ImageUploadController {


    private final FileUploadService fileUploadService;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart("file") MultipartFile file) {
        return fileUploadService.uploadImage(file);
    }

    @DeleteMapping("/api/v1/delete")
    public String deleteImage(@RequestParam("fileName") String fileName) {
        return fileUploadService.deleteImage(fileName);
    }
}
