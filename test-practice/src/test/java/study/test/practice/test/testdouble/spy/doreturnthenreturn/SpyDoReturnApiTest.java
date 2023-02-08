package study.test.practice.test.testdouble.spy.doreturnthenreturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import study.test.practice.domain.user.entity.User;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.user.presentation.UserManagementClient;
import study.test.practice.web.user.presentation.UserOutterApiCommandController;
import study.test.practice.web.user.presentation.dto.request.UserSignupRequest;
import study.test.practice.web.user.presentation.dto.response.UserResponse;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("doReturn 외부 API 테스트")
class SpyDoReturnApiTest extends AbstractTestConfiguration {

    @SpyBean
    private UserManagementClient userManagementClient;

    @SpyBean
    private UserOutterApiCommandController userOutterApiCommandController;

    @Test
    @DisplayName("스파이를 통해 외부 API도 모킹할 수 있다.")
    void thenReturn3_실제_메서드_호출_테스트() {
        // given
        UserManagementClient spy = spy(userManagementClient);
        User outterApiResponse = new User("devjun10");
        UserSignupRequest request = new UserSignupRequest("devjun10");
        doReturn(outterApiResponse).when(spy).getUserInformation(request);

        // when, then
        Assertions.assertNotNull(spy.getUserInformation(request));
        verify(spy, times(1)).getUserInformation(request);
    }

    @Test
    @DisplayName("스파이는 중간에 어떤 과정이 있더라도 최종 결과를 반환한다.")
    void doReturn_최종_결과_반환_테스트() {
        // given
        UserOutterApiCommandController spy = spy(userOutterApiCommandController);
        UserSignupRequest request = new UserSignupRequest("devjun10");
        User expected = new User(1L, "devjun10");
        UserResponse response = new UserResponse(expected);
        doReturn(response).when(spy).save(request);

        spy.save(request);

        // when, then
        verify(spy, times(1)).save(request);
    }
}
