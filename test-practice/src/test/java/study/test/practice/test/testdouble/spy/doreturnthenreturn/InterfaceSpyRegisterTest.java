package study.test.practice.test.testdouble.spy.doreturnthenreturn;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import study.test.practice.domain.user.entity.User;
import study.test.practice.domain.user.infrastructure.UserJpaRepository;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.user.application.UserService;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Slf4j
@DisplayName("인터페이스 스파이 등록 테스트")
class InterfaceSpyRegisterTest extends AbstractTestConfiguration {

    @SpyBean
    private UserService userService;

    @SpyBean
    private UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("JPA 레포지토리를 스파이로 등록할 수 있으며 정상동작한다.")
    void JPA_레포지토리_스파이_등록_테스트() {
        // given
        UserJpaRepository spy = spy(userJpaRepository);
        User newUser = new User("devjun10");
        User expected = new User(1L, "devjun10");
        doReturn(expected).when(spy).save(newUser);

        // when
        User response = spy.save(newUser);

        Assertions.assertNotNull(response.getUserId());
        log.info("저장된 사용자: {}", response);
    }

    @Test
    @DisplayName("일반 인터페이스는 스파이로 등록할 수 있으며 정상동작한다.")
    void 인터페이스_스파이_상호작용_테스트() {
        // given
        UserService spy = spy(userService);
        User newUser = new User("devjun10");
        User expected = new User(1L, "devjun10");
        doReturn(expected).when(spy).save(newUser);

        // when
        User response = spy.save(newUser);

        // then
        Assertions.assertNotNull(response.getUserId());
        log.info("저장된 사용자: {}", response);
    }
}
