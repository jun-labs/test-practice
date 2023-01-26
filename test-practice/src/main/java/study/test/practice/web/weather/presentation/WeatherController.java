package study.test.practice.web.weather.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.web.weather.application.WeatherCountService;
import study.test.practice.web.weather.outter.WeatherOpenApiComponent;
import study.test.practice.web.weather.outter.dto.response.WeatherOpenApiResponse;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherOpenApiComponent weatherOpenApiComponent;
    private final WeatherCountService weatherCountService;

    @GetMapping("/api/weather")
    public ResponseEntity<WeatherOpenApiResponse> getResponse(@RequestParam("date") String date,
                                                              @RequestParam("hour") String hour) {
        WeatherOpenApiResponse response = weatherOpenApiComponent.callBackData(date, hour);
        weatherCountService.increase();
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/outter")
    public WeatherOpenApiResponse get(@RequestParam("date") String date,
                                      @RequestParam("hour") String hour) {
        return weatherOpenApiComponent.callBackData(date, hour);
    }
}
