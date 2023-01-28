package study.project.codeexample.web.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.domain.user.infrastructure.UserJpaRepository;
import study.project.codeexample.exam.lock.LockUtils;
import study.project.codeexample.web.exception.user.UserNotFoundException;
import study.project.codeexample.web.user.presentation.dto.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserJpaRepository userJpaRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserResponse findById(Long userId) {
        User findUser = userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserResponse.of(findUser);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User findUserByIdOptimisticLock(Long userId, String name) throws InterruptedException {
        User findUser = userJpaRepository.findUserByIdOptimisticLock(userId).orElseThrow(UserNotFoundException::new);
        findUser.update(name);
        LockUtils.sleep(1);
        return findUser;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User findUserByIdOptimisticLockRead(Long userId, String name) {
        User findUser = userJpaRepository.findUserByIdOptimisticLock(userId).orElseThrow(UserNotFoundException::new);
        return findUser;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User findUserByIdOptimisticLockForceIncrement(Long userId, String name) throws InterruptedException {
        User findUser = userJpaRepository.findUserByIdOptimisticLockWithForceIncrement(userId).orElseThrow(UserNotFoundException::new);
        findUser.update(name);
        LockUtils.sleep(1);
        return findUser;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User findUserByIdPessimisticLockRead(Long userId, String name) {
        User findUser = userJpaRepository.findUserByIdPessimisticLockRead(userId).orElseThrow(UserNotFoundException::new);
        return findUser;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User findUserByIdPessimisticLockWrite(Long userId, String name) throws InterruptedException {
        User findUser = userJpaRepository.findUserByIdPessimisticLockWrite(userId).orElseThrow(UserNotFoundException::new);
        findUser.update(name);
        LockUtils.sleep(1);
        return findUser;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User findUserByIdPessimisticLockWithForceIncrement(Long userId, String name) throws InterruptedException {
        User findUser = userJpaRepository.findUserByIdPessimisticLockWithForceIncrement(userId).orElseThrow(UserNotFoundException::new);
        findUser.update(name);
        LockUtils.sleep(1);
        return findUser;
    }
}
