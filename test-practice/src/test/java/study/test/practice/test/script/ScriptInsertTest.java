package study.test.practice.test.script;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import study.test.practice.test.configuration.DatabaseConfiguration;
import study.test.practice.domain.user.entity.User;
import study.test.practice.domain.user.infrastructure.UserJpaRepository;
import study.test.practice.test.InsertData;

// SQL 먼저 실행 -> BeforeEach
@InsertData
@SpringBootTest
@ActiveProfiles("test")
class ScriptInsertTest {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @AfterEach
    void beforeEach() {
        databaseConfiguration.truncateAllEntity();
    }

    @Test
    @DisplayName("@InsertData로 데이터가 삽입된다.")
    void INSERT_TEST() {
        User user = userJpaRepository.findById(1L).get();
        User user2 = userJpaRepository.findById(2L).get();
        Assertions.assertNotNull(user.getUserId());
        Assertions.assertNotNull(user2.getUserId());
    }

    @Test
    @DisplayName("이전 데이터는 초기화 되며, @InsertData로 데이터가 다시 한 번 초기화된다.")
    void BEFORE_INSERT_DATA_INIT_TEST() {
        User user = userJpaRepository.findById(1L).get();
        Assertions.assertNotNull(user.getUserId());
    }
}
