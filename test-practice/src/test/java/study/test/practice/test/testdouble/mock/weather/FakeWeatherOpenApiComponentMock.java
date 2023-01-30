package study.test.practice.test.testdouble.mock.weather;

import org.springframework.stereotype.Component;
import study.test.practice.web.weather.outter.OpneApiComponent;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponse;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponseBody;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponseBodyItem;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponseBodyItems;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponseDetail;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponseHeader;

import java.util.List;

@Component
public class FakeWeatherOpenApiComponentMock implements OpneApiComponent<WeatherOpenApiResponse> {

    @Override
    public WeatherOpenApiResponse callBackData(String date, String hour) {
        return getResponse();
    }

    public static WeatherOpenApiResponse getResponse() {
        return
                new WeatherOpenApiResponse(
                        new WeatherOpenApiResponseDetail(
                                new WeatherOpenApiResponseHeader(
                                        "200",
                                        "SUCCESS"
                                ),
                                new WeatherOpenApiResponseBody(
                                        "JSON",
                                        new WeatherOpenApiResponseBodyItems(
                                                List.of(
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "PTY",
                                                                "60",
                                                                "127",
                                                                "0"
                                                        ),
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "REH",
                                                                "60",
                                                                "127",
                                                                "34"
                                                        ),
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "RN1",
                                                                "60",
                                                                "127",
                                                                "0"
                                                        ),
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "T1H",
                                                                "60",
                                                                "127",
                                                                "-0.8"
                                                        ),
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "UUU",
                                                                "60",
                                                                "127",
                                                                "1.7"
                                                        ),
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "VEC",
                                                                "60",
                                                                "127",
                                                                "-0.7"
                                                        ),
                                                        new WeatherOpenApiResponseBodyItem(
                                                                "20230118",
                                                                "1700",
                                                                "WSD",
                                                                "60",
                                                                "127",
                                                                "1.9"
                                                        )
                                                )
                                        ),
                                        1,
                                        10,
                                        10
                                )
                        )
                );
    }
}
