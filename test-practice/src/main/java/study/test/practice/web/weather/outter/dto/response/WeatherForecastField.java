package study.test.practice.web.weather.outter.dto.response;

import study.test.practice.web.weather.presentation.Convertor;

import java.util.Objects;

public class WeatherForecastField {

    private static final String RAINY_TYPE = "rainyType";

    private String category;
    private String value;

    public WeatherForecastField(WeatherOpenApiResponseBodyItem item) {
        this.category = Convertor.getValue(item.getCategory());
        this.value = getValue(item);
    }

    private String getValue(WeatherOpenApiResponseBodyItem item) {
        if (this.category.equals(RAINY_TYPE)) {
            return Convertor.getConvertTo(item.getObsrValue());
        }
        return item.getObsrValue();
    }

    public String getCategory() {
        return category;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherForecastField)) return false;
        WeatherForecastField field = (WeatherForecastField) o;
        return getCategory().equals(field.getCategory()) && getValue().equals(field.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getValue());
    }

    @Override
    public String toString() {
        return """
                    "category": %s,
                    "value": %s
                """.formatted(category, value);
    }
}
