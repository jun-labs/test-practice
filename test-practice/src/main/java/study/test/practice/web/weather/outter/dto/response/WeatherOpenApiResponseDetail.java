package study.test.practice.web.weather.outter.dto.response;


public class WeatherOpenApiResponseDetail {


    private WeatherOpenApiResponseHeader header;

    private WeatherOpenApiResponseBody body;

    private WeatherOpenApiResponseDetail() {
    }

    public WeatherOpenApiResponseDetail(WeatherOpenApiResponseHeader header, WeatherOpenApiResponseBody body) {
        this.header = header;
        this.body = body;
    }

    public WeatherOpenApiResponseHeader getHeader() {
        return header;
    }

    public WeatherOpenApiResponseBody getBody() {
        return body;
    }

    public WeatherOpenApiResponseBodyItems getWeatherApiResponseBodyItems() {
        return getBody().getItems();
    }

    @Override
    public String toString() {
        return """
                "header": %s, 
                "body": %s
                """.formatted(header, body);
    }
}
