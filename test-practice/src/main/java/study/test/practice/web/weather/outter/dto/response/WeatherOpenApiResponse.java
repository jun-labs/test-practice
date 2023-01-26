package study.test.practice.web.weather.outter.dto.response;


import java.util.List;

public class WeatherOpenApiResponse {

    private WeatherOpenApiResponseDetail response;

    private WeatherOpenApiResponse() {
    }

    public WeatherOpenApiResponse(WeatherOpenApiResponseDetail response) {
        this.response = response;
    }

    public WeatherOpenApiResponseDetail getResponse() {
        return response;
    }

    public WeatherOpenApiResponseBody getWeatherApiResponseBody() {
        return response.getBody();
    }

    public WeatherOpenApiResponseBodyItems getWeatherApiResponseBodyItems() {
        return getWeatherApiResponseBody().getItems();
    }

    public List<WeatherOpenApiResponseBodyItem> getWeatherApiResponseBodyItemList() {
        return getWeatherApiResponseBodyItems().getItem();
    }

    @Override
    public String toString() {
        return """
                "response": %s
                """.formatted(response);
    }
}
