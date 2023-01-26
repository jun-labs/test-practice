package study.test.practice.web.weather.outter.dto.response;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WeatherForecastOpenApiResponse {

    private final String date;
    private final String time;
    private final List<WeatherForecastField> fields;

    public WeatherForecastOpenApiResponse(WeatherOpenApiResponse response) {
        List<WeatherOpenApiResponseBodyItem> data = response.getWeatherApiResponseBodyItemList();
        WeatherOpenApiResponseBodyItem item = Objects.requireNonNull(data.get(0));

        this.date = item.getBaseDate();
        this.time = item.getBaseTime();
        this.fields = response.getWeatherApiResponseBodyItemList().stream()
                .filter(isNonNullCategory())
                .map(WeatherForecastField::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private Predicate<WeatherOpenApiResponseBodyItem> isNonNullCategory() {
        return field -> field.getCategory() != null;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<WeatherForecastField> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return """
                    "date": %s, 
                    "time": %s,
                    "fields": %s
                """.formatted(date, time, fields);
    }
}
