package study.test.practice.web.weather.outter.dto.response;

import java.util.List;

public class WeatherOpenApiResponseBodyItems {

    private List<WeatherOpenApiResponseBodyItem> item;

    private WeatherOpenApiResponseBodyItems() {
    }

    public WeatherOpenApiResponseBodyItems(List<WeatherOpenApiResponseBodyItem> item) {
        this.item = item;
    }

    public List<WeatherOpenApiResponseBodyItem> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
