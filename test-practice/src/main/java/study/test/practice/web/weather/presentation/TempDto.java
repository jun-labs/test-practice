package study.test.practice.web.weather.presentation;

import java.util.List;

public class TempDto {

    private List<String> temp;

    public TempDto(List<String> temp) {
        this.temp = temp;
    }

    public List<String> getTemp() {
        return temp;
    }
}
