package study.project.codeexample.localstack;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.project.codeexample.etc.AbstractTestConfiguration;

import java.io.File;

import static io.restassured.RestAssured.given;

@Slf4j
@DisplayName("파일 업로드 인수 테스트")
class S3ImageUploadAcceptanceTest extends AbstractTestConfiguration {

    @Test
    @DisplayName("올바른 이미지를 입력하면 S3에 이미지가 업로드 된다.")
    void 컨테이너_인수_테스트() {
        String filePath = "./src/main/resources/js/cookie.js";

        // https://stackoverflow.com/questions/48262096/how-to-send-form-data-in-api-using-rest-assured
        given(this.specification)
                .when()
                .header("Content-Type", "multipart/json")
                .accept("*/*")
                .multiPart("file", new File(filePath))
                .post("/api/v1/upload")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
