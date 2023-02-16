package study.project.codeexample.web.user.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.project.codeexample.exam.response.ApiResponse;
import study.project.codeexample.web.user.application.UserQueryService;
import study.project.codeexample.web.user.presentation.dto.response.UserResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/query/users")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> findUserById(@PathVariable Long userId) {
        UserResponse response = userQueryService.findById(userId);
        return ApiResponse.of(response);
    }

    @GetMapping("/v2/{userId}")
    public ApiResponse<UserResponse> findUserByIdV2(@PathVariable Long userId) {
        UserResponse response = userQueryService.findById(userId);
        return ApiResponse.of(response);
    }

    @GetMapping("/v4/{userId}")
    public String findUserNameById(
            @PathVariable Long userId,
            @RequestParam("name") String name
    ) {
        log.info("Name: {}", name);
        UserResponse response = userQueryService.findUserNameByIdAndName(userId, name);
        return response.getName();
    }
}
