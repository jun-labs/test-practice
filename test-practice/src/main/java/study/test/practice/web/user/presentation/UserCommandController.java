package study.test.practice.web.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.domain.user.entity.User;
import study.test.practice.web.user.application.UserCommandService;
import study.test.practice.web.user.application.UserService;
import study.test.practice.web.user.presentation.dto.request.UserSignupRequest;
import study.test.practice.web.user.presentation.dto.response.UserResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserCommandService userCommandService;

    @PostMapping
    public UserResponse save(@RequestBody UserSignupRequest request) {
        User newUser = new User(request.getName());
        User savedUser = userCommandService.save(newUser);
        return new UserResponse(savedUser);
    }
}
