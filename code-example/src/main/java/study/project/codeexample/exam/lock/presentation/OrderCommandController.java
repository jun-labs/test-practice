package study.project.codeexample.exam.lock.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.project.codeexample.exam.lock.application.OrderCommandService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/command/orders")
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

    @PostMapping
    public void orderV1(HttpServletRequest httpServletRequest) throws InterruptedException {
        Long userId = Long.valueOf(httpServletRequest.getHeader("userId"));
        String name = String.valueOf(httpServletRequest.getHeader("name"));
        orderCommandService.orderV1(userId, name);
    }

    @PostMapping("/v2")
    public void orderV2(HttpServletRequest httpServletRequest) throws InterruptedException {
        Long userId = Long.valueOf(httpServletRequest.getHeader("userId"));
        String name = String.valueOf(httpServletRequest.getHeader("name"));
        orderCommandService.orderV2(userId, name);
    }

    @PostMapping("/v3")
    public void orderV3(HttpServletRequest httpServletRequest) throws InterruptedException {
        Long userId = Long.valueOf(httpServletRequest.getHeader("userId"));
        String name = String.valueOf(httpServletRequest.getHeader("name"));
        orderCommandService.orderV3(userId, name);
    }
}
