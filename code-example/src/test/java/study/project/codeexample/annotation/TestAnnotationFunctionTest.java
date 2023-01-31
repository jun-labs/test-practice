package study.project.codeexample.annotation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.condition.JRE.JAVA_17;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;

@TestAnnotation
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TestAnnotationFunctionTest {

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    @EnabledOnOs(OS.MAC)
    void 어노테이션_기능_테스트_V1() {
        System.out.println("Test");
    }

    @Test
    @EnabledForJreRange(min = JAVA_9, max = JAVA_17)
    @EnabledOnOs(OS.MAC)
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void 어노테이션_기능_테스트_V2() throws Exception {
        System.out.println("Test");
    }

    @Test
    @Disabled(value = "버그가 수정되기 전까지 테스트를 하지 않는다.")
    void 어노테이션_기능_테스트_V3() throws Exception {

    }
}
