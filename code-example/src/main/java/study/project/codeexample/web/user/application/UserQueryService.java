package study.project.codeexample.web.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.domain.user.infrastructure.UserJpaRepository;
import study.project.codeexample.web.exception.user.UserNotFoundException;
import study.project.codeexample.web.user.presentation.dto.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        User findUser = userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserResponse.of(findUser);
    }
}
