package study.test.practice.web.weather.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/temp")
    public List<String> get() {
        List<String> list = get2().getTemp();
        list.add("A");
        System.out.println(list);
        return list;
    }

    @GetMapping("/temp2")
    public TempDto get2() {
        List<String> list = new LinkedList<>();
        return new TempDto(list);
    }
}
