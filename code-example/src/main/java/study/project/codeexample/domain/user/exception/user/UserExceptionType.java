package study.project.codeexample.domain.user.exception.user;

import org.springframework.http.HttpStatus;
import study.project.codeexample.common.exception.BaseExceptionType;

public enum UserExceptionType implements BaseExceptionType {

    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다.", 404, HttpStatus.NOT_FOUND);

    private final String message;
    private final int code;
    private final HttpStatus httpStatus;

    UserExceptionType(String message, int code, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
