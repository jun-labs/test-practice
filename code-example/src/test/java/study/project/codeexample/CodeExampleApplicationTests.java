package study.project.codeexample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import study.project.codeexample.exam.annotation.Tag;
import study.project.codeexample.exam.annotation.clazz.ChildrenClazz;
import study.project.codeexample.exam.annotation.clazz.ParentClazz;
import study.project.codeexample.exam.annotation.impl.ChildrenImpl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
public class CodeExampleApplicationTests {

    @Test
    @DisplayName("부모의 어노테이션은 @Inherited가 붙으면 상속된다.")
    void 어노테이션_상속_테스트() {
        ChildrenClazz childrenClazz = new ChildrenClazz();

        List<Annotation> lst = Arrays.stream(childrenClazz.getClass().getAnnotations()).toList();
        Assertions.assertTrue(lst.size() != 0);
    }

    @Test
    @DisplayName("상속받은 어노테이션은 한 번만 읽으며 동일하다.")
    void 어노테이션_상속_테스트_V2() {
        ChildrenClazz childrenClazzV1 = new ChildrenClazz();
        ChildrenClazz childrenClazzV2 = new ChildrenClazz();

        Annotation childV1Annotation = childrenClazzV1.getClass().getAnnotation(Tag.class);
        Annotation childV2Annotation = childrenClazzV2.getClass().getAnnotation(Tag.class);

        Assertions.assertEquals(childV1Annotation, childV2Annotation);
    }

    @Test
    @DisplayName("추상클래스를 컴포넌트로 등록하고 자식은 빈으로 등록하지 않아으면 NoSuchBeanDefinitionException이 발생한다.")
    void 추상클래스_빈_등록_후_자식_클래스_빈_등록여부_테스트() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CodeExampleApplicationTests.class);
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> context.getBean(ChildrenClazz.class)
        );
    }

    @Test
    @DisplayName("추상클래스를 컴포넌트로 등록해도 구체클래스가 아니라면 NoSuchBeanDefinitionException이 발생한다.")
    void 추상클래스_빈_등록_유무_테스트() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CodeExampleApplicationTests.class);
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> context.getBean(ParentClazz.class)
        );
    }

    @Test
    @DisplayName("인터페이스를 컴포넌트로 등록하고 자식은 빈으로 등록하지 않으면 NoSuchBeanDefinitionException이 발생한다.")
    void 인터페이스_빈_등록_테스트() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CodeExampleApplicationTests.class);
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> context.getBean(ChildrenImpl.class)
        );
    }

}
