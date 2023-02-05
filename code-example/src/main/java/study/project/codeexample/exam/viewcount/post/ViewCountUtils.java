package study.project.codeexample.exam.viewcount.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ViewCountUtils {

    public List<Long> findPostCookies(Cookie[] cookies) {
        List<Long> postIds = new LinkedList<>();

        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postIds")) {
                    String postId = cookie.getValue();
                    if (!Objects.isNull(postId)) {
                        Set<Long> cookiePostIds = Arrays.stream(postId.split("-"))
                                .map(Long::parseLong)
                                .collect(Collectors.toSet());
                        postIds.addAll(cookiePostIds);
                        return postIds;
                    }
                }
            }
            return postIds;
        } catch (Exception e) {
            log.error("쿠키가 조작되었습니다.");
            return new LinkedList<>();
        }
    }
}
