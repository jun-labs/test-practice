package study.test.practice.test.testdouble.mock.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.restassured3.RestAssuredOperationPreprocessorsConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import study.test.practice.test.configuration.DatabaseConfiguration;

import static io.restassured.RestAssured.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0, stubs = "classpath:/")
@TestPropertySource(properties = {
        "app.gateway-uri=http://localhost:${wiremock.server.port}"
})
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherOutterAPITest {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @LocalServerPort
    protected int port;

    @Rule
    public static WireMockRule wiremock = new WireMockRule(0);

    @BeforeAll
    static void seup() {
    }


    protected RequestSpecification specification;

    @Autowired
    protected ObjectMapper objectMapper;

    public WeatherOutterAPITest() {
        initRestAssureConfiguration();
        seup();
    }

    private void initRestAssureConfiguration() {
        objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 모든 정보 출력
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        RestAssured.config = new RestAssuredConfig().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory((clazz, charset) -> objectMapper)
        );
    }

    @BeforeEach
    void beforeEach(RestDocumentationContextProvider restDocumentation) throws JsonProcessingException {
        RestAssured.port = port;

        OperationPreprocessor operationPreprocessor = modifyUris()
                .host("222.123.12.4")
                .removePort();

        RestAssuredOperationPreprocessorsConfigurer restDocumentationFilter = documentationConfiguration(restDocumentation)
                .operationPreprocessors()
                .withRequestDefaults(operationPreprocessor, prettyPrint())
                .withResponseDefaults(prettyPrint());
        this.specification = new RequestSpecBuilder()
                .setPort(port)
                .addFilter(restDocumentationFilter)
                .build();


        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        databaseConfiguration.truncateAllEntity();

        wiremock.start();
    }

    @AfterEach
    void after() {
        wiremock.shutdown();
    }

    @Test
    @DisplayName("TODO")
    void 외부_API_테스트() {
        given(this.specification)
                .contentType(APPLICATION_JSON_VALUE)
                .get("/api/weather")

                .then()
                .statusCode(200)
                .log().all();
    }

}
