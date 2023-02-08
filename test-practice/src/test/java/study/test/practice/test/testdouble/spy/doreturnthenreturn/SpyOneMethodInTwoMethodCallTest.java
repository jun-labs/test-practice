package study.test.practice.test.testdouble.spy.doreturnthenreturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.verification.opentest4j.ArgumentsAreDifferent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.user.application.UserCommandService;
import study.test.practice.web.user.presentation.UserCommandController;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpyOneMethodInTwoMethodCallTest extends AbstractTestConfiguration {

    @SpyBean
    private UserCommandService userCommandService;

    @Autowired
    private UserCommandController userCommandController;

    @Test
    @DisplayName("한 동작을 스파이로 대체했을 때 내부의 다른 동작은 테스트할 수 없다.")
    void 하나의_동작에서_두_개의_메서드_테스트() {
        when(userCommandService.getInt(anyInt())).thenReturn(1);
        when(userCommandService.getInt()).thenReturn(1);

        for (int index = 1; index <= 10; index++) {
            userCommandController.getIntValue(index);
        }

        Assertions.assertThrows(ArgumentsAreDifferent.class,
                () -> verify(userCommandService, times(10)).getInt());
    }
}
