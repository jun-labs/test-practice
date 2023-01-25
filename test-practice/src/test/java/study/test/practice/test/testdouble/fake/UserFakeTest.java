package study.test.practice.test.testdouble.fake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import study.test.practice.domain.user.entity.User;
import study.test.practice.test.configuration.testcontainer.AbstractTestContainer;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserFakeTest extends AbstractTestContainer {

    @Mock
    private FakeUserService fakeUserService;

    @BeforeEach
    void beforeEach() {
        fakeUserService = new FakeUserService();
    }

    @Test
    @DisplayName("")
    void m() {
        User newUser = mock(User.class);
        User r = mock(User.class);
//        User newUser = new User("devjun");
        User savedUser = new User(1L, "devjun10");

        when(fakeUserService.save(newUser)).thenReturn(savedUser);
//        doReturn(r).when(fakeUserService).save(newUser);
        User result = fakeUserService.save(newUser);

        Assertions.assertNotNull(result);
    }

//    @Test
//    @DisplayName("")
//    void m() {
//        User newUser = new User("devjun");
//        User savedUser = new User(1L, "devjun10");
//
//        when(fakeUserService.save(newUser)).thenReturn(savedUser);
//
//        User result = fakeUserService.save(newUser);
//
//        Assertions.assertEquals(1L, result.getUserId());
//    }
}
