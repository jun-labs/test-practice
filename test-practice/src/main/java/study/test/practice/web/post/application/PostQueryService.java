package study.test.practice.web.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.test.practice.web.cache.PostPKCache;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostPKCache postPKCache;

    public Long findPostPKById(Long pk) {
        if (Objects.isNull(postPKCache.getPK(pk))) {
            postPKCache.put(pk);
        }
        return postPKCache.getPK(pk);
    }

    public Long getFixedValue() {
        return 1L;
    }
}
