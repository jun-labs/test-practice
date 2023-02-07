package study.test.practice.common.login.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.test.practice.common.login.outter.GithubApi;
import study.test.practice.common.login.presentation.dto.response.LoginResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/github/oauth")
public class LoginController {

    private final GithubApi githubApi;

    @GetMapping
    public ResponseEntity<LoginResponse> login() {
        LoginResponse response = githubApi.callBackData("", "");
        return ResponseEntity.ok(response);
    }
}
