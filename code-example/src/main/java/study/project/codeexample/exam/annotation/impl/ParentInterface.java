package study.project.codeexample.exam.annotation.impl;

import org.springframework.stereotype.Component;
import study.project.codeexample.exam.annotation.Tag;

@Tag(description = "부모 클래스")
@Component
public interface ParentInterface {
}
