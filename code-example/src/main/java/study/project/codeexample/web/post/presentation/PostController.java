package study.project.codeexample.web.post.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.project.codeexample.exam.response.ApiResponse;
import study.project.codeexample.exam.viewcount.post.PostViewCountIncrement;
import study.project.codeexample.web.post.application.PostQueryService;
import study.project.codeexample.web.post.presentation.dto.request.PostUpdateRequest;
import study.project.codeexample.web.post.presentation.dto.response.PostResponse;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostQueryService postQueryService;

    @PostViewCountIncrement
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPostByIdSimple(@PathVariable Long postId) {
        PostResponse response = new PostResponse(postQueryService.findById(postId));

        // 1. 헤더 생성 및 헤더에 데이터 넣어주는 로직 추가
        // 2. 헤더에 데이터 추가 및 조립
        // .......

        return ResponseEntity.ok(response);
    }

    @PostViewCountIncrement
    @GetMapping("/v1/{postId}")
    public ApiResponse<PostResponse> findPostById(@PathVariable Long postId) {
        PostResponse response = new PostResponse(postQueryService.findById(postId));
        return ApiResponse.of(response);
    }

    @PostViewCountIncrement
    @GetMapping("/v2/{postId}")
    public ResponseEntity<PostResponse> findPostByIdResponseEntity(@PathVariable Long postId) {
        PostResponse response = new PostResponse(postQueryService.findById(postId));
        return ResponseEntity
                .status(200)
                .body(response);
    }

    @PostViewCountIncrement
    @GetMapping("/v3/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId,
                                           @RequestBody PostUpdateRequest request) {
        postQueryService.update(postId, request);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/v4/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> findById(@PathVariable Long postId,
                                                              HttpServletResponse httpServletResponse) {

        // 무시
        httpServletResponse.setHeader("key", "value");

        PostResponse response = new PostResponse(postQueryService.findById(postId));
        return ResponseEntity
                .status(200)
                .body(ApiResponse.of(response));
    }
}
