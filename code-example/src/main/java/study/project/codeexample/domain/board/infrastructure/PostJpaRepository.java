package study.project.codeexample.domain.board.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.codeexample.domain.board.entity.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
