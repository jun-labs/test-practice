package study.project.codeexample.domain.board.exception;

import org.springframework.http.HttpStatus;
import study.project.codeexample.common.exception.BaseExceptionType;

public enum PostExceptionType implements BaseExceptionType {

    POST_NOT_FOUND("게시글을 찾을 수 없습니다.", 404, HttpStatus.NOT_FOUND);

    private final String message;
    private final int code;
    private final HttpStatus httpStatus;

    PostExceptionType(String message, int code, HttpStatus httpStatus) {
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
