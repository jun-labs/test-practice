package study.test.practice.web.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.domain.user.entity.User;
import study.test.practice.web.application.UserService;
import study.test.practice.web.presentation.dto.request.UserSignupRequest;
import study.test.practice.web.presentation.dto.response.UserListResponse;
import study.test.practice.web.presentation.dto.response.UserSignupResponse;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserListResponse> signup() {
        UserListResponse response = new UserListResponse(userService.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest request) {
        UserSignupResponse response = new UserSignupResponse(userService.save(new User(request.getName())));
        return ResponseEntity.ok(response);
    }
}
