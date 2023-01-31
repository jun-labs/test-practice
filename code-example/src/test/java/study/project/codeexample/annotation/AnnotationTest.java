package study.project.codeexample.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import study.project.codeexample.exam.annotation.Environment;
import study.project.codeexample.exam.annotation.Season;
import study.project.codeexample.exam.annotation.animal.Dog;
import study.project.codeexample.exam.annotation.clazz.ChildrenClazz;
import study.project.codeexample.exam.annotation.clazz.ChildrenClazzV2;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class AnnotationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("단순히 어노테이션을 붙이는 것만으로는 구체 클래스가 빈으로 등록되지 않는다.")
    void 어노테이션_빈_등록_테스트_V0() {
        assertThatThrownBy(() -> applicationContext.getBean(Dog.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    @DisplayName("어노테이션을 통해 구체 클래스를 빈으로 등록이 가능하다.")
    void 어노테이션_빈_등록_테스트_V2() {
        ChildrenClazz clazz = applicationContext.getBean(ChildrenClazz.class);
        List<Annotation> annotations = Arrays.stream(clazz.getClass().getAnnotations()).toList();
        log.info("Annotations: {}", annotations);
        Assertions.assertNotNull(clazz);
    }

    @Test
    @DisplayName("어노테이션에서 값을 꺼내 사용할 수 있다.")
    void 어노테이션_빈_등록_테스트_V3() {
        ChildrenClazzV2 clazz = applicationContext.getBean(ChildrenClazzV2.class);
        List<Annotation> annotations = Arrays.stream(clazz.getClass().getAnnotations()).toList();
        log.info("Annotations: {}", annotations);

        Season annotation = (Season) annotations.stream()
                .filter(isExtendsAnnotation())
                .findAny().get();

        log.info("Annotation: {}", annotation.weather());

        Assertions.assertNotNull(annotation);
    }

    private Predicate<Annotation> isExtendsAnnotation() {
        return annotation -> annotation.annotationType().equals(Season.class);
    }

    @Test
    @DisplayName("@Inherited가 붙어있다면 자식 어노테이션은 부모 어노테이션을 상속받는다.")
    void 어노테이션_빈_등록_테스트_V4() {
        Assertions.assertTrue(Dog.class.isAnnotationPresent(Environment.class));
    }
}
