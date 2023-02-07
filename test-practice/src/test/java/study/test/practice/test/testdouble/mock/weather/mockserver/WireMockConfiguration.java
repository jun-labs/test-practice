package study.test.practice.test.testdouble.mock.weather.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;

// @TestConfiguration
public class WireMockConfiguration extends AbstractTestConfiguration {

    @Primary
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockBooksService() {
        return new WireMockServer(port);
    }

}
