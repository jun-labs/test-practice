package study.test.practice.test.testdouble.mock.weather;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.weather.outter.WeatherOpenApiComponent;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

@Slf4j
@ActiveProfiles("test")
@Import(WireMockConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenApiClientMockingTest extends AbstractTestConfiguration {

    @Rule
    private static final WireMockServer wireMock = new WireMockServer(WireMockSpring.options().port(8090));  // upstream 서버의 port 지정

    @Autowired
    private WeatherOpenApiComponent weatherOpenApiComponent;

    @BeforeEach
    void setUp() {
        configureFor("localhost", 8090);
        wireMock.start();
    }

    @AfterAll
    static void afterAll() {
        wireMock.shutdown();
    }

    @Test
    @DisplayName("TODO")
    void WIRE_MOCK_테스트() {
        wireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("http://localhost:8090"))
                        .withQueryParam("pageNo", WireMock.equalTo("1"))
                        .withQueryParam("numOfRows", WireMock.equalTo("10"))
                        .withQueryParam("dataType", WireMock.equalTo("JSON"))
                        .withQueryParam("base_date", WireMock.equalTo("20230101"))
                        .withQueryParam("base_time", WireMock.equalTo("0100"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("response.json")
                        )
        );

        // given
        WeatherOpenApiResponse response = weatherOpenApiComponent.callBackData("20230101", "0100");
        log.info("Response Result: {}", response);
//
        Assertions.assertNotNull(response);
        wireMock.shutdownServer();
    }
}
