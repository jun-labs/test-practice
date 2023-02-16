package study.project.codeexample.web.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.project.codeexample.exam.response.ApiResponse;
import study.project.codeexample.web.user.application.UserCommandService;
import study.project.codeexample.web.user.presentation.dto.response.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/command/users")
public class UserCommandController {

    private final UserCommandService userCommandService;

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> findUserById(
            @PathVariable Long userId,
            @RequestParam("name") String name
    ) {
        UserResponse response = userCommandService.update(userId, name);
        return ApiResponse.of(response);
    }

    @GetMapping("/v2/{userId}")
    public String findUserByIdWithCache(
            @PathVariable Long userId,
            @RequestParam("name") String name
    ) {
        return userCommandService.updateV2(userId, name).getName();
    }
}
