package study.test.practice.web.presentation.dto.response;

import study.test.practice.domain.user.entity.User;

public class UserSignupResponse {

    private final Long userId;

    public UserSignupResponse(User user) {
        this.userId = user.getUserId();
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userId.toString();
    }
}
