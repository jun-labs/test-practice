package study.project.codeexample.web.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.project.codeexample.common.exception.BusinessException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ErrorResponse catchBusinessException(BusinessException businessException) {
        return ErrorResponse.of(businessException);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse catchBadRequest(BusinessException businessException) {
        return ErrorResponse.of(businessException);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ErrorResponse catchUnresolvedException(BusinessException businessException) {
        return ErrorResponse.of(businessException);
    }
}
