package study.test.practice.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.web.application.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/api/user-list")
//    public ResponseEntity<UserListResponse> signup() {
//        UserListResponse response = new UserListResponse(userService.findAll());
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/api/users")
//    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest request) {
//        UserSignupResponse response = new UserSignupResponse(userService.save(new User(request.getName())));
//        return ResponseEntity.ok(response);
//    }
}
