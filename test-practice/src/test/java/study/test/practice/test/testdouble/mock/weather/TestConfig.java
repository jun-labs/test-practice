package study.test.practice.test.testdouble.mock.weather;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import study.test.practice.web.weather.outter.OpneApiComponent;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public OpneApiComponent externalClient() {
        return new FakeWeatherOpenApiComponentMock();
    }
}
