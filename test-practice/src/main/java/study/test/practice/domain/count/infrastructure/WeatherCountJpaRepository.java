package study.test.practice.domain.count.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import study.test.practice.domain.count.entity.WeatherCount;

public interface WeatherCountJpaRepository extends JpaRepository<WeatherCount, Long> {
}
