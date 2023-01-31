package study.test.practice.test.listner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
@AcceptanceTest
class TestV1 {

    @Test
    @DisplayName("테스트_V1")
    void 테스트_V1() throws Exception {
        log.info("테스트_V1");
    }
}
