package study.test.practice.test.testdouble.mock.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.weather.outter.OpneApiComponent;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class OpenApiClientTest extends AbstractTestConfiguration {

    @Mock
    private OpneApiComponent<WeatherOpenApiResponse> weatherOpenApiComponent;

    @Test
    @DisplayName("하나의 외부 API를 테스트하기 위해서는 페이크를 통한 하드코딩으로 모킹을 할 수 있다.")
    void 페이크_모킹_테스트() {
        // given
        String date = "20230101";
        String hou = "0100";
        FakeWeatherOpenApiComponentMock fake = new FakeWeatherOpenApiComponentMock();
        WeatherOpenApiResponse response = fake.callBackData(date, hou);

        // when
        when(weatherOpenApiComponent.callBackData(any(), any())).thenReturn(response);
        WeatherOpenApiResponse result = weatherOpenApiComponent.callBackData(date, hou);

        // then
        Assertions.assertNotNull(result);
    }
}
