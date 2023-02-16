package study.project.codeexample.web.user.presentation.dto.response;

import study.project.codeexample.domain.user.entity.User;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private final String name;
    private final String domain;

    private UserResponse(User user) {
        this.name = user.getName();
        this.domain = "user";
    }

    public static UserResponse of(User user) {
        return new UserResponse(user);
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return name;
    }
}
