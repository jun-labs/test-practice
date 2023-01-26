package study.test.practice.web.weather.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.test.practice.domain.count.entity.WeatherCount;
import study.test.practice.domain.count.infrastructure.WeatherCountJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherCountService {

    private final WeatherCountJpaRepository weatherCountJpaRepository;

    @Transactional
    public void increase() {
        WeatherCount saved = weatherCountJpaRepository.save(new WeatherCount());
        WeatherCount weatherCount = weatherCountJpaRepository.findById(saved.getCountId()).get();
        weatherCount.increase();
        log.info("Count: {}", weatherCount.getCount());
    }
}
