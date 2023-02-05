package study.project.codeexample.web.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.codeexample.common.exception.BusinessException;
import study.project.codeexample.domain.board.entity.Post;
import study.project.codeexample.domain.board.infrastructure.PostJpaRepository;
import study.project.codeexample.web.post.presentation.dto.request.PostUpdateRequest;

import java.util.Objects;

import static study.project.codeexample.domain.board.exception.PostExceptionType.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostJpaRepository postJpaRepository;
    private PostValidation postValidation;

    @Transactional
    public void update(Long postId, PostUpdateRequest request) {
        Post findPost = postJpaRepository.findById(postId)
                .orElseThrow(() -> BusinessException.of(POST_NOT_FOUND));

        // 복잡한 로직은 최종 결과를 통해 알 수 있도록 테스트합니다.
        validateRequest(request);
    }

    // 이를 위한 로직이 있어선 안됩니다.
    private boolean validateRequest(PostUpdateRequest request) {
        if (Objects.isNull(request.getPostId())) {
            return false;
        }
        if (Objects.isNull(request.getContent())) {
            return false;
        }
        return true;
    }

    public boolean validateRequestForTest(PostUpdateRequest request) {
        if (Objects.isNull(request.getPostId())) {
            return false;
        }
        if (Objects.isNull(request.getContent())) {
            return false;
        }
        return true;
    }
}
