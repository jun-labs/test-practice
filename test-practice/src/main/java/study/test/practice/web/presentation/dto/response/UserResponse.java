package study.test.practice.web.presentation.dto.response;

import study.test.practice.domain.user.entity.User;

public class UserResponse {

    private final Long userId;
    private final String name;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
