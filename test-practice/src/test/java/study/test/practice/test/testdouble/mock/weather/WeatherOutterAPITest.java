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
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.restassured3.RestAssuredOperationPreprocessorsConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import study.test.practice.test.configuration.DatabaseConfiguration;

import java.io.InputStreamReader;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static io.restassured.RestAssured.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherOutterAPITest {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @LocalServerPort
    protected int port;

    @Rule
    public WireMockRule wiremock = new WireMockRule(9001);

    protected RequestSpecification specification;

    @Autowired
    protected ObjectMapper objectMapper;

    public WeatherOutterAPITest() {
        initRestAssureConfiguration();
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
    }

    @AfterEach
    void after() {
        wiremock.stop();
    }

    @Test
    @DisplayName("???")
    void m3() throws Exception {
        wiremock.stubFor(
                get(urlEqualTo("/temp2"))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBody("test.json")));

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .get("/temp2")
                .then()
                .statusCode(201);

    }

    @Test
    @DisplayName("???")
    void m() throws Exception {
        wiremock.stubFor(
                get(urlEqualTo("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=qIJIseyvqJg2M4a6oS0GjBZZ5m3oiHWsckheQSV22aNeWV%2FIV0Cs9OUmVvf%2Fg8WQyPLsXEho3H%2Bgvh2XBT40KQ%3D%3D&numOfRows%3D10&pageNo%3D1&dataType=JSON&base_date=20230116&base_time=1730&nx=60&ny=127"))
                        .willReturn(ok()
                                .withHeader("Content-Type", "application/json")
                                .withBody("response.json")));
//        base_date=20230116&base_time=1730&nx=60&ny=127
        wiremock.start();

        given(this.specification)
                .contentType(APPLICATION_JSON_VALUE)
                .queryParam("date", "20230116")
                .queryParam("hour", "1730")
                .get("/api/outter")

                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    @DisplayName("???")
    void m2() {
        wiremock.stubFor(
                get(urlMatching("/api/outter"))
                        .willReturn(ok()
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("response.json"))
        );

        given(this.specification)
                .contentType(APPLICATION_JSON_VALUE)
                .queryParam("date", "20230116")
                .queryParam("hour", "1730")
                .get("/api/outter")

                .then()
                .statusCode(200)
                .log().all();

    }

}
