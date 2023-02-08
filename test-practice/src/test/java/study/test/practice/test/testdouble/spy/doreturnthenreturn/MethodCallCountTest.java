package study.test.practice.test.testdouble.spy.doreturnthenreturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.springframework.boot.test.mock.mockito.SpyBean;
import study.test.practice.test.configuration.configuration.AbstractTestConfiguration;
import study.test.practice.web.post.presentation.PostQueryService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("스파이 doReturn times 테스트")
class MethodCallCountTest extends AbstractTestConfiguration {

    @SpyBean
    private PostQueryService postQueryService;

    @Test
    @DisplayName("doReturn을 사용하면 메서드 호출 횟수는 세지만 실제 호출은 하지 않는다.")
    void doReturn_Stub_실제_메서드_호출_테스트() {
        // given
        Long fixedPostPK = 1L;
        PostQueryService spy = spy(postQueryService);
        doReturn(fixedPostPK).when(spy).findPostPKById(fixedPostPK);

        // when
        int count = 0;
        while (true) {
            if (count == 10) {
                break;
            }
            spy.findPostPKById(fixedPostPK);
            count++;
        }

        // then
        verify(spy, times(10)).findPostPKById(fixedPostPK);
    }

    @Test
    @DisplayName("doReturn을 사용하지 않으면 메서드 호출 세며 실제 메서드를 호출한다.")
    void doReturn_실제_메서드_호출_테스트() {
        // given
        Long fixedPostPK = 1L;
        PostQueryService spy = spy(postQueryService);

        // when
        int count = 0;
        while (true) {
            if (count == 10) {
                break;
            }
            spy.findPostPKById(fixedPostPK);
            count++;
        }

        // then
        verify(spy, times(10)).findPostPKById(fixedPostPK);
    }

    @Test
    @DisplayName("스파이의 동작을 스터빙하고 다른 메서드를 테스트하면 WantedButNotInvoked가 발생한다.")
    void 스파이_다른_메서드_호출_테스트() {
        // given
        Long fixedPostPK = 1L;
        PostQueryService spy = spy(postQueryService);
        doReturn(fixedPostPK).when(spy).findPostPKById(fixedPostPK);
        doReturn(fixedPostPK).when(spy).getFixedValue();

        // when
        int count = 0;
        while (true) {
            if (count == 10) {
                break;
            }
            spy.findPostPKById(fixedPostPK);
            count++;
        }

        // then
        assertThatThrownBy(() -> verify(spy, times(10)).getFixedValue())
                .isInstanceOf(WantedButNotInvoked.class);
    }

    @Test
    @DisplayName("thenReturn을 사용하면 메서드 호출 횟수는 세지만 실제 호출은 한 번만 한다.")
    void thenReturn_Stub_실제_메서드_호출_횟수_테스트() {
        // given
        Long fixedPostPK = 1L;
        PostQueryService spy = spy(postQueryService);
        when(spy.findPostPKById(fixedPostPK)).thenReturn(fixedPostPK);

        // when
        int count = 0;
        while (true) {
            if (count == 10) {
                break;
            }
            spy.findPostPKById(fixedPostPK);
            count++;
        }

        // then
        verify(spy, times(10)).findPostPKById(fixedPostPK);
    }

    @Test
    @DisplayName("thenReturn을 사용하면 메서드 호출 횟수는 세지만 실제 호출은 한 번만 한다.")
    void thenReturn_Each_Stub_실제_메서드_호출_횟수_테스트() {
        // given
        Long fixedPostPK = 1L;
        PostQueryService spy = spy(postQueryService);

        // when
        int count = 0;
        while (true) {
            if (count == 10) {
                break;
            }
            when(spy.findPostPKById(fixedPostPK)).thenReturn(fixedPostPK);
            spy.findPostPKById(fixedPostPK);
            count++;
        }

        // then
        verify(spy, times(10)).findPostPKById(fixedPostPK);
    }

    @Test
    @DisplayName("thenReturn을 사용하지 않으면 메서드 호출 세며 실제 메서드를 호출한다.")
    void thenReturn_실제_메서드_호출_횟수_테스트() {
        // given
        Long fixedPostPK = 1L;
        PostQueryService spy = spy(postQueryService);

        // when
        int count = 0;
        while (true) {
            if (count == 10) {
                break;
            }
            spy.findPostPKById(fixedPostPK);
            count++;
        }

        // then
        verify(spy, times(10)).findPostPKById(fixedPostPK);
    }
}
