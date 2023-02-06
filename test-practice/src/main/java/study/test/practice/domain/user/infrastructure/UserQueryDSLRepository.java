package study.test.practice.domain.user.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import study.test.practice.domain.user.entity.User;

import java.util.Optional;

import static study.test.practice.domain.user.entity.QUser.user;

@Repository
public class UserQueryDSLRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public UserQueryDSLRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchOne()
        );
    }
}
