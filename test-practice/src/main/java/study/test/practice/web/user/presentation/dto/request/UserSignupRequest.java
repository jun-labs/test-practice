package study.test.practice.web.user.presentation.dto.request;

public class UserSignupRequest {

    private String name;

    private UserSignupRequest() {
    }

    public UserSignupRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

