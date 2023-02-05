package study.project.codeexample.web.post.application;

import study.project.codeexample.web.post.presentation.dto.request.PostUpdateRequest;

import java.util.Objects;

public class PostValidation {

    public boolean validateRequest(PostUpdateRequest request) {
        if (Objects.isNull(request.getPostId())) {
            return false;
        }
        if (Objects.isNull(request.getContent())) {
            return false;
        }
        return true;
    }
}
