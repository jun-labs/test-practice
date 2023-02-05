package study.project.codeexample.exam.viewcount.post;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import study.project.codeexample.exam.redis.RedisService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class PostAspect {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ViewCountUtils viewCountUtils;

    @Pointcut("@annotation(study.project.codeexample.exam.viewcount.post.PostViewCountIncrement)")
    private void viewCount() {
    }

    @Pointcut("execution(* study.project.codeexample.web.post.presentation.PostController.findPostById())")
    private void viewCountV2() {
    }

    @Before("viewCount()")
    public void increaseViewCountV1(JoinPoint jp) {
        HttpServletRequest httpServletRequest = extractHttpServletRequest();
        Cookie[] cookies = extractCookies(httpServletRequest);
        List<Long> postIds = viewCountUtils.findPostCookies(cookies);
        Long postId = extractPathVariable(httpServletRequest);
        postIds.add(postId);

        String cookieValue = postIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("-"));
        httpServletRequest.setAttribute("postIds", cookieValue);

        if (!postIds.contains(postId)) {
            redisService.increaseViewCount(postId);
        }

        log.info("HttpServletRequest: {}", httpServletRequest);
        log.info("Cookie: {}", postIds);
    }

    @After("viewCount()")
    public void after(JoinPoint jp) {

    }

    // @Around("study.project.codeexample.exam.aspect.post.PostAspect.viewCountV2()")
    public void increaseViewCountV2(JoinPoint jp) throws Throwable {
    }

    private HttpServletRequest extractHttpServletRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    private Cookie[] extractCookies(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getCookies();
    }

    private Long extractPathVariable(HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] temp;
        try {
            temp = requestURI.split("/");
            return Long.parseLong(temp[temp.length - 1]);
        } catch (Exception e) {
            log.error("올바르지 않은 파라미터 입니다.");
            throw new IllegalArgumentException();
        }
    }
}
