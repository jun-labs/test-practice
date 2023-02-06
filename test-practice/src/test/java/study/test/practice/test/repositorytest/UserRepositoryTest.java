package study.test.practice.test.repositorytest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import study.test.practice.common.jpa.JpaConfiguration;
import study.test.practice.domain.user.entity.User;
import study.test.practice.domain.user.infrastructure.UserJpaRepository;
import study.test.practice.domain.user.infrastructure.UserQueryDSLRepository;
import study.test.practice.test.configuration.DatabaseConfiguration;

@DataJpaTest(
        includeFilters =
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = UserQueryDSLRepository.class
        ))
@ActiveProfiles("test")
@Import({JpaConfiguration.class, UserQueryDSLRepository.class, DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private UserQueryDSLRepository userQueryDSLRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void beforeEach() {
        databaseConfiguration.truncateAllEntity();
    }

    @Test
    @DisplayName("존재하는 사용자를 PK로 조회하면 해당 사용자가 반환된다.")
    void 레포지토리_테스트() {
        User newUser = userJpaRepository.save(new User("devjun10"));
        User findUser = userQueryDSLRepository.findById(newUser.getUserId()).orElseThrow();
        Assertions.assertNotNull(findUser.getUserId());
    }
}
