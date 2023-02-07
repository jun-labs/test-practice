package study.test.practice.test.testdouble.mock.weather.mockserver;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.MediaType;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class WireMockResponse {

    public static void setStub() throws IOException {
        stubFor(WireMock.get(urlEqualTo("/api/oauth/login"))
                        .willReturn(aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("response-login.json")
                        )
        );
    }
}
