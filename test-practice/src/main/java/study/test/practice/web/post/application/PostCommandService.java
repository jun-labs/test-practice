package study.test.practice.web.post.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.test.practice.web.post.infrastructure.PostCommandRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostCommandRepository postCommandRepository;

    @Transactional
    public Long save(Long value) {
        log.info("Test");
        return value;
    }
}
