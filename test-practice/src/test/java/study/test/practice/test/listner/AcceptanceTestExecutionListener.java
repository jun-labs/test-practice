package study.test.practice.test.listner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

// 참조: https://mangkyu.tistory.com/264
@Slf4j
public class AcceptanceTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public void afterTestMethod(final TestContext testContext) {
        log.info("INIT DATA");
    }
}
