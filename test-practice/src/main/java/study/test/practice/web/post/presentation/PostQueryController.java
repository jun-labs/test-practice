package study.test.practice.web.post.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.web.post.application.PostQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/query")
public class PostQueryController {

    private final PostQueryService postQueryService;

    @PostMapping
    public Long findPostPKById(Long value) {
        return postQueryService.findPostPKById(value);
    }
}
