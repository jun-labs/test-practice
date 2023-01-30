package study.test.practice.test.testdouble.mock.weather;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class WeatherMock {

    public static void setupMockBooksResponse(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/test"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("response.json")));
    }
}
