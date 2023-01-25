package study.test.practice.test.testdouble.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.test.practice.domain.user.entity.User;
import study.test.practice.domain.user.infrastructure.UserJpaRepository;
import study.test.practice.test.configuration.testcontainer.AbstractTestContainer;
import study.test.practice.web.application.UserServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends AbstractTestContainer {

    @Mock
    private UserJpaRepository userJpaRepository;

    /**
     * 인터페이스가 아닌 구현체를 넣어줘야 합니다.
     */
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Mocking을 하게 되면 실제 레포지토리를 호출하지 않는다.")
    void Mocking_Not_Calls_Real_Method() {
        User newUser = new User("devjun");
        when(userService.save(newUser)).thenReturn(new User(1L, "devjun"));

        // mock을 검증
        verify(userJpaRepository, times(0)).save(any(User.class));
    }

    @Test
    @DisplayName("Mocking을 하고 상호작용을 검증하지 않더라도 오류가 발생하지 않는다.")
    void Mocking_Not_Calls_Real_Method2() {
        User newUser = new User("devjun");
        when(userService.save(newUser)).thenReturn(new User(1L, "devjun"));
    }

    @Test
    @DisplayName("Mocking을 하게 되면 원하는 결과 값이 반환된다.")
    void Mocking_Gives_Wanted_Result() {
        // given
        User newUser = new User("devjun");
        User result = new User(1L, "devjun");
        when(userService.save(newUser)).thenReturn(result);

        // when
        User savedUser = userService.save(newUser);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, savedUser.getUserId()),
                () -> Assertions.assertEquals("devjun", savedUser.getName())
        );
    }

    public int calculateAndUpdateToDatabase(int numberA, int numberB) {
        int newResult = numberA + numberB;
        save(newResult);
        return newResult;
    }

    public void save(int number){
        // save(number);
    }

}
