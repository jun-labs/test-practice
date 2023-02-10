package study.project.codeexample.exam.converter.converter;

import org.springframework.stereotype.Component;
import study.project.codeexample.domain.user.entity.User;
import study.project.codeexample.exam.converter.DomainModelConverTer;
import study.project.codeexample.exam.converter.domainmodel.UserDomainModel;

import java.io.Serializable;

@Component
public class UserDomainConverter implements DomainModelConverTer<User, UserDomainModel>, Serializable {

    @Override
    public UserDomainModel convertToDomainModel(User user) {
        return new UserDomainModel(
                user.getId(),
                user.getName(),
                user.getVersion()
        );
    }

    @Override
    public User convertToEntity(UserDomainModel domain) {
        return new User(
                domain.getId(),
                domain.getName(),
                domain.getVersion()
        );
    }
}
