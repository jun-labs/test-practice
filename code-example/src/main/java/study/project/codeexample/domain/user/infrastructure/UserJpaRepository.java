package study.project.codeexample.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.codeexample.domain.user.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
