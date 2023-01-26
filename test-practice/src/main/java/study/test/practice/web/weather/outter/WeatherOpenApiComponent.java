package study.test.practice.web.weather.outter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import study.test.practice.web.weather.outter.dto.response.WeatherForecastOpenApiResponse;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponse;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class WeatherOpenApiComponent {

    private static final String FIXED_PAGE_SIZE = "10";
    private static final String PAGE_NO = "pageNo";
    private static final String NUMBER_OF_ROWS = "numOfRows";
    private static final String DATA_TYPE = "dataType";
    private static final String BASE_DATE = "base_date";
    private static final String BASE_TIME = "base_time";
    private static final String X_POSITION = "nx";
    private static final String Y_POSITION = "ny";

    private final WebClient webClient;

    public WeatherOpenApiResponse callBackData(String date, String hour) {
        WeatherOpenApiResponse response;
        try {
            response = webClient.get()
                    .uri(uriBuilder(date, hour))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(WeatherOpenApiResponse.class)
                    .block();

            if (Objects.isNull(response)) {
                throw new RuntimeException();
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private Function<UriBuilder, URI> uriBuilder(String date, String hour) {
//        20230116&base_time=1730&nx=60&ny=127
        return uriBuilder -> uriBuilder
                .queryParam(PAGE_NO, "1")
                .queryParam(NUMBER_OF_ROWS, FIXED_PAGE_SIZE)
                .queryParam(DATA_TYPE, "JSON")
                .queryParam(BASE_DATE, date)
                .queryParam(BASE_TIME, hour)
                .queryParam(X_POSITION, "60")
                .queryParam(Y_POSITION, "127")
                .build();
    }

    public WebClient getWebClient() {
        return webClient;
    }
}

