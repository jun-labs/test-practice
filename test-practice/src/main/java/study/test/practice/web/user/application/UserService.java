package study.test.practice.web.user.application;

import study.test.practice.domain.user.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();
}
