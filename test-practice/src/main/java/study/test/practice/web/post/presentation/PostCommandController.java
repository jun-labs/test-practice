package study.test.practice.web.post.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.web.post.application.PostCommandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/command")
public class PostCommandController {

    private final PostCommandService postCommandService;

    @PostMapping
    public Long save(Long value) {
        postCommandService.save(value);
        return value;
    }
}
