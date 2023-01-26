package study.test.practice.web.weather.outter.dto.response;

import lombok.Data;

@Data
public class WeatherOpenApiResponseHeader {

    private String resultCode;
    private String resultMsg;

    private WeatherOpenApiResponseHeader() {
    }

    public WeatherOpenApiResponseHeader(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    @Override
    public String toString() {
        return """
                "resultCode": %s,
                "resultMsg": %s
                """.formatted(resultCode, resultMsg);
    }
}
