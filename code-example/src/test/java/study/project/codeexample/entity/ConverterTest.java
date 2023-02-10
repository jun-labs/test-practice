package study.project.codeexample.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.domain.user.infrastructure.UserJpaRepository;
import study.project.codeexample.exam.converter.DomainModelConverTer;
import study.project.codeexample.exam.converter.converter.UserDomainConverter;
import study.project.codeexample.exam.converter.domainmodel.UserDomainModel;

@DataJpaTest
@ActiveProfiles("test")
@Import(UserDomainConverter.class)
@DisplayName("도메인 모델 컨버터 테스트")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConverterTest {

    @Autowired
    private DomainModelConverTer<User, UserDomainModel> converter;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("엔티티를 도메인모델로 변환할 수 있다.")
    void 엔티티_도메인모델_변환_테스트() {
        // given
        User user = userJpaRepository.save(new User("devjun10"));

        // when
        UserDomainModel domainModel = converter.convertToDomainModel(user);

        // then
        Assertions.assertEquals(domainModel.getId(), user.getId());
        Assertions.assertEquals(domainModel.getName(), user.getName());
        Assertions.assertEquals(domainModel.getVersion(), user.getVersion());
    }

    @Test
    @DisplayName("엔티티를 도메인모델로 변환할 수 있다.")
    void 도메인모델_엔티티_변환_테스트() {
        // given
        User user = userJpaRepository.save(new User("devjun10"));
        UserDomainModel domainModel = converter.convertToDomainModel(user);

        // when
        User convertedEntity = converter.convertToEntity(domainModel);

        // then
        Assertions.assertEquals(user.getId(), convertedEntity.getId());
        Assertions.assertEquals(user.getName(), convertedEntity.getName());
        Assertions.assertEquals(user.getVersion(), convertedEntity.getVersion());
    }
}
