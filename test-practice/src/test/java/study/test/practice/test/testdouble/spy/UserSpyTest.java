package study.test.practice.test.testdouble.spy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.mockito.junit.MockitoJUnitRunner;
import study.test.practice.domain.user.entity.User;
import study.test.practice.domain.user.infrastructure.UserJpaRepository;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.user.application.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceSpyAbstractTest extends AbstractTestConfiguration {

    @Spy
    public UserJpaRepository userJpaRepository;

    @InjectMocks
    public UserServiceImpl userService;

    @Test
    @DisplayName("Spy를 사용하면 실제 레포지토리를 호출한다.")
    void Spy_Calls_Real_Method_V1() {
        // given
        User newUser = new User("devjun");
        when(userService.save(newUser)).thenReturn(new User(1L, "devjun"));

        // when
        userService.save(newUser);

        // then
        verify(userJpaRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Spy를 사용하더라도 잘못된 값을 넣으면 오류가 발생한다.")
    void Spy_Calls_Real_Method_V2() {
        // given
        User newUser = new User("devjun");

        // when
        assertThrows(IllegalArgumentException.class,
                () -> when(userService.save(null)).thenReturn(new User(1L, "devjun"))
        );
    }

    @Test
    @DisplayName("Spy를 사용하고 상호작용을 하지 않으면 WantedButNotInvoked가 발생한다.")
    void Spy_Not_Calls_Real_Method_V2() {
        // given
        User newUser = new User("devjun");
        when(userService.save(newUser)).thenReturn(new User(1L, "devjun"));

        // when, then
        assertThrows(WantedButNotInvoked.class,
                () -> verify(userJpaRepository, times(1)).save(any(User.class))
        );
    }

    @Test
    @DisplayName("Spy를 사용하고 상호작용을 하지 않으면 WantedButNotInvoked가 발생한다.")
    void Spy_Not_Calls_Real_Method_Do_Return() {
        // given
        User newUser = new User( "devjun");

        doReturn(newUser).when(userService).save(newUser);

        userService.save(newUser);

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    @Disabled
    @DisplayName("Spy를 사용하고 상호작용을 하지 않으면 WantedButNotInvoked가 발생한다.")
    void Spy_Not_Calls_Real_Method_For_Log() {
        // given
        User newUser = new User("devjun");
        when(userService.save(newUser)).thenReturn(new User(1L, "devjun"));

        // when
        userService.save(newUser);

        // when, then
        verify(userJpaRepository, times(1)).save(any(User.class));
    }
}
