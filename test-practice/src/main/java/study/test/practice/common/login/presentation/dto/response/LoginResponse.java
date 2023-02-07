package study.test.practice.common.login.presentation.dto.response;

public class LoginResponse {

    private String access_token;

    private LoginResponse() {
    }

    public LoginResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    @Override
    public String toString() {
        return access_token;
    }
}
