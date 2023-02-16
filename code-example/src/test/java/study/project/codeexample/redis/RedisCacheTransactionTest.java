package study.project.codeexample.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ActiveProfiles;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.domain.user.infrastructure.UserJpaRepository;
import study.project.codeexample.web.user.application.UserQueryService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;

/**
 * 참조1: https://stackoverflow.com/questions/33008798/spring-session-with-redis-how-to-mock-it-in-integration-tests
 * 참조2: https://github.com/spring-projects/spring-data-redis/issues/951
 */
@SpringBootTest
@ActiveProfiles("test")
class RedisCacheTransactionTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @SpyBean
    private UserQueryService userQueryService;

    @SpyBean
    private RedisCacheManager redisCacheManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    void beforeEach() {
        redisCacheManager.initializeCaches();
    }

    @Test
    @DisplayName("세션콜백을 통해 트랜잭션이 끝나면 캐시 내부 값을 읽을 수 있다.")
    void 캐시매니저_트랜잭션_SessionCallback_테스트() {
        String userName = "devjun10";
        redisTemplate.execute(new SessionCallback<List<?>>() {
            public List<?> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                redisCacheManager.getCache("userCache").put(userName, userName);
                return operations.exec();
            }
        });
        Assertions.assertNotNull(redisCacheManager.getCache("userCache").get(userName));
    }

    @Test
    @DisplayName("레디스는 (기본적으로) 트랜잭션을 지원하지 않기 때문에 캐시매니저에 등록한 순간에는 값을 읽을 수 없다.")
    void 캐시매니저_트랜잭션_테스트() {
        // given
        Long memberId = 1L;
        String memberName = "hellow-world";
        userJpaRepository.save(new User(memberName));
        UserQueryService spy = spy(userQueryService);

        // when
        spy.findUserNameByIdAndName(memberId, memberName);
        spy.findUserNameByIdAndName(memberId, memberName);

        // then
        assertNull(redisCacheManager.getCache("userCache").get(memberName));
    }
}
