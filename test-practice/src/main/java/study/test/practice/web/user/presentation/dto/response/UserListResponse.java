package study.test.practice.web.user.dto.response;

import study.test.practice.domain.user.entity.User;

import java.util.List;

public class UserListResponse {

    private final List<UserResponse> users;

    public UserListResponse(List<User> users) {
        this.users = users.stream()
                .map(UserResponse::new)
                .toList();
    }

    public List<UserResponse> getUsers() {
        return users;
    }
}
