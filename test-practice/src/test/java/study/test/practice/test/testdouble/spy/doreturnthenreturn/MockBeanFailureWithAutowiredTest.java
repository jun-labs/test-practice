package study.test.practice.test.testdouble.spy.doreturnthenreturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import study.test.practice.domain.user.entity.User;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.user.application.UserCommandService;
import study.test.practice.web.user.presentation.UserCommandController;
import study.test.practice.web.user.presentation.dto.request.UserSignupRequest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@DisplayName("@SpyBean과 @Autowired을 함께 사용할때 doReturn 테스트")
class MockBeanFailureWithAutowiredTest extends AbstractTestConfiguration {

    @SpyBean
    private UserCommandService userCommandService;

    @Autowired
    private UserCommandController userCommandController;

    @Test
    @DisplayName("doReturn은 실제 메서드를 호출하지 않기 때문에 @Autowired와 함께 사용하면 NullPointerException이 발생한다.")
    void doReturn_실제_메서드_호출_테스트() {
        // given
        User expected = new User(1L, "devjun10");
        UserSignupRequest request = new UserSignupRequest("devjun10");
        when(userCommandService.save(new User("devjun10"))).thenReturn(expected);
        doReturn(expected).when(userCommandService).save(new User("devjun10"));

        // when, then
        assertThatThrownBy(() -> userCommandController.save(request))
                .isInstanceOf(NullPointerException.class);
    }
}
