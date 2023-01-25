package study.test.practice.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import study.test.practice.domain.user.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
