package study.test.practice.web.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.web.user.application.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//
//    @PostMapping("/api/users")
//    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest request) {
//        UserSignupResponse response = new UserSignupResponse(userService.save(new User(request.getName())));
//        return ResponseEntity.ok(response);
//    }
}
