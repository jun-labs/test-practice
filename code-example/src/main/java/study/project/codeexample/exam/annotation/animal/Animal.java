package study.project.codeexample.exam.annotation.animal;

import lombok.extern.slf4j.Slf4j;
import study.project.codeexample.exam.annotation.Environment;

@Slf4j
@Environment
public abstract class Animal {

    void test() {
        log.info("Animal");
    }
}
