package study.test.practice.web.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.test.practice.domain.user.entity.User;
import study.test.practice.domain.user.infrastructure.UserJpaRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("올바른 회원 엔티티를 넣어주세요.");
        }
        return userJpaRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }
}
