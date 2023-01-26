package study.test.practice.web.weather.outter.dto.response;

public class WeatherOpenApiResponseBodyItem {

    private String baseDate;
    private String baseTime;
    private String category;
    private String nx;
    private String ny;
    private String obsrValue;

    private WeatherOpenApiResponseBodyItem() {
    }

    public WeatherOpenApiResponseBodyItem(String baseDate,
                                          String baseTime,
                                          String category,
                                          String nx,
                                          String ny,
                                          String obsrValue) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.category = category;
        this.nx = nx;
        this.ny = ny;
        this.obsrValue = obsrValue;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public String getCategory() {
        return category;
    }

    public String getNx() {
        return nx;
    }

    public String getNy() {
        return ny;
    }

    public String getObsrValue() {
        return obsrValue;
    }

    @Override
    public String toString() {
        return """
                    "baseDate": %s,
                    "baseTime": %s,
                    "category": %s,
                    "nx": %s,
                    "ny": %s,
                    "obsrValue": %s
                """.formatted(
                baseDate,
                baseTime,
                category,
                nx,
                ny,
                obsrValue
        );
    }
}
