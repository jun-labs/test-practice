package study.project.codeexample.web.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.codeexample.common.exception.BusinessException;
import study.project.codeexample.domain.board.entity.Post;
import study.project.codeexample.domain.board.infrastructure.PostJpaRepository;
import study.project.codeexample.web.post.presentation.dto.request.PostUpdateRequest;

import static study.project.codeexample.domain.board.exception.PostExceptionType.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostJpaRepository postJpaRepository;

    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> BusinessException.of(POST_NOT_FOUND));
    }

    @Transactional
    public void update(Long postId, PostUpdateRequest request) {
        Post findPost = postJpaRepository.findById(postId)
                .orElseThrow(() -> BusinessException.of(POST_NOT_FOUND));
    }
}
