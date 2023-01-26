package study.project.codeexample.web.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.project.codeexample.exam.response.ApiResponse;
import study.project.codeexample.web.user.application.UserQueryService;
import study.project.codeexample.web.user.presentation.dto.response.UserResponse;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/query/users")
public class UserApiController {

    private final UserQueryService userQueryService;

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> findUserById(@PathVariable Long userId,
                                                  HttpServletRequest httpServletRequest) {
        UserResponse response = userQueryService.findById(userId);
        return ApiResponse.of(response, httpServletRequest);
    }

    @GetMapping("/v2/{userId}")
    public ApiResponse<UserResponse> findUserByIdV2(@PathVariable Long userId) {
        UserResponse response = userQueryService.findById(userId);
        return ApiResponse.of();
    }
}
