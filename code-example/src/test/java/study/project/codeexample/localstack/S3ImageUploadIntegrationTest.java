package study.project.codeexample.localstack;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import study.project.codeexample.web.image.application.FileUploadService;

import java.nio.charset.StandardCharsets;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class S3ImageUploadIntegrationTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    @DisplayName("컨테이너구동 테스트")
    void 컨테이너_구동_테스트() {
        String data = "helloworld";
        MultipartFile multipartFile = new MockMultipartFile(
                "files",
                "hello-world.png",
                "png",
                data.getBytes(StandardCharsets.UTF_8)
        );
        log.info("Test Success");
        fileUploadService.uploadImage(multipartFile);
    }
}
