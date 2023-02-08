package study.test.practice.web.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class PostPKCache {

    private final Map<Long, Long> factory = new ConcurrentHashMap<>();

    public void put(Long pk) {
        factory.put(pk, pk);
    }

    public Long getPK(Long pk) {
        log.info("PK: {}", pk);
        return factory.get(pk);
    }
}
